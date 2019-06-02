package org.caalpeva.starwars.configuration;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@EnableCaching
public class CachingConfig {
	
	@Bean
	public CacheManager cacheManager() {
	    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
	    cacheManager.init();
	    
//	    SimpleCacheManager cacheManager = new SimpleCacheManager();
//	    cacheManager.setCaches(Arrays.asList(
//	    		new ConcurrentMapCache("directory"),
//	    		new ConcurrentMapCache("addresses")));
	   
	    return cacheManager;
	}
}