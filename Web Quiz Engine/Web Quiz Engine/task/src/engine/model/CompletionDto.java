package engine.model;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class CompletionDto {

    @NotNull
    private int id;

    @NotNull
    private LocalDateTime completedAt = LocalDate.now().atTime(LocalTime.now());

    public CompletionDto (int id , LocalDateTime completedAt){
        this.id = id;
        this.completedAt = completedAt;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
