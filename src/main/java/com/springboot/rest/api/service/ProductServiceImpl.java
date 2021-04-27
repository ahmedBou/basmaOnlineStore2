package com.springboot.rest.api.service;

import com.springboot.rest.api.common.Utils;
import com.springboot.rest.api.entity.CategoryEntity;
import com.springboot.rest.api.entity.ProductEntity;
import com.springboot.rest.api.exception.UserException;
import com.springboot.rest.api.repository.CategoryRepository;
import com.springboot.rest.api.repository.ProductRepository;
import com.springboot.rest.api.request.ProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private Utils utils;

    @Override
    public void createProduct(ProductRequest productRequest) {
        ProductEntity productEntity = new ProductEntity();

        // Store image names
        String imgName = "";
        ArrayList<String> imageNames = new ArrayList<>();
        String tempImg = "";

        for (int i = 0; i < productRequest.getProductImages().length; i++) {
            tempImg = utils.generatePublicUserId(25);
            imgName += tempImg;

            if (!(i == productRequest.getProductImages().length - 1)) imgName += ",";

            imageNames.add(tempImg);
        }

        // Set product name and product image
        productEntity.setProductName(productRequest.getProductName());
        productEntity.setProductImages(imgName);

        // search for category
        CategoryEntity categoryEntity = categoryRepository.findByCategoryName(productRequest.getProductCategory());

        // if category does exist
        if (categoryEntity == null) throw new UserException("Category does not exists");

        // check size of product images
        if (!(productRequest.getProductImages().length >= 4 && productRequest.getProductImages().length <= 8))
            throw new UserException("Product images must be between 4 and 8 images ");

        // set category for product
        productEntity.setCategory(categoryEntity);

        // save data to database
        productRepository.save(productEntity);

        // upload image to server
        uploadProductImage(productRequest.getProductImages(), imageNames);
    }

    @Override
    public void uploadProductImage(MultipartFile[] images, ArrayList<String> imageNames) {
        // File Output Stream which writes bytes to the server
        FileOutputStream fileOutputStream = null;

        try {

            int counter = 0;

            for (MultipartFile image : images) {
                // Upload image to the server
                fileOutputStream = new FileOutputStream("C:/Users/Admin/Desktop/" + imageNames.get(counter));
                fileOutputStream.write(image.getBytes());

                counter++;
            }

            fileOutputStream.close();

        }  catch (IOException e) {
            e.printStackTrace();
        }
    }
}
