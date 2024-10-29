import java.util.Scanner;
import java.util.HashMap;

/**
 * Class Room - a room in an adventure game.
 * <p>
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text-based adventure game.
 * <p>
 * A "Room" represents one location in the scenery of the game. It is
 * connected to other rooms via exits. The exits are labeled north,
 * east, south, west, up, and down. For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits; // stores exits of this room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * 
     * @param description The room's description.
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
    }

    /**
     * Define an exit for this room.
     * 
     * @param direction The direction of the exit.
     * @param neighbor  The room in the given direction.
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room.
     * 
     * @return The description of the room.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * 
     * @param direction The exit direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Return a string describing the room's exits, for example "Exits: north west".
     * 
     * @return Details of the room's exits.
     */
    public String getExitString() {
        StringBuilder exitString = new StringBuilder("Exits:");
        for (String direction : exits.keySet()) {
            exitString.append(" ").append(direction);
        }
        return exitString.toString();
    }
}