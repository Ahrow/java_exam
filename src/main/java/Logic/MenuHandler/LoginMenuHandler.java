package Logic.MenuHandler;

import Services.EntityExistenceService;
import Logic.UserOperations.CreateUser;
import Logic.UserOperations.ValidateUser;
import Services.EntityUpdateService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LoginMenuHandler implements MenuHandler {
    private ValidateUser validateUser;
    String loggedInUser = null;
    public LoginMenuHandler() {
        validateUser = new ValidateUser("userDB");
    }
    @Override
    public int handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> {
                if (loggedInUser == null) {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Enter student name");
                    String studentName = scanner.nextLine();
                    EntityExistenceService studentNameExistenceService = new EntityExistenceService("universityDB");
                    boolean studentExist = studentNameExistenceService.checkIfEntityExist("student", "student_name", studentName);
                    if (studentExist) {
                        System.out.println("Choose username: ");
                        String username = scanner.nextLine();
                        System.out.println("Choose password: ");
                        String password = scanner.nextLine();
                        EntityExistenceService userNameExistenceService = new EntityExistenceService("userDB");
                        userNameExistenceService.checkIfUserExist(username);
                        boolean userExist = userNameExistenceService.checkIfUserExist(username);
                        if (userExist) {
                            System.out.println("Username already exist!");
                            return 1; // return to the login method
                        } else {
                            CreateUser createUser = new CreateUser("userDB");
                            createUser.createNewUser(username, password);

                            EntityUpdateService entityUpdateService = new EntityUpdateService("universityDB");
                            Map<String,Object> columnValueMap = new HashMap<>();
                            columnValueMap.put("student_username", username);
                            entityUpdateService.updateValuesInColumns("student", columnValueMap,"student_name", studentName);
                        }
                    } else {
                        System.out.println("Student does not exist!");
                        return 1;
                    }
                } else {
                    System.out.println("You must log out before logging in to a new user!");
                }
            }
            case 2 -> {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter username: ");
                String username = scanner.nextLine();
                System.out.println("Enter password: ");
                String password = scanner.nextLine();
                validateUser.validateUser(username,password);
                if(validateUser.validateUser(username,password)) {
                    System.out.println("Login Successful!");
                    loggedInUser = username;
                    return 2;
                } else {
                    System.out.println("Login failed!");
                    loggedInUser = null;
                }
            }
            case 3 -> {
                System.out.println("Logging out...");
                loggedInUser = null;
                return 1;
            }
            case 4 -> System.exit(0);
            default -> System.out.println("LoginMenu | default | Error");
        }
        return 1; // Stay in the login menu if the choice is invalid or login failed
    }
}
