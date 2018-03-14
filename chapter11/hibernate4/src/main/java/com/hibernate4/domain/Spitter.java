package com.hibernate4.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by yangjing on 2018/3/14
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Spitter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "updateByEmail")
    private boolean updateByEmail;

}
