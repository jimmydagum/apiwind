package com.weather.apiwind.cache;

// testing thru separate run
public class WindInMemoryCacheTest {

	public static void main(String[] args) throws InterruptedException {

		WindInMemoryCacheTest windCache = new WindInMemoryCacheTest();

		System.out.println("\n\n==========Test1: windTestAddRemoveObjects ==========");
		windCache.windTestAddRemoveObjects();
		System.out.println("\n\n==========Test2: windTestExpiredCacheObjects ==========");
		windCache.windTestExpiredCacheObjects();
		System.out.println("\n\n==========Test3: windTestObjectsCleanupTime ==========");
		windCache.windTestObjectsCleanupTime();
	}

	private void windTestAddRemoveObjects() {

		// Test with windTimeToLive = 200 seconds
		// windTimerInterval = 500 seconds
		// maxItems = 6
		WindInMemoryCache<Long, String> cache = new WindInMemoryCache<Long, String>(200, 500, 6);

		cache.put(89144L, "Las Vegas");
		cache.put(12345L, "CHICAGO");
		cache.put(88888L, "New York");
		cache.put(34568L, "Santa Clarita");
		cache.put(64236L, "IBM City");
		cache.put(12342L, "Silver City");

		System.out.println("6 Cache Object Added.. cache.size(): " + cache.size());
		cache.remove(64236L);
		System.out.println("One object removed.. cache.size(): " + cache.size());

		cache.put(22222L, "Phoenix");
		cache.put(11111L, "Texas");
		System.out.println("Two objects Added but reached maxItems.. cache.size(): " + cache.size());

	}

	private void windTestExpiredCacheObjects() throws InterruptedException {

		// Test with windTimeToLive = 1 second
		// windTimerInterval = 1 second
		// maxItems = 10
		WindInMemoryCache<String, String> cache = new WindInMemoryCache<String, String>(1, 1, 10);

		cache.put("JIMMY", "JIMMY DAGUM");
		cache.put("KED", "KAYZELLE DAGUM");
		// Adding 3 seconds sleep.. Both above objects will be removed from
		// Cache because of timeToLiveInSeconds value
		Thread.sleep(3000);

		System.out.println("Two objects are added but reached timeToLive. cache.size(): " + cache.size());

	}

	private void windTestObjectsCleanupTime() throws InterruptedException {
		int size = 500000;

		// Test with timeToLiveInSeconds = 100 seconds
		// timerIntervalInSeconds = 100 seconds
		// maxItems = 500000

		WindInMemoryCache<String, String> cache = new WindInMemoryCache<String, String>(100, 100, 500000);

		for (int i = 0; i < size; i++) {
			String value = Integer.toString(i);
			cache.put(value, value);
		}

		Thread.sleep(200);

		long start = System.currentTimeMillis();
		cache.cleanup();
		double finish = (double) (System.currentTimeMillis() - start) / 1000.0;

		System.out.println("Cleanup times for " + size + " objects are " + finish + " s");

	}

}
