package org.caalpeva.starwars.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

/**
 * Archivo de configuración de Spring 
 * @author Alberto
 */
@Configuration
@ComponentScan(basePackages = {
		"org.caalpeva.starwars",
		"org.caalpeva.starwars.controller",
		"org.caalpeva.starwars.service"
		})
@Import({WebAppConfig.class, CachingConfig.class})
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
		restTemplate.setInterceptors(interceptors);

		return restTemplate;
	}

}