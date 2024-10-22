package Logic.MenuHandler;

import Logic.EventOperations.RegisterEvent;
import Logic.EventOperations.ShowEvent;

public class EventMenuHandler implements MenuHandler{
    public EventMenuHandler() {}
    @Override
    public int handleMenuChoice(int choice) {
        switch (choice) {
            case 1 -> {
                System.out.println("Upcoming events...");
                ShowEvent showEvent = new ShowEvent("eventDB");
                showEvent.showAllEvents();
            }
            case 2 -> {
                System.out.println("Register for event...");
                RegisterEvent registerEvent = new RegisterEvent("eventDB");
                registerEvent.registerForEvent();
            }
            case 3 -> {
                System.out.println("3 .....");
            }
            case 4 -> System.out.println("Cancel event......");
            case 5 -> {
                System.out.println("Back to main....");
                return 2;
            }
            default -> System.out.println("HandleMainMenu | default | error");
        }
        return 2; // Stay in main menu if choice invalid
    }
}
