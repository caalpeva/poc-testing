package org.caalpeva.starwars.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan(basePackages = {
		"org.caalpeva.starwars",
		"org.caalpeva.starwars.controller",
		"org.caalpeva.starwars.service"
		})
@Import(WebAppConfig.class)
public class AppConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(
				MatchingStrategies.STRICT);
		return modelMapper;
	}
	
	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		List<ClientHttpRequestInterceptor> interceptors = restTemplate.getInterceptors();
		if (CollectionUtils.isEmpty(interceptors)) {
			interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		}
		interceptors.add(new ClientHttpRequestInterceptor() {
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().add("user-agent", "Spring-Java-Client/1.0");
				return execution.execute(request, body);
			}
		});
//        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
//	        request.getHeaders().add("user-agent", "spring");
//	        return execution.execute(request, body);
//	    };
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

	@Bean
	public CacheManager cacheManager() {
	    CacheManager cacheManager = CacheManagerBuilder.newCacheManagerBuilder().build();
        cacheManager.init();
        
        return cacheManager;
	}

//	@Bean
//	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
//	    ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
//	        request.getHeaders().add("user-agent", "spring");
//	        return execution.execute(request, body);
//	    };
//	    
//	    return restTemplateBuilder.additionalInterceptors(interceptor).build();
//	}
}