import java.util.Scanner;

/**
 * Classe responsável por analisar os comandos fornecidos pelo usuário.
 */
public class Parser {
    private CommandWords commands; // holds all valid command words
    private Scanner reader; // source of command input

    /**
     * Construtor padrão que inicializa as palavras de comando e o leitor de entrada.
     */
    public Parser() {
        commands = new CommandWords();
        reader = new Scanner(System.in);
    }

    /**
     * Lê a entrada do usuário, analisa-a e cria um objeto Command correspondente.
     * 
     * @return Um objeto Command que representa o comando inserido pelo usuário
     */
    public Command getCommand() {
        String inputLine; // will hold the full input line
        String word1 = null;
        String word2 = null;

        System.out.print("> "); // print prompt

        inputLine = reader.nextLine();

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next(); // get first word
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next(); // get second word
                // note: we just ignore the rest of the input line.
            }
        }

        if (commands.isCommand(word1)) {
            return new Command(word1, word2);
        } else {
            return new Command(null, word2);
        }
    }
}