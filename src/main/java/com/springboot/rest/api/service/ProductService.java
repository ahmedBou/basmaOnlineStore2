package com.springboot.rest.api.service;

import com.springboot.rest.api.request.ProductRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    void uploadProductImage(MultipartFile[] images, ArrayList<String> imgNames);

}
