import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
    public void addEmployee(int id, String name, char gender, String job_title) throws FileNotFoundException {
        head = addEmployee(head, id, name, gender, job_title);
        if (loaded) {
            updateEmployeeList();
        }
    }

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

    public void deleteEmployeeByID(int id) throws FileNotFoundException {
        head = deleteEmployeeByID(head, id);
        if (loaded) {
            updateEmployeeList();
        }
    }

    private Employee deleteEmployeeByID(Employee head, int id) {
        if (!isEmpty()) {
            if (head.getId() == id) {
                head = head.getNext();
            } else {
                Employee helpPtr = head;
                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getId() == id) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break;
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
        }
        return head;
    }

    public void displayAllEmployees() {
        displayAllEmployees(head);
    }

    private void displayAllEmployees(Employee head) {
        Employee helpPtr = head;
        System.out.println("Employee ID\tName\tGender\tJob Title");
        while (helpPtr != null) {
            System.out.println(helpPtr.getId() + "\t" + helpPtr.getName() + "\t" + helpPtr.getGender() + "\t" + helpPtr.getJob_title());
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
}
