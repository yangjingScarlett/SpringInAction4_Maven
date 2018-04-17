package spittr.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by yangjing on 2018/4/8
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Spitter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FULLNAME")
    private String fullName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "UPDATE_BY_EMAIL")
    private String updateByEamil;

    @Column(name = "STATUS")
    private String status;

    public Spitter(String username, String password, String fullName, String email, String updateByEamil, String status) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.updateByEamil = updateByEamil;
        this.status = status;
    }

}
