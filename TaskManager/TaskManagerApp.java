import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManagerApp {

    static class Task {
        int id;
        String name;
        Boolean completed;
    }

    static class TaskManager {
        ArrayList<Task> tasks = new ArrayList<>();

        void addTask(String name, int id) {
            Task newTask = new Task();
            newTask.id = id;
            newTask.name = name;
            newTask.completed = false;
            tasks.add(newTask);
            System.out.println("Task added: " + name);
        }

        void viewTasks() {
            if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
                return;
            }
            for (Task t : tasks) {
                System.out.println("Task " + t.id + ": " + t.name + " [" + (t.completed ? "Done" : "Not Done") + "]");
            }
        }

        boolean completeTask(int id) {
            for (Task t : tasks) {
                if (t.id == id) {
                    t.completed = true;
                    System.out.println("Task " + id + " marked as completed.");
                    return true;
                }
            }
            System.out.println("Task ID not found.");
            return false;
        }

        boolean deleteTask(int id) {
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).id == id) {
                    tasks.remove(i);
                    System.out.println("Task " + id + " deleted.");
                    return true;
                }
            }
            System.out.println("Task ID not found.");
            return false;
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        try (PrintWriter writer = new PrintWriter("tasks.txt")) {
            for (Task t : tasks) {
                writer.println(t.id + ";" + t.name + ";" + t.completed);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File("tasks.txt");
        if (!file.exists()) return tasks;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    Task t = new Task();
                    t.id = Integer.parseInt(parts[0]);
                    t.name = parts[1];
                    t.completed = Boolean.parseBoolean(parts[2]);
                    tasks.add(t);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    public static void main(String[] args) {
        int nextId = 0;
        TaskManager taskManager = new TaskManager();
        taskManager.tasks = loadTasks();
        if (!taskManager.tasks.isEmpty()) {
            nextId = taskManager.tasks.get(taskManager.tasks.size() - 1).id + 1;
        }

        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        while (true) {
            System.out.println("\nTask Manager");
            System.out.println("------------");
            System.out.println("1 Add Task");
            System.out.println("2 View Tasks");
            System.out.println("3 Complete Task");
            System.out.println("4 Delete Task");
            System.out.println("5 Exit");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();

            if (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            scanner.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter task title: ");
                String title = scanner.nextLine();
                taskManager.addTask(title, nextId);
                nextId++;
                saveTasks(taskManager.tasks);

            } else if (choice == 2) {
                taskManager.viewTasks();

            } else if (choice == 3) {
                System.out.print("Enter task ID to complete: ");
                int id = scanner.nextInt();
                taskManager.completeTask(id);
                saveTasks(taskManager.tasks);

            } else if (choice == 4) {
                System.out.print("Enter task ID to delete: ");
                int id = scanner.nextInt();
                taskManager.deleteTask(id);
                saveTasks(taskManager.tasks);

            } else if (choice == 5) {
                saveTasks(taskManager.tasks);
                System.out.println("Operation ended. Goodbye!");
                break;
            }
        }

        scanner.close();
    }
}