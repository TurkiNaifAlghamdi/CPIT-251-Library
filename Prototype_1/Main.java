
import java.io.FileNotFoundException;
import java.util.Scanner;
//import java.util.concurrent.TimeUnit;

public class Main {

    public static void testMain() throws FileNotFoundException {
//        EmployeeList list = new EmployeeList();
//        
//        list.displayAllEmployees();
//        list.addEmployee(0, "name", 'm', "job");
//        list.displayAllEmployees();

        Inventory inventory = new Inventory();
        inventory.loadBooks();
        inventory.displayAllBooks();

        inventory.increaseStock("Software Development", 100);
//        System.out.println("\n\n");
//        inventory.addBook("Introduction to IT: A beginner's guide", "T. Alghamdi", 2023, 111113, "Jarir Bookstore", 50, 25, 987654321);
//        inventory.displayAllBooks();
    }

    public static void customerMenu(Scanner input, String clear) throws FileNotFoundException {
        System.out.println("1. Purchase Book\n2. Search Book");
        int user_choice = input.nextInt();
        Inventory inventory = new Inventory();
        inventory.loadBooks();
        System.out.println(clear);
        switch (user_choice) {
            case 1:
                input.nextLine();
                System.out.println("Please Enter the Title of the Book:");
                String title = input.nextLine();

                System.out.print("\nPlease Enter the Quantity: ");
                int quantity = input.nextInt();

                inventory.decreaseStock(title, quantity);
                break;

            case 2:
                input.nextLine();
                System.out.println("Please Enter the Title of the Book:");
                title = input.nextLine();
                System.out.println(inventory.searchBook(title).toString());
                break;
                
            case 0:
                homeMenu();
                break;

            default:
                System.out.println("Please enter a valid option!");
                customerMenu(input, clear);
        }
    }

    public static void employeeMenu(Scanner input, String clear) throws FileNotFoundException {
        System.out.println("1. Manage Employees\n2. Manage Inventory");
        int user_choice = input.nextInt();
        System.out.println(clear);

        switch (user_choice) {
            case 1:
                System.out.println("1. Add Employee\n2. Remove Employee\n3. View Employees");
                user_choice = input.nextInt();
                EmployeeList employees = new EmployeeList();
                employees.loadEmployees();
                System.out.println(clear);

                switch (user_choice) {
                    case 1:
                        System.out.print("Please enter the employee's first name: ");
                        String name = input.next();
                        System.out.print("please enter the employee's gender: ");
                        char gender = input.next().charAt(0);
                        System.out.print("Please enter the employee's job title in one word: ");
                        String job_title = input.next();

                        employees.addEmployee(0, name, gender, job_title);
                        break;
                    case 2:
                        System.out.print("Please enter the ID of the employee to be removed: ");
                        int id = input.nextInt();
                        employees.deleteEmployeeByID(id);
                        break;
                    case 3:
                        employees.displayAllEmployees();
                        break;
                    default:
                        System.out.println("Please enter a valid option!");
                        employeeMenu(input, clear);
                }
                break;

            case 2:
                System.out.println("1. Add Book\n2. Remove Book\n3. Search Book\n4. View All Books\n5. Increase Stock\n6. View Total Stock\n7. View Daily Sales");
                user_choice = input.nextInt();
                Inventory inventory = new Inventory();
                inventory.loadBooks();
                System.out.println(clear);

                switch (user_choice) {
                    case 1:
                        input.nextLine();
                        System.out.println("Please Enter the Title of the Book:");
                        String title = input.nextLine();

                        System.out.println("Please enter the Author's Name:");
                        String author = input.nextLine();

                        System.out.print("Please Enter the Year of Publication: ");
                        int year = input.nextInt();

                        System.out.print("What is the Location Number of the Book: ");
                        int location = input.nextInt();
                        input.nextLine();

                        System.out.println("Please Enter the Name of the Publisher:");
                        String publisher = input.nextLine();

                        System.out.print("Please Enter the Current Stock: ");
                        int stock = input.nextInt();

                        System.out.print("Please Enter the Price of the Book: ");
                        double price = input.nextDouble();

                        System.out.println("Please Enter the Book's ISBN: ");
                        long isbn = input.nextLong();

                        inventory.addBook(title, author, year, location, publisher, stock, price, isbn);
                        break;
                    case 2:
                        input.nextLine();
                        System.out.println("Please Enter the Title of the Book:");
                        title = input.nextLine();
                        inventory.deleteBookByTitle(title);
                        break;
                    case 3:
                        input.nextLine();
                        System.out.println("Please Enter the Title of the Book:");
                        title = input.nextLine();
                        System.out.println(inventory.searchBook(title).toString());
                        break;
                    case 4:
                        inventory.displayAllBooks();
                        break;
                    case 5:
                        input.nextLine();
                        System.out.println("Please Enter the Title of the Book:");
                        title = input.nextLine();
                        System.out.print("Please Enter the Quantity: ");
                        int quantity = input.nextInt();
                        inventory.increaseStock(title, quantity);
                        break;
                    case 6:
                        System.out.println("The Inventory currently has a Stock of " + inventory.getTotalInventoryCount());
                        break;
                    case 7:
                        System.out.println("Sale Revenue Made Today: " + inventory.getSales());
                        break;
                    default:
                        System.out.println("Please enter a valid option!");
                        employeeMenu(input, clear);

                }
                break;

            default:
                System.out.println("Please enter a valid option!");
                homeMenu();
        }
    }
    
    public static void homeMenu() throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        System.out.println("1. Customer\n2. Employee");
        int user_choice = input.nextInt();
        String clear = "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
        System.out.println(clear);
        switch (user_choice) {
            case 1:
                customerMenu(input, clear);
                homeMenu();
                break;

            case 2:
                employeeMenu(input, clear);
                homeMenu();
                break;

            default:
                break;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        homeMenu();

    }

}
