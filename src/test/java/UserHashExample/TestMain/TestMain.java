package UserHashExample.TestMain;

import UserHashExample.CreateUser.CreateNewUser;

import java.util.Scanner;

public class TestMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        CreateNewUser createNewUser = new CreateNewUser();
        createNewUser.createNewUser(username, password);

        System.out.println("Enter password to login: ");
        String loginPassword = scanner.nextLine();

        if (createNewUser.validateUser(username,loginPassword)) {
            System.out.println("Login Successful!!");
        } else {
            System.out.println("Login failed!");
        }
        scanner.close();
    }
}
