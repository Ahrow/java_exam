package UserInterface;

public class DisplayMenu {

    public void displayHelpMenu() {
        System.out.println("Help menu");
        System.out.println("----------");
    }
    public void displayLoginMenu() {
        System.out.println("Welcome to University Program!");
        System.out.println("------------------------------");
        System.out.println("1. Create new user");
        System.out.println("2. Login");
        System.out.println("3. Logout");
        System.out.println("4. Exit Program");
    }
    public void displayMainMenu() {
        System.out.println("Main menu");
        System.out.println("---------");
        System.out.println("1. Event management");
        System.out.println("2. Book loan system");
        System.out.println("3. Vote for student of the year");
        System.out.println("4. Play quiz-game");
        System.out.println("5. Back");
    }
    public void displayEventMenu() {
        System.out.println("Event menu");
        System.out.println("------------");
        System.out.println("1. Upcoming events");
        System.out.println("2. Register for event");
        System.out.println("3. Edit event");
        System.out.println("4. Cancel event");
        System.out.println("5. Back");
    }
}
