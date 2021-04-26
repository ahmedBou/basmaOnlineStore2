package com.springboot.rest.api.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
public class ProductRequest {
    private String productName;
    private String productCategory;
    private MultipartFile[] productImages;
}
