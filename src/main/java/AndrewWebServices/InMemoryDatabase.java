package AndrewWebServices;

import java.util.HashMap;
import java.util.Map;

/*
 * InMemoryDatabase is a fake for the AndrewWS database which is used to improve test efficiency.
 * Remember, fakes are fully functional classes with simplified implementation.
 * What is the simplest core functionality that we need for a functional database?
 * 
 * Hint: there are two methods you need to implement
 */
/*
 * InMemoryDatabase is a fake database for testing which avoids the delay inherent in the real Database class.
 */
public class InMemoryDatabase extends Database {
    private Map<String, Integer> userPasswords = new HashMap<>();

    public InMemoryDatabase() {
        // Prepopulate with test data
        userPasswords.put("Scotty", 17214); // Assuming the same password for consistency
        userPasswords.put("TestUser", 12345);
    }

    @Override
    public int getPassword(String accountName) {
        return userPasswords.getOrDefault(accountName, 0);
    }
}