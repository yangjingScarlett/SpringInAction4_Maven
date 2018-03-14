package com.jpahibernate.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yangjing on 2018/3/14
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Spittle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spitter")
    private Spitter spitter;

    @Column(name = "message")
    private String message;

    @Column(name = "post_time")
    private Date postTime;

}
