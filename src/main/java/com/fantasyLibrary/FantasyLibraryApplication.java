package com.fantasyLibrary;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class FantasyLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(FantasyLibraryApplication.class, args);
	}
	
	@Bean
	public RestTemplate getRestTemplate() {
		HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
		HttpClient httpClient = HttpClientBuilder.create()
		                                               .setRedirectStrategy(new LaxRedirectStrategy())
		                                               .build();
		RestTemplate restTemplate = new RestTemplate();
		factory.setHttpClient(httpClient);
		restTemplate.setRequestFactory(factory);
        return restTemplate;
	}
	

}
