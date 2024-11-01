/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text-based adventure game.
 * <p>
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognize commands as they are typed in.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class CommandWords {
    // a constant array that holds all valid command words
    private static final String[] validCommands = { "go", "quit", "help", "look" };

    /**
     * Constructor - initialize the command words.
     */
    public CommandWords() {
        // nothing to do at the moment...
    }

    /**
     * Check whether a given String is a valid command word.
     * 
     * @return true if a given string is a valid command, false if it isn't.
     */
    public boolean isCommand(String aString) {
        for (String validCommand : validCommands) {
            if (validCommand.equals(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }
}
