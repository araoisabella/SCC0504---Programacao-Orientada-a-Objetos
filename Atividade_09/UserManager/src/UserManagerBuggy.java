//Isabella Arao 9265732, Marina Fagundes 9265405

import java.io.*;
import java.util.*;

public class UserManagerBuggy {
    private static final String CREDENTIALS_FILE = "credentials.txt";
    private static final String USER_DATA_FILE = "userdata.txt";

    private List<String[]> userSessions = new ArrayList<>(); // Simulate user sessions

    public static void main(String[] args) {
        UserManagerBuggy manager = new UserManagerBuggy();

        // Provocando erros intencionais
        // 1. NullPointerException
        manager.login(null, null);

        // 2. ArrayIndexOutOfBoundsException (indiretamente)
        String[] session = new String[1];
        System.out.println(session[10]);

        // 3. NumberFormatException
        manager.fetchUserData("not_a_number");

        // 4. IOException
        manager.fetchUserData("existing_user_id"); // Supondo que exista um usuário com esse ID
        manager.updateUserData("user_id", "new_data"); // Supondo que "user_id" seja um ID válido
    }

    /**
     * User login method
     * @param username the username
     * @param password the password
     * @return boolean indicating success or failure
     */
    public boolean login(String username, String password) {
        String[] credentials = null; // This will cause NullPointerException
        // Bug: Attempt to access method on a null object reference
        if (credentials[0].equals(username) && credentials[1].equals(password)) {
            return true;
        }
        return false;
    }

    /**
     * Fetch user data method
     * @param userId the user ID
     * @return user data as a string
     */
    public String fetchUserData(String userId) {
        // Bug: Attempt to parse a possibly incorrectly formatted string
        int id = Integer.parseInt(userId);
        BufferedReader reader = new BufferedReader(new FileReader(USER_DATA_FILE));
        String line = null;
        // Simulating data fetching based on userId
        for (int i = 0; i <= id; i++) {
            line = reader.readLine();
        }
        reader.close();
        return line;
    }

    /**
     * Update user data method
     * @param userId the user ID
     * @param data new data to update
     */
    public void updateUserData(String userId, String data) {
        // Force an IOException by providing an invalid file path
        BufferedWriter writer = new BufferedWriter(new FileWriter("/invalid/path/" + USER_DATA_FILE, true));
        // Simulate updating user data
        writer.write(userId + ":" + data);
        writer.close();
    }
}
