package com.springboot.rest.api.repository;

import com.springboot.rest.api.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryEntity, UUID> {
    CategoryEntity findByCategoryName(String categoryName);
}
