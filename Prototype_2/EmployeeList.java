
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeList {

    // "head" if the linked list's head & "loaded" indicates if the user loaded the Employees
    private Employee head;
    private boolean loaded;

    public EmployeeList() {
        head = null;
        this.loaded = false;
    }

    public boolean isEmpty() {
        return head == null;
    }

    // This method reads the Employees off of a file
    public Employee loadEmployees() throws FileNotFoundException {
        File input = new File("Employees.txt");
        Scanner read = new Scanner(input);

        while (read.hasNext()) {
            head = addEmployee(head, read.nextInt(), read.next(), read.next().charAt(0), read.next());

        }
        // Since the user read from a file, loaded is set to "true"
        loaded = true;
        return head;
    }

    // The next methods, except the last, are standard for linked lists
    public void addEmployee(int id, String name, char gender, String job_title) throws FileNotFoundException, IOException {
        if (head == null) {
            id = 100;
        } else {
            Employee helpPtr = head;
            while (helpPtr.getNext() != null) {
                helpPtr = helpPtr.getNext();
            }
            id = helpPtr.getId() + 1;
        }

        // Prompt for password and add to passwd file
        try {
            Scanner inputScanner = new Scanner(System.in);
            System.out.print("Enter password for " + name + " with ID " + id + ": ");
            String password = inputScanner.nextLine();
            if (password.matches(".*\\s+.*")) {
                throw new IOException();
            }
            String encryptedPassword = encryptPassword(password);

            FileWriter passwdWriter = new FileWriter("passwd.txt", true);
            passwdWriter.write(id + " " + encryptedPassword + "\n");
            passwdWriter.close();

            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Employee with ID " + id + " added successfully.");
        } catch (IOException e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Error adding employee's password.");
            return;
        }

        head = addEmployee(head, id, name, gender, job_title);
        if (loaded) {
            updateEmployeeList();
        }
    }

    // Adding a new Employee
    private Employee addEmployee(Employee head, int id, String name, char gender, String job_title) {
        if (head == null) {
            head = new Employee(id, name, gender, job_title, head);
            return head;
        } else {
            Employee helpPtr = head;
            while (helpPtr.getNext() != null) {
                helpPtr = helpPtr.getNext();
            }
            Employee newNode = new Employee(id, name, gender, job_title, helpPtr.getNext());
            helpPtr.setNext(newNode);
        }
        return head;
    }

    // Delete Employee by ID
    public void deleteEmployeeByID(int id) throws FileNotFoundException {
        head = deleteEmployeeByID(head, id);
        if (loaded) {
            updateEmployeeList();
        }
    }

    private Employee deleteEmployeeByID(Employee head, int id) throws FileNotFoundException {
        if (!isEmpty()) {
            if (head.getId() == id) {
                deletePasswordByID(id);
                head = head.getNext();
            } else {
                Employee helpPtr = head;
                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getId() == id) {
                        deletePasswordByID(id);
                        System.out.println("Employee ID " + id + " Was Deleted.");
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break;
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
        }
        return head;
    }

    // Display all Employees
    public void displayAllEmployees() {
        displayAllEmployees(head);
    }

    private void displayAllEmployees(Employee head) {
        Employee helpPtr = head;
        System.out.println("Employee ID\tName\t\tGender\tJob Title");
        while (helpPtr != null) {
            String jobTitle = helpPtr.getJob_title();
            String[] jobParts = jobTitle.split("_"); // Split job title by underscore

            StringBuilder formattedJobTitle = new StringBuilder();
            for (String part : jobParts) {
                formattedJobTitle.append(part).append(" ");
            }

            String name = helpPtr.getName();
            String[] nameParts = name.split("_"); // Split name by underscore

            StringBuilder formattedname = new StringBuilder();
            for (String part : nameParts) {
                formattedname.append(part).append(" ");
            }

            System.out.println(helpPtr.getId() + "\t\t" + formattedname + "\t\t\t" + helpPtr.getGender() + "\t" + formattedJobTitle.toString());
            helpPtr = helpPtr.getNext();
        }
        System.out.println(".......................................................................");
    }

    // This method updates the Employee file
    public void updateEmployeeList() throws FileNotFoundException {
        File output = new File("Employees.txt");
        PrintWriter out = new PrintWriter(output);
        Employee helpPtr = head;
        while (helpPtr != null) {
            out.println(helpPtr.getId() + " " + helpPtr.getName() + " " + helpPtr.getGender() + " " + helpPtr.getJob_title());
            helpPtr = helpPtr.getNext();
        }
        out.flush();
        out.close();
    }

    // Log in method
    public boolean employeeLogin(int id, String password) throws FileNotFoundException {
        File passwdFile = new File("passwd.txt");
        Scanner scanner = new Scanner(passwdFile);

        while (scanner.hasNext()) {
            int storedId = scanner.nextInt();
            String storedPassword = scanner.next();
            String decryptedPassword = decryptPassword(storedPassword);

            if (id == storedId && password.equals(decryptedPassword)) {
                System.out.println("Login successful!");
                return true;
            }
        }

        System.out.println("Login failed. Incorrect ID or password.");
        return false;
    }

    // Password encryption
    private String encryptPassword(String password) {
        StringBuilder encryptedPassword = new StringBuilder();
        for (char c : password.toCharArray()) {
            int shiftedAscii = c + 15; // Shift ASCII value by 15
            encryptedPassword.append((char) shiftedAscii);
        }
        return encryptedPassword.toString();
    }

    // password decryption
    private String decryptPassword(String encryptedPassword) {
        StringBuilder decryptedPassword = new StringBuilder();
        for (char c : encryptedPassword.toCharArray()) {
            int shiftedAscii = c - 15; // Revert the ASCII shift
            decryptedPassword.append((char) shiftedAscii);
        }
        return decryptedPassword.toString();
    }

    // delete Employee's password after being removed
    private void deletePasswordByID(int id) throws FileNotFoundException {
//        try {
//            File passwdFile = new File("passwd.txt");
//            File tempFile = new File("passwd_temp.txt");
//
//            Scanner scanner = new Scanner(passwdFile);
//            FileWriter writer = new FileWriter(tempFile);
//
//            while (scanner.hasNext()) {
//                String line = scanner.nextLine();
//                String[] parts = line.split(" ");
//                int storedId = Integer.parseInt(parts[0]);
//
//                if (storedId != id) {
//                    writer.write(line + "\n");
//                }
//            }
//
//            writer.close();
//            scanner.close();
//            passwdFile.delete();
//            tempFile.renameTo(passwdFile);
//        } catch (IOException e) {
//            System.out.println("Something went wrong!");
//        }

        try {
            ArrayList<String> oldPasswords = new ArrayList<>();
            File passwdFile = new File("passwd.txt");
            Scanner scanner = new Scanner(passwdFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" ");
                int storedId = Integer.parseInt(parts[0]);

                if (storedId != id) {
                    oldPasswords.add(line); // Save non-deleted passwords
                }
            }

            scanner.close();
            passwdFile.delete();

            FileWriter writer = new FileWriter(passwdFile);
            for (String password : oldPasswords) {
                writer.write(password + "\n"); // Write back non-deleted passwords
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }

}
