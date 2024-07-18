import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListManager {
    private static ArrayList<String> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    viewTasks();
                    break;
                case 3:
                    markTaskAsCompleted();
                    break;
                case 4:
                    removeTask();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("incorrect choice. try again?");
            }
        }
        System.out.println("why?");
        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n--- to-do list manager ---");
        System.out.println("1. add a task");
        System.out.println("2. view tasks");
        System.out.println("3. Mark task as resolved");
        System.out.println("4. rm a task");
        System.out.println("5. exit");
        System.out.print("make your choice: ");
    }

    private static int getChoice() {
        return scanner.nextInt();
    }

    private static void addTask() {
        scanner.nextLine(); 
        System.out.print("enter the task: ");
        String task = scanner.nextLine();
        tasks.add(task);
        System.out.println("task added?");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("no tasks in the list. makes sense.");
        }  else {
            System.out.println("current tasks:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    private static void markTaskAsCompleted() {
        viewTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter the number of the task to mark as completed: ");
            int taskNumber = scanner.nextInt();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                String task = tasks.get(taskNumber - 1);
                tasks.set(taskNumber - 1, task + " (Completed)");
                System.out.println("Task marked as completed!");
            } else {
                System.out.println("Invalid task number.");
            }
        }
    }

    private static void removeTask() {
        viewTasks();
        if (!tasks.isEmpty()) {
            System.out.print("Enter the number of the task to remove: ");
            int taskNumber = scanner.nextInt();
            if (taskNumber > 0 && taskNumber <= tasks.size()) {
                tasks.remove(taskNumber - 1);
                System.out.println("Task removed successfully!");
            } else {
                System.out.println("Invalid task number.");
            }
        }
    }
}