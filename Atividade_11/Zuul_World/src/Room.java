import java.util.HashMap;

/**
 * Classe que representa uma sala em um jogo de aventura.
 */
public class Room {
    private String description;
    private HashMap<String, Room> exits;
    private String item; // Add an item to the room.

    /**
     * Construtor da classe Room.
     * 
     * @param description A descrição da sala
     */
    public Room(String description) {
        this.description = description;
        exits = new HashMap<>();
    }
    
    /**
     * Obtém o item presente na sala.
     * 
     * @return O item presente na sala
     */
    public String getItem() {
    	return item;
    }

    /**
     * Define a saída para uma direção específica.
     * 
     * @param direction A direção da saída (ex: "north", "south", "east", "west")
     * @param neighbor A sala vizinha para onde a direção leva
     */
    public void setExit(String direction, Room neighbor) {
        exits.put(direction, neighbor);
    }

    /**
     * Obtém a descrição da sala.
     * 
     * @return A descrição da sala
     */
    public String getDescription() {
        return description;
    }

    /**
     * Obtém a sala vizinha na direção especificada.
     * 
     * @param direction A direção da saída desejada
     * @return A sala vizinha na direção especificada, ou null se não houver saída nessa direção
     */
    public Room getExit(String direction) {
        return exits.get(direction);
    }

    /**
     * Retorna uma string com todas as saídas disponíveis desta sala.
     * 
     * @return Uma string formatada com todas as direções de saída disponíveis
     */
    public String getExitString() {
        StringBuilder exitString = new StringBuilder("Exits:");
        for (String direction : exits.keySet()) {
            exitString.append(" ").append(direction);
        }
        return exitString.toString();
    }

    /**
     * Retorna a descrição detalhada da sala, incluindo suas saídas disponíveis.
     * 
     * @return A descrição detalhada da sala
     */
    public String getLongDescription() {
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Define o item presente na sala.
     * 
     * @param item O item a ser definido na sala
     */
    public void setItem(String item) {
        this.item = item;
    }
}