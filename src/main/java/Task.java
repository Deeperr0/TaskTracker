import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Task {
    private final int id;
    private String description;
    private int status; // 0- "todo", 1- "in-progress", 2-"done"
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate createdAt;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate updatedAt;
    public Task(@JsonProperty("id") int id, @JsonProperty("description") String description, @JsonProperty("status") int status, @JsonProperty("createdAt") LocalDate createdAt, @JsonProperty("updatedAt") LocalDate updatedAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public void setDescription(String description) {
        this.description = description;
        this.updatedAt = LocalDate.now();
    }
    public void setStatus(int status) {
        this.status = status;
        this.updatedAt = LocalDate.now();
    }
    public int getStatus(){
        return this.status;
    }
    public int getId() {
        return this.id;
    }
    public String getDescription() {
        return this.description;
    }
    public void print(){
        String printableStatus = switch (this.status) {
            case 0 -> "todo";
            case 1 -> "in-progress";
            case 2 -> "done";
            default -> "";
        };
        System.out.println(id + " " + description + " " + printableStatus + " " + createdAt + " " + updatedAt);
    }
}
