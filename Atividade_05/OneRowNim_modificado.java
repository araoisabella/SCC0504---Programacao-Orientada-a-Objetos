//Isabella Arao 9265732, Marina Fagundes 9265405 

import java.io.*;
import java.awt.*;

interface IPlayer {
    public String makeAMove(String prompt);
}

interface IGame {
    public String getGamePrompt();
    public String reportGameState();
}

interface UserInterface {
    public String getUserInput();
    public void report(String s);
    public void reportWinner(String winner); // declaracao  de um novo metodo 
}

interface CLUIPlayableGame extends IGame {
    public abstract void play(UserInterface ui);
}

abstract class TwoPlayerGame {// classe abstrata para jogos com dois jogadores 
    public static final int PLAYER_ONE = 1;
    public static final int PLAYER_TWO = 2;

    protected boolean onePlaysNext = true;
    protected int nComputers = 0;
    protected IPlayer computer1, computer2;

    public void setPlayer(int starter) {
        if (starter == PLAYER_TWO)
            onePlaysNext = false;
        else
            onePlaysNext = true;
    }

    public int getPlayer() {
        if (onePlaysNext)
            return PLAYER_ONE;
        else
            return PLAYER_TWO;
    }

    public void changePlayer() {
        onePlaysNext = !onePlaysNext;
    }

    public int getNComputers() {
        return nComputers;
    }

    public String getRules() {
        return "The rules of this game are: ";
    }

    public void addComputerPlayer(IPlayer player) {
        if (nComputers == 0)
            computer2 = player;
        else if (nComputers == 1)
            computer1 = player;
        else
            return; // No more than 2 players
        ++nComputers;
    }

    public abstract boolean gameOver();
    public abstract String getWinner();
}

class KeyboardReader implements UserInterface { //classe para ler entradas do teclado 
    private BufferedReader reader;

    public KeyboardReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    public String getKeyboardInput() {
        return readKeyboard();
    }

    public int getKeyboardInteger() {
        return Integer.parseInt(readKeyboard());
    }

    public double getKeyboardDouble() {
        return Double.parseDouble(readKeyboard());
    }

    public String getUserInput() {
        return getKeyboardInput();
    }

    public void report(String s) {
        System.out.print(s);
    }
/**
 * Imprimir o vencedor 
 * 
 * @param winner 
 *      jogador vencedor informado 
 * 
 **/
    public void reportWinner(String winner) { //metodo que imprime na tela quem foi o jogador que ganhou 
        System.out.println(">>>>>>>> WINNER is PLAYER " + winner + " <<<<<<<<");
    }

    public void display(String s) {
        System.out.print(s);
    }

    private String readKeyboard() {
        String line = "";
        try {
            line = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }
}

class NimPlayerBad implements IPlayer { // implementacao antiga de NimPlayer 
    private OneRowNim game;

    public NimPlayerBad(OneRowNim game) {
        this.game = game;
    }

    public String makeAMove(String prompt) {
        return "" + randomMove();
    }

    private int randomMove() {
        int sticksLeft = game.getSticks();
        return 1 + (int) (Math.random() * Math.min(sticksLeft, game.MAX_PICKUP));
    }

    public String toString() {
        String className = this.getClass().toString();
        return className.substring(5);
    }
}// movimentos aleatorios 

/**
 * Classe que implementa novo computador para o OneRowNim 
 * 
 * 
 *
 **/ 
class NimPlayerSuper implements IPlayer { //implementacao nova de NimPlayer 
    private OneRowNim game;

/**
 * Metodo construtor 
 * 
 * @param game 
 *      jogo informado. 
 **/
    public NimPlayerSuper(OneRowNim game) {
        this.game = game;
    }

/**
 * Fazer uma jogada 
 * 
 * @param prompt 
 *      comando informado
 * 
 * @return move 
 **/

    public String makeAMove(String prompt) {
        int sticksLeft = game.getSticks();
        int move;
        
        if (sticksLeft > 3) {
            // Se ainda houver mais de 3 palitos, escolhe sempre 2 palitos
            move = 2;
        } else {
            // Se houver 1, 2 ou 3 palitos, escolhe aleatoriamente entre 1 e 3 palitos
            move = 1 + (int) (Math.random() * 3);
        }
        
        return Integer.toString(move);
    }

/**
 * Representar textualmente a instancia 
 * 
 * 
 * @return substring 
 **/

