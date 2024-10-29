/**
 * Enumeração que define os possíveis comandos disponíveis para um jogo ou sistema interativo.
 */
public enum CommandWord {
    /**
     * Comando para mover para uma direção específica.
     */
    GO,

    /**
     * Comando para encerrar o jogo ou sistema.
     */
    QUIT,

    /**
     * Comando para obter ajuda.
     */
    HELP,

    /**
     * Comando para examinar o ambiente ou objeto.
     */
    LOOK,

    /**
     * Comando para retornar à localização anterior.
     */
    BACK,

    /**
     * Comando desconhecido ou não reconhecido.
     */
    UNKNOWN
}
