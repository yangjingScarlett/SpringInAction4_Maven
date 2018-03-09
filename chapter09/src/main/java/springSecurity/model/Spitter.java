package springSecurity.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by yangjing on 2018/2/12
 */
@Getter
@Setter//如果没有setter的话注册界面会获取不到前端的数据
public class Spitter implements Serializable {

    private Long id;

    @NotNull
    @Size(min = 5, max = 16, message = "username.size")
    private String username;

    @NotNull
    @Size(min = 5, max = 25, message = "password.size")
    private String password;

    @NotNull
    @Size(min = 2, max = 30, message = "firstName.size")
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30, message = "lastName.size")
    private String lastName;

    @NotNull
    @Email
    private String email;

    public Spitter() {
    }

    public Spitter(String username, String password, String firstName, String lastName, String email) {
        this(null, username, password, firstName, lastName, email);
    }

    public Spitter(Long id, String username, String password, String firstName, String lastName, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

}
