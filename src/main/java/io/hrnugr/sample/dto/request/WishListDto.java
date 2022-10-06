package io.hrnugr.sample.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class WishListDto {
    private Long userId;
    private Long productId;
    private String authToken;
}
