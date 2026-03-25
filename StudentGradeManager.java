import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentGradeManager {
    static class Student{ 
        String name;
        int grade;
        int Id;

    }
    static class StudentGrader{
        ArrayList<Student> students = new ArrayList<>();

        void addStudent (String name, int grade, int Id){
            Student NewStudent = new Student();
            NewStudent.Id = Id;
            NewStudent.name = name;
            NewStudent.grade = grade;
            students.add(NewStudent);
            System.out.println("Student added " +  name);

        }
        void viewStudents (){
            if (students.isEmpty()) {
                System.out.println("No student found.");
                return;
            }
            for (Student s : students) {
                System.out.println("Name of student " + s.Id + ": " + s.name +" and grade " + s.grade);
        }
    }
        boolean changeGrade(int id, int change){
            for (Student s : students) {
                if (s.Id == id) {
                    s.grade  = change;
                    System.out.println("Grade successfully changed new grade is: " + s.grade);
                    return true;
                }
            }
            System.out.println("Task ID not found");
            return false;
        }
        boolean deleteStudent(int id){
            for(int i = 0; i < students.size(); i++){
                if(students.get(i).Id == id){
                    students.remove(i);
                    System.out.println("Student " + id + "deleted");
                    return true;
                }
            }
            System.out.println("Task ID not found");
            return false;
        }
        void averageGrade(){
            int sum = 0;
            int count = 0;
            for(Student s : students){
                sum += s.grade;
                count++;
            }
            if(count != 0){
                int avarage = sum / count;
                System.out.println("Avarage grade of students are: " + avarage);
            }
            else{
                System.out.println("We do not have students");
            }
            
        }
    }
    public static void saveStudents(ArrayList<Student> students){
        try(PrintWriter writer = new PrintWriter("students.txt")){
            for(Student s : students){
                writer.println(s.Id + ";" + s.name + ";"+ s.grade);
            }
        } catch (FileNotFoundException e){
            System.out.println("Error saving Student grades: " + e.getMessage());
        }
    }
    
    public static ArrayList<Student> loadSGM(){
        ArrayList<Student> students =  new ArrayList<>();
        File file = new File("students.txt");
        if(!file.exists()) return students;

        try(Scanner fileScanner = new Scanner(file)){
            while(fileScanner.hasNextLine()){
                String line = fileScanner.nextLine();
                String[] parts = line.split(";");
                if(parts.length == 3){
                    Student s = new Student();
                    s.Id = Integer.parseInt(parts[0]);
                    s.name = parts[1];
                    s.grade = Integer.parseInt(parts[2]);
                    students.add(s);
                }
            }
        }catch(FileNotFoundException e){
            System.out.println("Error loading Student's Grades " + e.getMessage());
        }
        return students;
    }

    public static void main(String[] args){
        int NextId = 0;
        int Choice;
        StudentGrader manager = new StudentGrader();
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println("StudentGradeManager");
            System.out.println("--------------");
            System.out.println("1 Add Student");
            System.out.println("2 View Students");
            System.out.println("3 Update Grade");
            System.out.println("4 Delete Student");
            System.out.println("5 Average Grade");
            System.out.println("6 Exit");

            Choice = scanner.nextInt();

            if (Choice < 1 || Choice > 6) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }
            if(Choice == 1){
                System.out.println("Enter the informations of student \n(first name and then grade)");
                scanner.nextLine();
                String title = scanner.nextLine();
                while(true){
                    int Grade = scanner.nextInt();
                    if(Grade < 0 ||  100 < Grade ){
                        System.out.println("This number cannot be assigned");
                    }
                    else{
                        manager.addStudent(title, Grade, NextId);
                        NextId++;
                        break;
                    }
                }
                saveStudents(manager.students);
            }
            else if(Choice == 2){
                manager.viewStudents();

            }
            else if(Choice == 3){
                System.out.print("Enter Student ID to change grade: ");
                int id = scanner.nextInt();
                System.out.println("Enter the new grade of student");
                int change = -1;
                while(true){
                    int temp = scanner.nextInt();
                    if(temp < 0 || temp > 100){
                        System.out.println("This number cannot be assigned");
                    }
                    else{
                        change = temp;
                        break;
                    }
                }
                manager.changeGrade(id, change);
                saveStudents(manager.students);

            }
            else if(Choice == 4){
                System.out.println("Enter Student ID to delete");
                int id = scanner.nextInt();
                manager.deleteStudent(id);
                saveStudents(manager.students);
            }
            else if(Choice == 5){
                manager.averageGrade();
            }
            else if(Choice == 6){
                saveStudents(manager.students);
                System.out.println("Operation ended. Goodbye!");
                break;

            }
        }
        
        scanner.close();
    }
}

