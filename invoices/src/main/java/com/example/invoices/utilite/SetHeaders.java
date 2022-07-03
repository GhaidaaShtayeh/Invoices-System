package com.example.invoices.utilite;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

public class SetHeaders {

    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("DELETE");
        source.registerCorsConfiguration("http://localhost:4200/", config);
        return new CorsFilter();
    }


    public HttpHeaders Headers() {

        HttpHeaders headers = new HttpHeaders();

        headers.add("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        headers.add("Access-Control-Allow-Headers", "Access-Control-Allow-Headers, Accept=application/json , Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
        return headers;
    }
    public SetHeaders(){}

}
