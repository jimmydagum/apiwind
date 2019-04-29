package com.weather.apiwind.cache;

import java.util.ArrayList;

import org.apache.commons.collections4.MapIterator;
import org.apache.commons.collections4.map.LRUMap;

//Generic Caching program - applicable to any java application
public class WindInMemoryCache<K, T> {
	private long timeToLive;
	@SuppressWarnings("rawtypes")
	private LRUMap windCacheMap;

	protected class WindCacheObject {
		public long lastAccessed = System.currentTimeMillis();
		public T value;

		protected WindCacheObject(T value) {
			this.value = value;
		}

	}

	public WindInMemoryCache(long windTimeToLive, final long windTimerInterval, int maxItems) {
		this.timeToLive = windTimeToLive * 1000;

		windCacheMap = new LRUMap<>(maxItems);

		if (timeToLive > 0 && windTimerInterval > 0) {

			Thread t = new Thread(new Runnable() {
				public void run() {
					while (true) {
						try {
							Thread.sleep(windTimerInterval * 1000);
						} catch (InterruptedException ex) {
						}
						cleanup();
					}
				}
			});

			t.setDaemon(true);
			t.start();
		}
	}

	@SuppressWarnings("unchecked")
	public void put(K key, T value) {
		synchronized (windCacheMap) {
			windCacheMap.put(key, new WindCacheObject(value));
		}
	}

	@SuppressWarnings("unchecked")
	public T get(K key) {
		synchronized (windCacheMap) {
			WindCacheObject c = (WindCacheObject) windCacheMap.get(key);

			if (c == null)
				return null;
			else {
				c.lastAccessed = System.currentTimeMillis();
				return c.value;
			}
		}
	}

	public void remove(K key) {
		synchronized (windCacheMap) {
			windCacheMap.remove(key);
		}
	}

	public int size() {
		synchronized (windCacheMap) {
			return windCacheMap.size();
		}
	}

	@SuppressWarnings("unchecked")
	public void cleanup() {

		long now = System.currentTimeMillis();
		ArrayList<K> deleteKey = null;

		synchronized (windCacheMap) {
			MapIterator<?, ?> itr = windCacheMap.mapIterator();

			deleteKey = new ArrayList<K>((windCacheMap.size() / 2) + 1);
			K key = null;
			WindCacheObject c = null;

			while (itr.hasNext()) {
				key = (K) itr.next();
				c = (WindCacheObject) itr.getValue();

				if (c != null && (now > (timeToLive + c.lastAccessed))) {
					deleteKey.add(key);
				}
			}
		}

		for (K key : deleteKey) {
			synchronized (windCacheMap) {
				windCacheMap.remove(key);
			}

			Thread.yield();
		}
	}

}
