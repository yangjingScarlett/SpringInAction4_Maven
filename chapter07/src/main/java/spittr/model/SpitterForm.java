package spittr.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by yangjing on 2018/1/17
 */
@Getter
@Setter
public class SpitterForm implements Serializable {

    @NotNull
    @Size(min = 5, max = 16, message = "{username.size}")
    private String username;

    @NotNull
    @Size(min = 5, max = 25, message = "{password.size}")
    private String password;

    @NotNull
    @Size(min = 2, max = 30, message = "{firstName.size}")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30, message = "{lastName.size}")
    private String lastName;

    @NotNull
    @Email
    private String email;

    private MultipartFile profilePicture;

    public Spitter toSpitter() {
        return new Spitter(username, password, firstName, lastName, email);
    }

}
