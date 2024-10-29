//Isabella Arao 9265732, Marina Fagundes 9265405

import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class UserManagerHandled {
    private static final String CREDENTIALS_FILE = "credentials.txt";
    private static final String USER_DATA_FILE = "userdata.txt";
    private static final String ERROR_LOG_FILE = "error.log";

    private List<String[]> userSessions = new ArrayList<>(); // Simulate user sessions

    public static void main(String[] args) {
        UserManagerHandled manager = new UserManagerHandled();

        // Adicionando um novo usuário
        manager.addUser("new_user", "new_user_password");
        manager.addUser("new_user1", "new_user_password1");

        // Provocando erros intencionais
        // 1. NullPointerException
        try {
            manager.login(null, null);
        } catch (NullPointerException e) {
            manager.logError(e);
            System.out.println("NullPointerException: Username or password is null.");
        }

        // 2. ArrayIndexOutOfBoundsException (indiretamente)
        try {
            String[] session = new String[1];
            System.out.println(session[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            manager.logError(e);
            System.out.println("ArrayIndexOutOfBoundsException: Tried to access an invalid session index.");
        }

        // 3. NumberFormatException
        try {
            manager.fetchUserData("not_a_number");
        } catch (NumberFormatException e) {
            manager.logError(e);
            System.out.println("NumberFormatException: Invalid user ID format. Please enter a valid number.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 4. IOException
        try {
            manager.fetchUserData("existing_user_id"); // Supondo que exista um usuário com esse ID
        } catch (IOException e) {
            manager.logError(e);
            System.out.println("IOException: An error occurred while fetching user data.");
        }

        try {
            manager.updateUserData("user_id", "new_data"); // Supondo que "user_id" seja um ID válido
        } catch (IOException e) {
            manager.logError(e);
            System.out.println("IOException: An error occurred while updating user data.");
        }
    }

    /**
     * Add a new user method
     * @param username the username
     * @param password the password
     */
    public void addUser(String username, String password) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_DATA_FILE, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            System.out.println("User added: " + username);
        } catch (IOException e) {
            logError(e);
            System.out.println("IOException: Error adding user.");
        }
    }

    /**
     * User login method
     * @param username the username
     * @param password the password
     * @return boolean indicating success or failure
     */
    public boolean login(String username, String password) {
        String[] credentials = null; // This will cause NullPointerException
        try {
            // Bug: Attempt to access method on a null object reference
            if (credentials[0].equals(username) && credentials[1].equals(password)) {
                return true;
            }
        } catch (NullPointerException e) {
            logError(e);
            System.out.println("NullPointerException: Credentials are not initialized.");
        }
        return false;
    }

    /**
     * Fetch user data method
     * @param userId the user ID
     * @return user data as a string
     */
    public String fetchUserData(String userId) throws IOException {
        BufferedReader reader = null;
        String line = null;
        try {
            // Bug: Attempt to parse a possibly incorrectly formatted string
            int id = Integer.parseInt(userId);

            reader = new BufferedReader(new FileReader(USER_DATA_FILE));
            // Simulating data fetching based on userId
            for (int i = 0; i <= id; i++) {
                line = reader.readLine();
            }
        } catch (FileNotFoundException e) {
            logError(e);
            System.out.println("FileNotFoundException: User data file not found.");
        } catch (IOException e) {
            logError(e);
            System.out.println("IOException: Error reading from user data file.");
        } catch (NumberFormatException e) {
            logError(e);
            System.out.println("NumberFormatException: Invalid user ID format. Please enter a valid number.");
        } catch (NullPointerException e) {
            logError(e);
            System.out.println("NullPointerException: Encountered null while reading user data.");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logError(e);
                    System.out.println("IOException: Error closing reader.");
                }
            }
        }
        return line;
    }

    /**
     * Update user data method
     * @param userId the user ID
     * @param data new data to update
     */
    public void updateUserData(String userId, String data) throws IOException {
        BufferedWriter writer = null;
        try {
            // Force an IOException by providing an invalid file path
            writer = new BufferedWriter(new FileWriter("/invalid/path/" + USER_DATA_FILE, true));
            // Simulate updating user data
            writer.write(userId + ":" + data);
        } catch (IOException e) {
            logError(e);
            System.out.println("IOException: Error writing to user data file.");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    logError(e);
                    System.out.println("IOException: Error closing writer.");
                }
            }
        }
    }

    /**
     * Log error details to a file
     * @param e the exception to log
     */
    private void logError(Exception e) {
        try (BufferedWriter logWriter = new BufferedWriter(new FileWriter(ERROR_LOG_FILE, true))) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String timestamp = sdf.format(new Date());
            logWriter.write(timestamp + " - " + e.toString());
            logWriter.newLine();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
