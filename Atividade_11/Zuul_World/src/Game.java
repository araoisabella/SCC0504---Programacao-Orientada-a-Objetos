//Isabella Arao 9265732, Marina Fagundes 9265405

/**
 * Classe principal que representa o jogo "World of Zuul", um jogo de aventura simples.
 */
public class Game {
    private Parser parser;
    private Room currentRoom;
    private Room previousRoom;

    /**
     * Construtor da classe Game. Inicializa as salas do jogo e o parser dos comandos.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Método privado que cria todas as salas e define as conexões entre elas.
     */
    private void createRooms() {
        Room outside, theatre, pub, lab, office, upstairs, basement;

        // create the rooms
        outside = new Room("outside the main entrance of the university");
        theatre = new Room("in a lecture theatre");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        upstairs = new Room("upstairs in the university");
        basement = new Room("in the basement of the university");

        // initialize room exits
        outside.setExit("east", theatre);
        outside.setExit("south", lab);
        outside.setExit("west", pub);
        outside.setExit("up", upstairs);
        outside.setExit("down", basement);

        theatre.setExit("west", outside);
        pub.setExit("east", outside);
        lab.setExit("north", outside);
        lab.setExit("east", office);
        office.setExit("west", lab);
        upstairs.setExit("down", outside);
        basement.setExit("up", outside);

        // Add items to rooms
        outside.setItem("a bench");
        theatre.setItem("a projector");
        pub.setItem("a dartboard");
        lab.setItem("a computer");
        office.setItem("a desk");
        upstairs.setItem("a bookshelf");
        basement.setItem("a storage box");

        currentRoom = outside; // start game outside
    }
    
    /**
     * Método principal que inicia e controla a execução do jogo.
     */
    public void play() {
        printWelcome();

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing. Good bye.");
    }
    

    /**
     * Imprime a mensagem de boas-vindas ao jogador.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        printLocationInfo();
    }

    /**
     * Imprime informações sobre a localização atual do jogador.
     */
    private void printLocationInfo() {
        System.out.println(currentRoom.getLongDescription() + "\n" + "You see: " + currentRoom.getItem());
    }

    /**
     * Processa o comando fornecido pelo jogador.
     * 
     * @param command O comando a ser processado
     * @return true se o jogador deseja sair do jogo, false caso contrário
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        if (command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        CommandWord commandWord = command.getCommandWord();
        switch (commandWord) {
            case HELP:
                printHelp();
                break;
            case LOOK:
                System.out.println(currentRoom.getLongDescription());
                break;
            case GO:
                goRoom(command);
                break;
            case BACK:
                back();
                break;
            case QUIT:
                wantToQuit = quit(command);
                break;
        }

        return wantToQuit;
    }

    /**
     * Imprime uma mensagem de ajuda para o jogador.
     */
    private void printHelp() {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
        System.out.println();
        System.out.println("Your command words are:");
        System.out.println("   go quit help look back");
    }

    /**
     * Move o jogador para a sala na direção especificada pelo comando.
     * 
     * @param command O comando que contém a direção para onde o jogador deseja ir
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            previousRoom = currentRoom; // Track the previous room.
            currentRoom = nextRoom;
            printLocationInfo();
        }
    }

    /**
     * Permite que o jogador volte para a sala anteriormente visitada.
     */
    private void back() {
        if (previousRoom == null) {
            System.out.println("You can't go back!");
        } else {
            Room temp = currentRoom;
            currentRoom = previousRoom;
            previousRoom = temp;
            printLocationInfo();
        }
    }

    /**
     * Verifica se o jogador quer sair do jogo.
     * 
     * @param command O comando que pode conter informações adicionais sobre o comando de saída
     * @return true se o jogador deseja sair do jogo, false caso contrário
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }

    /**
     * Método de teste para verificar a funcionalidade do comando 'back'.
     */
    public void testBackCommand() {
        Game game = new Game();
        assert game.currentRoom.getDescription().equals("outside the main entrance of the university");

        game.processCommand(new Command("go", "east"));
        assert game.currentRoom.getDescription().equals("in a lecture theatre");

        game.processCommand(new Command("back", null));
        assert game.currentRoom.getDescription().equals("outside the main entrance of the university");

        System.out.println("All tests passed.");
    }

    /**
     * Método principal que inicia o jogo.
     * 
     * @param args Argumentos da linha de comando (não são utilizados neste jogo)
     */
    public static void main(String[] args) {
        new Game().play();
        new Game().testBackCommand();
    }
}
