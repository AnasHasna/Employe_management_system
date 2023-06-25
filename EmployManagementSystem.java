import java.util.*;
import java.io.*;

public class EmployManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployManagementSystem ems = new EmployManagementSystem();
        int id = ems.getId();
        MainMenu m = new MainMenu();
        m.menu();
        int choice = 0;
        while (choice < 6) {
            System.out.print("\nPlease Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consommer le caractère de nouvelle ligne résiduel
            switch (choice) {
                case 1: {
                    addEmployee a = new addEmployee();
                    a.createFile(id);
                    m.menu();
                    break;
                }
                case 2: {
                    showEmploye s = new showEmploye();
                    try {
                        s.show();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    System.out.print("\nPress Enter to Continue...");
                    scanner.nextLine();
                    m.menu();
                    break;
                }
                case 3: {
                    deleteEmploy d = new deleteEmploy();
                    try {
                        d.delete();
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    System.out.print("\nPress Enter to Continue...");
                    scanner.nextLine();
                    m.menu();
                    break;
                }
                case 4: {
                    updateEmployee u = new updateEmployee();
                    showEmploye s = new showEmploye();
                    try {

                        s.show();

                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    int employeId = s.getId();
                    System.out.println("Please Enter the detail you want to Update:");
                    System.out.println("For Example:");
                    System.out.print(
                            "If you want to Change the Name, then Enter Current Name and Press Enter. Then write the new Name then Press Enter. It will Update the Name.: ");
                    String oldInfo = scanner.next();
                    System.out.print("Please Enter the Updated Info: ");
                    String updateInfo = scanner.next();
                    scanner.nextLine(); // Consommer le caractère de nouvelle ligne résiduel
                    try {
                        u.updateFile(employeId, oldInfo, updateInfo);
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                    System.out.print("\nPress Any Key and Enter to Continue...\n");
                    scanner.nextLine();
                    m.menu();
                    break;
                }
                case 5: {
                    Exit e = new Exit();
                    e.exit();
                    break;
                }
            }
        }
        scanner.close();
    }

    public int getId() {
        Random rand = new Random();
        int employeId = rand.nextInt(1000);
        return employeId;
    }
}

class MainMenu {
    public void menu() {
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t\t  EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("\t\t*******************************************");
        System.out.println("\t\t\t    --------------------");
        System.out.println("\t\t\t     ~$ Anas Hasna");
        System.out.println("\t\t\t    --------------------");
        System.out.println("\n\nPress 1 : To Add an Employee Details");
        System.out.println("Press 2 : To See an Employee Details ");
        System.out.println("Press 3 : To Remove an Employee");
        System.out.println("Press 4 : To Update Employee Details");
        System.out.println("Press 5 : To Exit the EMS Portal");
    }
}

class addEmployee {
    public void createFile(int id) {
        Scanner sc = new Scanner(System.in);
        EmployDetail e = new EmployDetail();
        e.getInfo();
        try {
            File file = new File("employ" + id + ".txt");
            if (file.createNewFile()) {
                FileWriter myWriter = new FileWriter("employ" + id + ".txt");
                myWriter.write("Name: " + e.name + "\n");
                myWriter.write("Father Name: " + e.fatherName + "\n");
                myWriter.write("Email: " + e.email + "\n");
                myWriter.write("Position: " + e.position + "\n");
                myWriter.write("Salary: " + e.salary + "\n");
                myWriter.close();
                System.out.println("Successfully added employee!");
            } else {
                System.out.println("File Problem!");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}

class EmployDetail {
    String name, fatherName, email, position, salary;

    public void getInfo() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter name:");
        name = sc.nextLine();
        System.out.println("Enter father name:");
        fatherName = sc.nextLine();
        System.out.println("Enter email:");
        email = sc.nextLine();
        System.out.println("Enter position:");
        position = sc.nextLine();
        System.out.println("Enter salary:");
        salary = sc.nextLine();
    }
}

class showEmploye {
    int employeId;

    public void show() throws Exception {
        Scanner sc2 = new Scanner(System.in);
        System.out.print("Please Enter the Employee Id: ");
        employeId = sc2.nextInt();
        sc2.nextLine(); // Consommer le caractère de nouvelle ligne résiduel
        File file = new File("employ" + employeId + ".txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        sc.close();
    }

    public int getId() {
        return employeId;
    }
}

class deleteEmploy {
    public void delete() throws Exception {
        Scanner sc2 = new Scanner(System.in);
        int employeId;
        System.out.print("Please Enter the Employee Id: ");
        employeId = sc2.nextInt();
        sc2.nextLine(); // Consommer le caractère de nouvelle ligne résiduel
        File file = new File("employ" + employeId + ".txt");
        if (file.delete()) {
            System.out.println("Employee deleted successfully!");
        } else {
            System.out.println("Employee not found!");
        }
    }
}

class updateEmployee {
    public void updateFile(int employeId, String old, String newOne) throws Exception {
        File file = new File("employ" + employeId + ".txt");
        Scanner fileScanner = new Scanner(file);

        String fileContext = "";
        while (fileScanner.hasNextLine()) {
            fileContext = fileContext.concat(fileScanner.nextLine() + "\n");
        }
        fileScanner.close();
        FileWriter myWriter = new FileWriter("employ" + employeId + ".txt");
        fileContext = fileContext.replaceAll(old, newOne);
        myWriter.write(fileContext);
        myWriter.close();
    }
}

class Exit {
    public void exit() {
        System.out.println("\n*****************************************");
        System.out.println("Thank You For Using my Software :) ");
        System.out.println("*****************************************");
        System.out.println("\t\tAnas Hasna 0923018239\n");
        System.exit(0);
    }
}
