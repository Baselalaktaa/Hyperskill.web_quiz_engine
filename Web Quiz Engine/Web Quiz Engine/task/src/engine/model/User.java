package engine.model;


import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
public class User {
    @Id
    @NotNull
    @Column(name = "USER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(unique = true)
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @NotEmpty
    @Size(min = 5)
    private String password;

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }

    @Column
    private String role;

    @JsonProperty
    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    @OneToMany (mappedBy = "user")
    private List<Quiz> quizzes;




    public void setPassword(String password) {
        this.password = password;
    }

    public User() {

    }

    public User( String email, String password, String role) {
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
