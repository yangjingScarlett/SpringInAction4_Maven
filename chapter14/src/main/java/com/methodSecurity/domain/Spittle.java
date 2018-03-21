package com.methodSecurity.domain;

import java.util.Date;

/**
 * Created by yangjing on 2018/3/19
 */
public class Spittle {

    private final Long id;
    private final String text;
    private final Date postedTime;
    private Spitter spitter;

    public Spittle(Long id, String text, Date postedTime, Spitter spitter) {
        this.id = id;
        this.text = text;
        this.postedTime = postedTime;
        this.spitter = spitter;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getPostedTime() {
        return postedTime;
    }

    public Spitter getSpitter() {
        return spitter;
    }

}
