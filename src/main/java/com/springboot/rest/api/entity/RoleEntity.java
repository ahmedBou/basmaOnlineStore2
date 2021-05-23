package com.springboot.rest.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


@Setter
@Getter
@NoArgsConstructor
@Entity(name = "RoleEntity")
@Table(name = "roles")
public class RoleEntity implements Serializable {
    private static final long serialVersionUID = -5299077814427394790L;

    @Id
    private String id = UUID.randomUUID().toString().replace("-", "").substring(0, 16);

    @Column(nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<UserEntity> users;

}
