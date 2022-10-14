package io.ugurh.ecommerce.model.response;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author harun ugur
 * @project sample
 * @created 12.10.2022 - 00:53
 */
@Component
public class ResponseBuilder {

    public ResponseEntity<ECommerceApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message, Object data, Map<String, Object> otherParams) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message)
                .withHttpHeader(httpHeader)
                .withData(data)
                .withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity<ECommerceApiResponse> buildResponse(int httpStatusCode, String message, Object data, Map<String, Object> otherParams) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message)
                .withData(data)
                .withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity<ECommerceApiResponse> buildResponse(int httpStatusCode, String message, Map<String, Object> otherParams) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message)
                .withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity<ECommerceApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message, Object data) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message)
                .withHttpHeader(httpHeader)
                .withData(data)
                .build();
    }

    public ResponseEntity<ECommerceApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message, Map<String, Object> otherParams) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message)
                .withHttpHeader(httpHeader)
                .withOtherParams(otherParams)
                .build();
    }

    public ResponseEntity<ECommerceApiResponse> buildResponse(HttpHeaders httpHeader, int httpStatusCode, String message) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message)
                .withHttpHeader(httpHeader)
                .build();
    }

    public ResponseEntity<ECommerceApiResponse> buildResponse(int httpStatusCode, String message, Object data) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message)
                .withData(data)
                .build();
    }

    public ResponseEntity<ECommerceApiResponse> buildResponse(int httpStatusCode, String message) {

        return new ECommerceApiResponse.ApiResponseBuilder<>(httpStatusCode, message).build();
    }

}
