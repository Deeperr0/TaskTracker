import java.time.LocalDate;

public class Task {
    private final int id;
    private String description;
    private int status; // 0- "todo", 1- "in-progress", 2-"done"
    private final LocalDate createdAt;
    private LocalDate updatedAt;
    public Task(int id, String description, int status, LocalDate createdAt, LocalDate updatedAt) {
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
