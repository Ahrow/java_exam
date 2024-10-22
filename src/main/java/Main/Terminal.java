package Main;

import Logic.MenuHandler.EventMenuHandler;
import Logic.MenuHandler.LoginMenuHandler;
import Logic.MenuHandler.MainMenuHandler;
import UserInterface.DisplayMenu;

import java.util.Scanner;

public class Terminal {
    private final DisplayMenu displaymenu;
    private final LoginMenuHandler loginMenuHandler;
    private final MainMenuHandler mainMenuHandler;
    private final EventMenuHandler eventMenuHandler;
    private int currentMenu;

    public Terminal() {
        displaymenu = new DisplayMenu();
        currentMenu = 1; // Starting menu
        loginMenuHandler = new LoginMenuHandler();
        mainMenuHandler = new MainMenuHandler();
        eventMenuHandler = new EventMenuHandler();
    }
    public void runTerminal() {
        boolean runState = true;
        Scanner scanner = new Scanner(System.in);

        do {
            switch (currentMenu) {
                case 1 -> {
                    displaymenu.displayLoginMenu();
                    int loginChoice = scanner.nextInt();
                    currentMenu = loginMenuHandler.handleMenuChoice(loginChoice);
                }
                case 2 -> {
                    displaymenu.displayMainMenu();
                    int mainMenuChoice = scanner.nextInt();
                    currentMenu = mainMenuHandler.handleMenuChoice(mainMenuChoice);
                }
                case 3 -> {
                    displaymenu.displayEventMenu();
                    int eventMenuChoice = scanner.nextInt();
                    currentMenu = eventMenuHandler.handleMenuChoice(eventMenuChoice);
                }
                default -> System.out.println("Something went wrong!");
            }
        } while (runState);
        scanner.close();
    }
}
