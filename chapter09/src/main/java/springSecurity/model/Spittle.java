package springSecurity.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by yangjing on 2018/2/12
 */
@Getter
@Setter
public class Spittle {

    private Long id;
    private String message;
    private Date createdAt;
    private Double latitude;
    private Double longitude;

    public Spittle(String message, Date createdAt) {
        this(null, message, createdAt, null, null);
    }

    public Spittle(Long id, String message, Date createdAt, Double latitude, Double longitude) {
        this.id = id;
        this.message = message;
        this.createdAt = createdAt;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
