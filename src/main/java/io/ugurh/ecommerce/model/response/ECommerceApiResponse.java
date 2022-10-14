package io.ugurh.ecommerce.model.response;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Map;

/**
 * @author harun ugur
 * @created 12.10.2022 - 00:39
 */
@JsonPropertyOrder({"httpHeaders", "httpStatusCode", "message", "data", "otherParams"})
public class ECommerceApiResponse<T> {

    private final HttpHeaders httpHeaders;
    private final int httpStatusCode;
    private final String message;
    private final T data;
    private final Map<String, Object> otherParams;

    private ECommerceApiResponse(ApiResponseBuilder builder) {
        this.httpHeaders = builder.httpHeaders;
        this.httpStatusCode = builder.httpStatusCode;
        this.message = builder.message;
        this.data = (T) builder.data;
        this.otherParams = builder.otherParams;
    }

    public HttpHeaders getHttpHeaders() {
        return httpHeaders;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public String getMessage() {
        return message;
    }

    public T getData() {
        return data;
    }

    public Map<String, Object> getOtherParams() {
        return otherParams;
    }

    public static class ApiResponseBuilder<T> {

        private final int httpStatusCode;
        private final String message;
        private HttpHeaders httpHeaders = new HttpHeaders();
        private T data;
        private Map<String, Object> otherParams = Collections.emptyMap();

        public ApiResponseBuilder(int httpStatusCode, String message) {
            this.httpStatusCode = httpStatusCode;
            this.message = message;
        }

        public ApiResponseBuilder<T> withHttpHeader(HttpHeaders httpHeader) {
            this.httpHeaders = httpHeader;
            return this;
        }

        public ApiResponseBuilder<T> withData(T data) {
            this.data = data;
            return this;
        }

        public ApiResponseBuilder<T> withOtherParams(Map<String, Object> otherParams) {
            this.otherParams = otherParams;
            return this;
        }

        public ResponseEntity<ECommerceApiResponse> build() {
            ECommerceApiResponse<T> eCommerceApiResponse = new ECommerceApiResponse<>(this);

            return ResponseEntity
                    .status(eCommerceApiResponse.getHttpStatusCode())
                    .headers(eCommerceApiResponse.getHttpHeaders())
                    .body(eCommerceApiResponse);
        }
    }
}