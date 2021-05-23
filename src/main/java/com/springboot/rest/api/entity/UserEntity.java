package com.springboot.rest.api.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@Entity(name = "UserEntity")
@Table(name = "users")
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1754022545324503836L;

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(columnDefinition = "CHAR(32)")
    private String id;

    @Column(nullable = false, length = 20)
    private String firstName;

    @Column(nullable = false, length = 20)
    private String lastName;

    @Column(nullable = false, length = 20, unique = true)
    private String email;

    @Column(nullable = false, length = 60)
    private String encryptedPassword;


    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;


}
