
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;


public class Main {

    public static void testMain() throws FileNotFoundException, IOException {
        EmployeeList list = new EmployeeList();

        list.loadEmployees();
        list.addEmployee(0, "Abdullah", 'M', "Security");
//        list.employeeLogin(105, "please_work");
//        list.deleteEmployeeByID(105);
        list.displayAllEmployees();

    }

    // this method is called when the customer uses the system
    public static void customerMenu(Scanner input, String clear) throws FileNotFoundException, IOException {
        System.out.println("1. Purchase Book\n2. Search Book\n0. Back");
        int user_choice = input.nextInt();
        Inventory inventory = new Inventory();
        inventory.loadBooks();
        System.out.println(clear);
        
        switch (user_choice) {
            // Back Button
            case 0:
                break;
            
            // Purchase Book
            case 1:
                input.nextLine();
                System.out.print("Please Enter the Title of the Book:");
                String title = input.nextLine();

                System.out.print("\nPlease Enter the Quantity: ");
                int quantity = input.nextInt();

                inventory.decreaseStock(title, quantity);
                customerMenu(input, clear);
                break;

            // Search for a Book
            case 2:
                input.nextLine();
                System.out.print("Please Enter the Title of the Book:");
                title = input.nextLine();
                Book searched_book = inventory.searchBook(title);
                if (searched_book != null) {
                    System.out.println(searched_book.toString());
                } else {
                    System.out.println("'" + title + "' is not available");
                }
                customerMenu(input, clear);
                break;

            // If the input is invalid
            default:
                System.out.println("Please enter a valid option!");
                customerMenu(input, clear);
        }
    }

    // This is the method that runs when the employee uses the system
    public static void employeeMenu(Scanner input, String clear) throws FileNotFoundException, IOException {
        System.out.println("1. Manage Employees\n2. Manage Inventory\n0. Back");
        int user_choice = input.nextInt();
        System.out.println(clear);

        switch (user_choice) {
            // Back Button
            case 0:
                break;

            // Managing Employees
            case 1:
                System.out.println("1. Add Employee\n2. Remove Employee\n3. View Employees\n0. Back");
                user_choice = input.nextInt();
                EmployeeList employees = new EmployeeList();
                employees.loadEmployees();
                System.out.println(clear);

                switch (user_choice) {
                    // Back Button
                    case 0:
                        customerMenu(input, clear);
                        break;

                    // Adding an Employee
                    case 1:
                        input.nextLine();
                        System.out.print("Please enter the employee's name: ");
                        String name = input.nextLine();
                        name = name.replace(" ", "_");

                        System.out.print("please enter the employee's gender (M/F): ");
                        char gender = input.next().toUpperCase().charAt(0);

                        input.nextLine();
                        System.out.print("Please enter the employee's job title: ");
                        String job_title = input.nextLine();
                        job_title = job_title.replace(" ", "_");

                        employees.addEmployee(0, name, gender, job_title);
                        break;
                    // Remove an Employee
                    case 2:
                        System.out.print("Please enter the ID of the employee to be removed: ");
                        int id = input.nextInt();
                        employees.deleteEmployeeByID(id);
                        break;
                    // View all Employees
                    case 3:
                        employees.displayAllEmployees();
                        break;
                    // In case of an invalid input
                    default:
                        System.out.println("Please enter a valid option!");

                }
                // Recusivley cal the menu Method
                employeeMenu(input, clear);
                break;

            // Managing the Inventory
            case 2:
                System.out.println("1. Add Book\n2. Remove Book\n3. Search Book\n4. View All Books\n5. Increase Stock\n6. View Total Stock\n7. View Daily Sales\n0. Back");
                user_choice = input.nextInt();
                Inventory inventory = new Inventory();
                inventory.loadBooks();
                System.out.println(clear);

                switch (user_choice) {
                    // Back button
                    case 0:
                        customerMenu(input, clear);
                        break;

                    // Adding a Book
                    case 1:
                        input.nextLine();
                        System.out.print("Please Enter the Title of the Book: ");
                        String title = input.nextLine();

                        System.out.print("Please enter the Author's Name: ");
                        String author = input.nextLine();

                        System.out.print("Please Enter the Year of Publication: ");
                        int year = input.nextInt();

                        System.out.print("What is the Location Number of the Book: ");
                        int location = input.nextInt();
                        input.nextLine();

                        System.out.print("Please Enter the Name of the Publisher: ");
                        String publisher = input.nextLine();

                        System.out.print("Please Enter the Current Stock: ");
                        int stock = input.nextInt();

                        System.out.print("Please Enter the Price of the Book: ");
                        double price = input.nextDouble();

                        System.out.print("Please Enter the Book's ISBN: ");
                        long isbn = input.nextLong();

                        inventory.addBook(title, author, year, location, publisher, stock, price, isbn);
                        break;
                    // Removing a Book
                    case 2:
                        input.nextLine();
                        System.out.print("Please Enter the Title of the Book: ");
                        title = input.nextLine();
                        inventory.deleteBookByTitle(title);
                        break;
                    // Searching for a book
                    case 3:
                        input.nextLine();
                        System.out.print("Please Enter the Title of the Book: ");
                        title = input.nextLine();
                        Book searched_book = inventory.searchBook(title);
                        if (searched_book != null) {
                            System.out.println(searched_book.toString());
                        } else {
                            System.out.println("'" + title + "' is not available");
                        }
                        break;
                    // Display all the Books
                    case 4:
                        inventory.displayAllBooks();
                        break;
                    // Increase Stock
                    case 5:
                        input.nextLine();
                        System.out.print("Please Enter the Title of the Book: ");
                        title = input.nextLine();
                        System.out.print("Please Enter the Quantity: ");
                        int quantity = input.nextInt();
                        inventory.increaseStock(title, quantity);
                        break;
                    // Total Stock
                    case 6:
                        System.out.println("The Inventory currently has a Stock of " + inventory.getTotalInventoryCount() + " Books.");
                        break;
                    // Daily Sales
                    case 7:
                        System.out.println("Sale Revenue Made Today: " + inventory.getSales());
                        break;
                    // In case the input is invalid
                    default:
                        System.out.println("Please enter a valid option!");

                }
                // Recursivley call the menu
                employeeMenu(input, clear);
                break;
            // in case the input is invalid
            default:
                System.out.println("Please enter a valid option!");
                employeeMenu(input, clear);
        }
    }

    public static void homeMenu() throws FileNotFoundException, IOException {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Customer\n2. Employee\n0. Exit");
        int user_choice = input.nextInt();
        String clear = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        System.out.println(clear);
        switch (user_choice) {
            // Exit Program
            case 0:
                break;

            // Customer's Menu
            case 1:
                customerMenu(input, clear);
                homeMenu();
                break;

            // Employee's Menu
            case 2:
                // Log in
                EmployeeList login_checker = new EmployeeList();
                System.out.print("Please enter your Employee ID: ");
                int id = input.nextInt();

                input.nextLine();
                System.out.print("Please enter your password: ");
                String password = input.nextLine();

                System.out.println(clear);
                if (login_checker.employeeLogin(id, password)) {
                    employeeMenu(input, clear);
                }

                homeMenu();
                break;

            // In case of an invalid input
            default:
                System.out.println("Please enter a valid option!");
                homeMenu();
                break;
        }
    }

    // Main method
    public static void main(String[] args) throws FileNotFoundException, IOException {
        // Begins the program
        homeMenu();
        // ID: 106 ; Password: admin
    }

}
