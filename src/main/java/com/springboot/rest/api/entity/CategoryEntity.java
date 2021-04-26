package com.springboot.rest.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "CategoryEntity")
@Table(name = "categories")
public class CategoryEntity implements Serializable {
    private static final long serialVersionUID = 8088000463570195276L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @Column(nullable = false)
    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<ProductEntity> products;

}
