package Logic.EventOperations;

import DAO.BaseDAO;
import Services.EntityExistenceService;
import Services.EntityInsertionService;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RegisterEvent extends BaseDAO {
    public RegisterEvent(String dbName) {
        super(dbName);
    }
    public void registerForEvent(){
        Scanner scanner = new Scanner(System.in);
        ShowEvent showEvent = new ShowEvent("eventDB");
        showEvent.showAllEvents();
        System.out.println("Select event");
        int eventChoice = scanner.nextInt();
        scanner.nextLine();
        //TODO finish this
        EntityExistenceService eventExistenceService = new EntityExistenceService("eventDB");
        boolean eventExist = eventExistenceService.checkIfEventExist(eventChoice);

        if (eventExist) {
            EntityInsertionService entityInsertionService = new EntityInsertionService("eventDB");
            Map<String, Object> columnValueMap = new HashMap<>();
            columnValueMap.put("participant_name", "John DOE");
            columnValueMap.put("participant_type", "student");
            columnValueMap.put("event_id", eventChoice);
            entityInsertionService.insertValuesIntoColumns("participant", columnValueMap);

            System.out.println("Do you wish to bring guest(s)?");
            System.out.println("Expected input: yes/no");
            String userAnswer = scanner.nextLine();
            if (userAnswer.equals("yes")) {
                System.out.println("Call add guest method");
                AddGuest addGuest = new AddGuest();
                addGuest.addGuestToEvent(eventChoice);
            } else if (userAnswer.equals("no")) {
                System.out.println("You can add guests later in the event menu -> edit event");
            } else {
                System.out.println("ERROR: only yes or no");
            }
        } else {
            System.out.println("Event does not exist!");
        }
    }
}