    public String toString() {
        String className = this.getClass().toString();
        return className.substring(5);
    }
}

public class OneRowNim extends TwoPlayerGame implements CLUIPlayableGame { //classe para o jogo OneRowNim 
    public static final int MAX_PICKUP = 3;
    public static final int MAX_STICKS = 11;

    private int nSticks = MAX_STICKS;

    public OneRowNim() { //metodo construtor 
    }

    public OneRowNim(int sticks) {
        nSticks = sticks;
    }

    public OneRowNim(int sticks, int starter) {
        nSticks = sticks;
        setPlayer(starter);
    }

    public boolean takeSticks(int num) {
        if (num < 1 || num > MAX_PICKUP || num > nSticks)
            return false;
        else
        {
            nSticks = nSticks - num;
            return true;
        }
    }

    public int getSticks() {
        return nSticks;
    }

    public String getRules() {
        return "\n*** The Rules of One Row Nim ***\n" +
                "(1) A number of sticks between 7 and " + MAX_STICKS + " is chosen.\n" +
                "(2) Two players alternate making moves.\n" +
                "(3) A move consists of subtracting between 1 and\n\t" +
                MAX_PICKUP + " sticks from the current number of sticks.\n" +
                "(4) A player who cannot leave a positive\n\t" +
                " number of sticks for the other player loses.\n";
    }

    public boolean gameOver() {
        return (nSticks <= 0);
    }

    public String getWinner() {
        if (gameOver())
            return "" + getPlayer() + "";
        return "The game is not over yet.";
    }

    public String getGamePrompt() {
        return "\nYou can pick up between 1 and " + Math.min(MAX_PICKUP, nSticks) + " : ";
    }

    public String reportGameState() {
        if (!gameOver())
            return ("\nSticks left: " + getSticks() + " Who's turn: Player " + getPlayer());
        else
            return ("\nSticks left: " + getSticks() + " Game over! Winner is Player " + getWinner() + "\n");
    }

    public void play(UserInterface ui) {
        int sticks = 0;
        ui.report(getRules());
        if (computer1 != null)
            ui.report("\nPlayer 1 is a " + computer1.toString());
        if (computer2 != null)
            ui.report("\nPlayer 2 is a " + computer2.toString());

        while (!gameOver()) {
            IPlayer computer = null;
            ui.report(reportGameState());
            switch (getPlayer()) {
                case PLAYER_ONE:
                    computer = computer1;
                    break;
                case PLAYER_TWO:
                    computer = computer2;
                    break;
            }

            if (computer != null) {
                sticks = Integer.parseInt(computer.makeAMove(""));
                ui.report(computer.toString() + " takes " + sticks + " sticks.\n");
            } else {
                ui.report(getGamePrompt());
                sticks = Integer.parseInt(ui.getUserInput());
            }
            if (takeSticks(sticks))
                changePlayer();
        }
        ui.report(reportGameState());
        ui.reportWinner(getWinner());
    }

    public String submitUserMove(String theMove) {
        int sticks = Integer.parseInt(theMove);
        if (takeSticks(sticks)) {
            changePlayer();
            if (gameOver()) {
                return reportGameState() + "\nGame won by player" + getWinner() + "\n";
            } else {
                return reportGameState() + getGamePrompt();
            }
        }
        return "\nOops. " + sticks + " is an illegal move." + getGamePrompt();
    }

    public void draw(Graphics g) {
        g.setColor(Color.red);
        for (int k = 1; k <= nSticks; k++) {
            g.drawLine(10 + k * 10, 10, 10 + k * 10, 60);
        }
    }

    public static void main(String args[]) {
        KeyboardReader kb = new KeyboardReader();
        CLUIPlayableGame game = new OneRowNim();

        kb.report("How many computers are playing, 0, 1, or 2? ");// escolhe quantos jogadores sao computador  
        int m = kb.getKeyboardInteger();
        
        for (int k = 0; k < m; k++) {
            kb.report("What type of player, NimPlayerBad = 0, or NimPlayerSuper = 1? "); // escolhe o tipo de computador 
            int n = kb.getKeyboardInteger();
            
            if (n == 1){// nova implementacao de NimPlayer, quando escolhe o super 
                IPlayer computer1 = new NimPlayerSuper((OneRowNim) game);
                ((TwoPlayerGame) game).addComputerPlayer(computer1);   
            }
            else if (n == 0) { // antiga implementacao de NimPlayer, quando escolhe o bad 
                IPlayer computer2 = new NimPlayerBad((OneRowNim) game);
                ((TwoPlayerGame) game).addComputerPlayer(computer2);
            }
            
        }
        game.play(kb);
    }
}