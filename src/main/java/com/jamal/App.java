package com.jamal;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {

    public static void main( String[] args ) {
        RestTemplate restTemplate = new RestTemplate();
        Language lang = Language.EN;

        if (args.length > 0) {
            try {
                lang = Language.valueOf(args[0].toUpperCase());
            } catch (IllegalArgumentException ex) {
                System.out.println("Invalid argument: " + args[0] + ". Should be either en or ru");
                return;
            }
        }

        List<HttpMessageConverter<?>> messageConverterList = new ArrayList<>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverterList.add(converter);
        restTemplate.setMessageConverters(messageConverterList);

        String url = String.format("http://api.forismatic.com/api/1.0/?method=%s&key=%d&format=%s&lang=%s", "getQuote", 457653, "json", lang.name().toLowerCase());
        HttpEntity<QuoteResponse> responseEntity = restTemplate.getForEntity(url, QuoteResponse.class);
        QuoteResponse response = responseEntity.getBody();
        System.out.println("Quote: " + response.getQuoteText());
        System.out.println("Author: " + response.getQuoteAuthor());
    }

}
