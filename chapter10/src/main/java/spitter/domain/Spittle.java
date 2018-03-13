package spitter.domain;

import lombok.Getter;

import java.util.Date;

/**
 * Created by yangjing on 2018/3/13
 */
@Getter
public class Spittle {

    private final Long id;
    private final Spitter spitter;
    private final String message;
    private final Date postedTime;

    public Spittle(Long id, Spitter spitter, String message, Date postedTime) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.postedTime = postedTime;
    }

}
