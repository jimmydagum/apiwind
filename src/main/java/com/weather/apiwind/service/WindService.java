package com.weather.apiwind.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import com.weather.apiwind.cache.WindInMemoryCache;
import com.weather.apiwind.model.Wwind;

@Primary
@Component
public class WindService extends WindWrapper {

	// windTimeToLive = 15 minutes or 900 seconds
	// windTimerInterval = 50 seconds
	// maxItems = 20
	private static WindInMemoryCache<Long, Wwind> cache = new WindInMemoryCache<Long, Wwind>(900, 50, 20);

	public WindService(WindImp wind) {
		super(wind);

		// retain value of cache after each call to the service
		getCache();
	}

	public static WindInMemoryCache<Long, Wwind> getCache() {
		return cache;
	}

	@Override
	public Wwind getWind(Long zipcode) {
		// no need to add extra validation for the zipcode:
		// 1. alpaNumeric value - exception handled by default by springboot
		// 2. cached zipcode comes from valid value returned by weather API
		// 3. ZIP code length = 5; may be skipped, API return valid zipcodes only -
		// performance penalty maybe negligible
		Wwind wwind;

		// get wind from cache
		wwind = cache.get(zipcode);
		if (wwind == null) {
			System.out.println("Old Cache.size(): " + cache.size());

			// get wind from weather api and update cache
			wwind = super.getWind(zipcode);
			cache.put(zipcode, wwind);
			System.out.println("After update - New Cache.size(): " + cache.size());
			return wwind;
		}

		System.out.println("Retrieved from - Cache.size(): " + cache.size());
		return wwind;
	}

	// reset cache data - CLI: $ curl -x
	// http://localhost:8080/api/v1/wind/clearCache
	public String clearCache() {
		cache = new WindInMemoryCache<Long, Wwind>(900, 50, 20);
		return "Memoy Cache Reset to size: " + cache.size();
	}

}
