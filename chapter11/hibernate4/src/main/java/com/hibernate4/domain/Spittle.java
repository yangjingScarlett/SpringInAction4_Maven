package com.hibernate4.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yangjing on 2018/3/14
 */
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Spittle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "spitter")
    private Spitter spitter;

    @Column(name = "message")
    private String message;

    @Column(name = "postedTime")
    private Date postedTime;

}
