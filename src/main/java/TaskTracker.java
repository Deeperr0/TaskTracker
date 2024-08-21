import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;

public class TaskTracker {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to TaskTracker!");
        System.out.println("You can start by using \"task-cli\" to explore the available commands:");
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            taskList = objectMapper.readValue(new File("taskList.json"), new TypeReference<ArrayList<Task>>(){});
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        while(true) {
            String userInput = input.nextLine();
            if(userInput.equals("exit")) {
                try {
                    objectMapper.writeValue(new File("taskList.json"), taskList);
                    break;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else if (userInput.equals("task-cli") || userInput.startsWith("task-cli --help")) {
                printUsage();
            } else if (userInput.startsWith("task-cli add")) {
                int taskId;
                if(taskList.size() == 0)
                {
                    taskId = 1;
                }
                else {
                    taskId = taskList.getLast().getId() + 1;
                }
                String taskDescription = userInput.split("\"")[1];
                Task newTask = new Task(taskId, taskDescription, 0, LocalDate.now(), LocalDate.now());
                taskList.add(newTask);
            }
            else if (userInput.startsWith("task-cli delete")) {
                int taskId = Integer.parseInt(userInput.split(" ")[2]);
                taskList.removeIf(task -> task.getId() == taskId);
            }
            else if(userInput.startsWith("task-cli list")) {
                if(userInput.split(" ").length > 2) {
                    String filter = userInput.split(" ")[2];
                    int filterAsInt = switch(filter)
                    {
                        case "todo" -> 0;
                        case "in-progress" -> 1;
                        case "done" -> 2;
                        default -> 3;
                    };
                    if(filterAsInt == 3){
                        for(Task task : taskList) {
                            task.print();
                        }
                    }
                    for(Task task : taskList) {
                        if(task.getStatus() == filterAsInt)
                        {
                            task.print();
                        }
                    }
                }
                else {
                    for(Task task : taskList) {
                        task.print();
                    }
                }
            }
            else if(userInput.startsWith("task-cli update")) {
                int taskId = Integer.parseInt(userInput.split(" ")[2]);
                String taskDescription = userInput.split("\"")[1];
                for(Task task : taskList) {
                    if(task.getId() == taskId) {
                        task.setDescription(taskDescription);
                    }
                }

            }
            else if (userInput.startsWith("task-cli mark-in-progress")){
                int taskId = Integer.parseInt(userInput.split(" ")[2]);
                for(Task task : taskList) {
                    if(task.getId() == taskId) {
                        task.setStatus(1);
                    }
                }
            }
            else if (userInput.startsWith("task-cli mark-done")){
                int taskId = Integer.parseInt(userInput.split(" ")[2]);
                for(Task task : taskList) {
                    if(task.getId() == taskId) {
                        task.setStatus(2);
                    }
                }
            }
        }
        input.close();
        System.exit(0);
    }
    private static void printUsage() {
        System.out.println("Usage: task-cli <command> [<args>]");
        System.out.println("Commands:");
        System.out.println("\tadd <task_description>              Add a new task with the given description");
        System.out.println("\tupdate <task_id> <new_description>  Update the task with the given ID");
        System.out.println("\tdelete <task_id>                    Delete the task with the given ID");
        System.out.println("\tlist [status]                       List all tasks or filter by status (todo, in-progress, done)");
        System.out.println("\tmark-in-progress <task_id>          Mark the task with the given ID as 'in-progress'");
        System.out.println("\tmark-done <task_id>                 Mark the task with the given ID as 'done'");
        System.out.println();
        System.out.println("Options:");
        System.out.println("\t--help                              Display this help text");
    }
}
