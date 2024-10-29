/**
 * Classe que gerencia os comandos válidos disponíveis em um sistema ou jogo.
 */
public class CommandWords {
    private static final String[] validCommands = { "go", "quit", "help", "look", "back" };

    /**
     * Verifica se uma string representa um comando válido.
     * 
     * @param aString A string a ser verificada
     * @return true se a string é um comando válido, false caso contrário
     */
    public boolean isCommand(String aString) {
        for (String command : validCommands) {
            if (command.equals(aString))
                return true;
        }
        return false;
    }

    /**
     * Obtém o tipo de comando representado pela string especificada.
     * 
     * @param commandWord A string que representa o comando
     * @return O tipo de comando correspondente, ou CommandWord.UNKNOWN se não for um comando válido
     */
    public CommandWord getCommandWord(String commandWord) {
        try {
            return CommandWord.valueOf(commandWord.toUpperCase());
        } catch (IllegalArgumentException e) {
            return CommandWord.UNKNOWN;
        }
    }
}