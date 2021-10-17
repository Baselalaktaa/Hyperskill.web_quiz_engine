package engine.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Quiz {
    @Id
    @NotNull
    @Column(name = "QUIZ_ID")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;

    @NotBlank
    @Column
    private String title;


    @NotBlank
    @Column
    private String text;



    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ElementCollection
    @CollectionTable
    @Size(min = 2)
    @NotEmpty
    @Size.List({})
    private List<String> options;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @ElementCollection
    @CollectionTable
    private List<Integer> answer;


    @ManyToOne
    @JoinColumn(name = "UserID")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;





    public Quiz () {
        if (answer == null){
            answer = new ArrayList<>();
        }
    }

    public Quiz (String title , String text , ArrayList<String> options, ArrayList<Integer> answer){
        this.title = title;
        this.answer = answer;
        this.text = text;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    @JsonProperty
    public List<String> getOptions() {
        return options;
    }
    @JsonProperty
    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }
    @JsonProperty
    public List<Integer> getAnswer() {
        return answer;
    }

    @JsonProperty
    public void setAnswer(ArrayList<Integer> answer) {
        this.answer = answer;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }
    @JsonProperty
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
