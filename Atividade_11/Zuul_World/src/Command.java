public class Command {
    private CommandWord commandWord;
    private String secondWord;

    /**
     * Cria um novo comando com a palavra de comando especificada e a segunda palavra.
     * 
     * @param firstWord A palavra de comando principal
     * @param secondWord A segunda palavra associada ao comando (pode ser null)
     */
    public Command(String firstWord, String secondWord) {
        CommandWords commands = new CommandWords();
        this.commandWord = commands.getCommandWord(firstWord);
        this.secondWord = secondWord;
    }

    /**
     * Obtém a palavra de comando deste comando.
     * 
     * @return A palavra de comando deste comando
     */
    public CommandWord getCommandWord() {
        return commandWord;
    }

    /**
     * Obtém a segunda palavra deste comando.
     * 
     * @return A segunda palavra deste comando, ou null se não houver segunda palavra
     */
    public String getSecondWord() {
        return secondWord;
    }

    /**
     * Verifica se a palavra de comando deste comando é desconhecida.
     * 
     * @return true se a palavra de comando for desconhecida, false caso contrário
     */
    public boolean isUnknown() {
        return (commandWord == CommandWord.UNKNOWN);
    }

    /**
     * Verifica se este comando possui uma segunda palavra.
     * 
     * @return true se houver uma segunda palavra, false caso contrário
     */
    public boolean hasSecondWord() {
        return (secondWord != null);
    }
}