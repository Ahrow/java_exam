package Logic.MenuHandler;
public class MainMenuHandler implements MenuHandler{
    public MainMenuHandler() {
    }
    @Override
    public int handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> {
                System.out.println("Launching event management...");
                return 3;} //Return 3 = eventMenu
            case 2 -> {
                System.out.println("Launching book loan system...");
            }
            case 3 -> {
                System.out.println("Launching vote for student...");
            }
            case 4 -> {
                System.out.println("Launching quiz game...");
            }
            case 5 -> {
                System.out.println("Back to login...");
                return 1;}//Return 1 = loginMenu
            default -> System.out.println("HandleMainMenu | default | error");
        }
        return 2; // Stay in main menu if choice invalid
    }
}
