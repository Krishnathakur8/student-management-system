import java.util.*;
import java.io.*;

public class Main {

    static void saveToFile(ArrayList<Student> list) {
        try {
            FileWriter fw = new FileWriter("students.txt");
            for(Student s : list) {
                fw.write(s.id + "," + s.name + "," + s.marks + "\n");
            }
            fw.close();
        } catch(Exception e) {
            System.out.println("Error saving file");
        }
    }

    static void loadFromFile(ArrayList<Student> list) {
        try {
            File file = new File("students.txt");
            if(!file.exists()) return;

            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",");

                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int marks = Integer.parseInt(parts[2]);

                list.add(new Student(id, name, marks));
            }
            sc.close();
        } catch(Exception e) {
            System.out.println("Error loading file");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Student> list = new ArrayList<>();

        loadFromFile(list);  

        while(true) {
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Delete Student");
            System.out.println("4. Search Student");
            System.out.println("5. Update Marks");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            if(choice == 1) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Marks: ");
                int marks = sc.nextInt();

                list.add(new Student(id, name, marks));
                saveToFile(list);
            }
            else if(choice == 2) {
                for(Student s : list) {
                    System.out.println(s.id + " " + s.name + " " + s.marks);
                }
            }
            else if(choice == 3) {
                System.out.print("Enter ID to delete: ");
                int id = sc.nextInt();

                list.removeIf(s -> s.id == id);
                saveToFile(list);
            }
            else if(choice == 4) {
                System.out.print("Enter ID to search: ");
                int id = sc.nextInt();

                for(Student s : list) {
                    if(s.id == id) {
                        System.out.println(s.id + " " + s.name + " " + s.marks);
                    }
                }
            }
            else if(choice == 5) {
                System.out.print("Enter ID to update: ");
                int id = sc.nextInt();

                for(Student s : list) {
                    if(s.id == id) {
                        System.out.print("Enter new marks: ");
                        s.marks = sc.nextInt();
                        saveToFile(list);
                    }
                }
            }
            else if(choice == 6) {
                break;
            }
        }
    }
}
