package task.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @OneToMany(mappedBy = "user")
    private List<Task> createdTasks;

 /*   @OneToMany(mappedBy = "recipient")
    private List<SharedTask> recipients;*/

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public List<Task> getCreatedTasks() {
        return createdTasks;
    }


    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getEmail().equals(user.getEmail()) &&
                getPassword().equals(user.getPassword()) &&
                getNickname().equals(user.getNickname()) &&
                Objects.equals(getCreatedTasks(), user.getCreatedTasks());
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getEmail(), getPassword(), getNickname(), getCreatedTasks());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", createdTasks=" + createdTasks +
                //", recipients=" + recipients +
                '}';
    }
}
