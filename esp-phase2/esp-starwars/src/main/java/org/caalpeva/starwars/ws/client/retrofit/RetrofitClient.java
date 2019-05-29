package org.caalpeva.starwars.ws.client.retrofit;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.caalpeva.starwars.ws.api.StarWarsApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.joda.JodaModule;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitClient<T> {

    private static final Logger logger = LoggerFactory.getLogger(RetrofitClient.class);

	private final String USER_AGENT_NAME = "Retrofit-Java-Client/1.0";
	
    private StarWarsRetrofitClientApi retrofitService;
    private static RetrofitClient instance;

    private RetrofitClient(Class className) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(new UserAgentInterceptor(USER_AGENT_NAME))
                .addInterceptor(new RequestLoggingInterceptor());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(StarWarsApi.BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create(
                		new ObjectMapper().registerModule(new JodaModule())))
                .client(httpClientBuilder.build())
                .build();

        retrofitService = retrofit.create(className);
    }

    public static StarWarsRetrofitClientApi getClient(Class className) {
        if (instance == null) {
            instance = new RetrofitClient(className);
        }
        return instance.retrofitService;
    }

    /***************************************************/
    /***************** CLASES PRIVADAS *****************/
    /***************************************************/
    
    private static class RequestLoggingInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            logger.debug("HTTP Request: {}", request);
            logger.debug("HTTP Request headers: {}", request.headers());
            
            return chain.proceed(request);
        }
    }

    private static class UserAgentInterceptor implements Interceptor {
        private final String userAgent;

        public UserAgentInterceptor(String userAgent) {
            this.userAgent = userAgent;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Request requestWithUserAgent = request.newBuilder()
                    .header("User-Agent", userAgent)
                    .build();

            return chain.proceed(requestWithUserAgent);
        }
    }
}