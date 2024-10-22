package Logic.EventOperations;

import Services.EntityExistenceService;
import Services.EntityInsertionService;

import java.util.*;

public class AddGuest {
    public void addGuestToEvent(int eventChoice) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number of guests (max 4): ");
        int numberOfGuests = scanner.nextInt();
        List<String> guestNames = new ArrayList<>();

        if (numberOfGuests >= 0 && numberOfGuests <= 4) {
            for (int i = 1; i <= numberOfGuests; i++) {
                System.out.println("Enter the name of guest " + i + ": ");
                String guestName = scanner.next();
                guestNames.add(guestName);
            }
        }

        EntityExistenceService entityExistenceService = new EntityExistenceService("eventDB");
        List<String> existingGuestNames = new ArrayList<>();

        for (String guestName : guestNames) {
            if (entityExistenceService.checkIfGuestExist(guestName)) {
                existingGuestNames.add(guestName);
            }
        }

        if (!existingGuestNames.isEmpty()) {
            System.out.println("The following guest(s) already exist and cannot be added:");
            for (String existingGuest : existingGuestNames) {
                System.out.println(existingGuest);
            }
        } else {
            System.out.println("All guests are new and can be added.");
            EntityInsertionService entityInsertionService = new EntityInsertionService("eventDB");
            for (String guestName : guestNames) {
                Map<String, Object> columnValueMap = new HashMap<>();
                columnValueMap.put("participant_name", guestName);
                columnValueMap.put("participant_type", "guest");
                columnValueMap.put("event_id", eventChoice);
                entityInsertionService.insertValuesIntoColumns("participant", columnValueMap);
                System.out.println("Guest added: " + guestName);
            }
        }
    }
}
