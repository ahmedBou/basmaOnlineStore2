package com.springboot.rest.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "ProductEntity")
@Table(name = "products")
public class ProductEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @Column(nullable = false, length = 25)
    private String productName;

    @Column(nullable = false)
    private String productImages;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
