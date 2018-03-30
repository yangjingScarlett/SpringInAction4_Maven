package spittr.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by yangjing on 2018/3/26
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spittle {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "SPITTER")
    private Spitter spitter;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "POST_TIME")
    private Date postTime;

    public Spittle(Spitter spitter, String message, Date postTime) {
        this.spitter = spitter;
        this.message = message;
        this.postTime = new Date();
    }

}
