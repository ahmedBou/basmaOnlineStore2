package com.springboot.rest.api.controller;

import com.springboot.rest.api.common.Utils;
import com.springboot.rest.api.request.ProductRequest;
import com.springboot.rest.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    private Utils utils;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> createProduct(@ModelAttribute ProductRequest productRequest) {

        productService.createProduct(productRequest);


        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping(value = "/multiple-uploads")
    public ResponseEntity<Void> createMultipleUpload(@RequestParam("images") MultipartFile[] images) {
        uploadProductImage(images);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public void uploadProductImage(MultipartFile[] images) {
//        // File Output Stream which writes bytes to the server
//        FileOutputStream fileOutputStream = null;
//
//        try {
//
//            for (MultipartFile image : images) {
//                // Upload image to the server
//                fileOutputStream = new FileOutputStream("C:/Users/Sofia/Desktop/" + utils.generatePublicUserId(16));
//                fileOutputStream.write(image.getBytes());
//                fileOutputStream.close();
//            }
//
//
//        }  catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
