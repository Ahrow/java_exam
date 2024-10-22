package MultipleValueInsertion;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EntityInsertionService insertionService = new EntityInsertionService("databaseName");
        Map<String, Object> columnValueMap = new HashMap<>();
        columnValueMap.put("column1", "value1");
        columnValueMap.put("column2", 123);
        insertionService.insertValuesIntoColumns("tableName", columnValueMap);
    }
}
