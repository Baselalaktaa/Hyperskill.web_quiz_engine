package engine.model;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Component
public class RegisterData {

    @Email(regexp = "[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+")
    private String email;

    @Size(min = 5)
    private String password;


    public RegisterData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public RegisterData () {

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

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * public static void main(String[] args) {
     *         Pattern pattern = Pattern.compile("[a-zA-Z]+@[a-zA-Z]+\\.[a-zA-Z]+");
     *         Matcher matcher = pattern.matcher("well@google.com");
     *         System.out.println(matcher.matches());
     *     }
     * @param args
     */

}
