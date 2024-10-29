//Isabella Arao 9265732, Marina Fagundes 9265405

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OneRowNimGUI extends JFrame implements UserInterface {
    private OneRowNim game;
    private JTextArea gameStateDisplay;
    private JTextField userMoveInput;
    private JButton takeSticksButton;
    private JButton resetGameButton;
    private IPlayer player1;
    private IPlayer player2;
    private boolean isPlayer1Turn;

    public OneRowNimGUI() {
        initGameSetup();
    }

    private void initGameSetup() {
        String[] options = {"Player vs Computer", "Player vs Player", "Computer vs Computer"};
        int choice = JOptionPane.showOptionDialog(this, "Select game mode:", "Game Setup",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == -1) {
            System.exit(0); // Exit if the user closes the dialog
        }

        switch (choice) {
            case 0:
                player1 = null; // Human player
                player2 = new NimPlayerBad(new OneRowNim());
                break;
            case 1:
                player1 = null; // Human player
                player2 = null; // Human player
                break;
            case 2:
                player1 = new NimPlayerBad(new OneRowNim());
                player2 = new NimPlayerBad(new OneRowNim());
                break;
        }

        game = new OneRowNim();
        isPlayer1Turn = true;

        initComponents();
    }

    private void initComponents() {
        setTitle("One Row Nim Game");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Display for game state
        gameStateDisplay = new JTextArea();
        gameStateDisplay.setEditable(false);
        add(new JScrollPane(gameStateDisplay), BorderLayout.CENTER);

        // Panel for user input and buttons
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        userMoveInput = new JTextField(10);
        inputPanel.add(userMoveInput);

        takeSticksButton = new JButton("Take Sticks");
        takeSticksButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUserMove();
            }
        });
        inputPanel.add(takeSticksButton);

        resetGameButton = new JButton("Reset Game");
        resetGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });
        inputPanel.add(resetGameButton);
        add(inputPanel, BorderLayout.SOUTH);

        // Initial game state
        updateGameStateDisplay(game.getRules());

        // If both players are computers, start the game loop
        if (player1 != null && player2 != null) {
            new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (!game.gameOver()) {
                        handleComputerMove();
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            }).start();
        }
    }

    private void handleUserMove() {
        if ((isPlayer1Turn && player1 == null) || (!isPlayer1Turn && player2 == null)) {
            String userInput = userMoveInput.getText();
            String response = game.submitUserMove(userInput);
            appendToGameStateDisplay(response);
            userMoveInput.setText("");
            isPlayer1Turn = !isPlayer1Turn;

            // Handle computer move immediately after user move
            if ((isPlayer1Turn && player1 != null) || (!isPlayer1Turn && player2 != null)) {
                handleComputerMove();
            }
        }
    }

    private void handleComputerMove() {
        if (game.gameOver()) {
            appendToGameStateDisplay(game.reportGameState());
            return;
        }

        IPlayer currentPlayer = isPlayer1Turn ? player1 : player2;
        if (currentPlayer != null) {
            String move = currentPlayer.makeAMove("");
            String response = game.submitUserMove(move);
            response += "\n" + (isPlayer1Turn ? "Player 1" : "Player 2") + " (Computer) takes " + move + " sticks.";
            appendToGameStateDisplay(response);
            isPlayer1Turn = !isPlayer1Turn;

            // If both players are computers, continue the game loop
            if ((isPlayer1Turn && player1 != null) || (!isPlayer1Turn && player2 != null)) {
                new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!game.gameOver()) {
                            handleComputerMove();
                        } else {
                            ((Timer) e.getSource()).stop();
                        }
                    }
                }).start();
            }
        }
    }

    private void resetGame() {
        game = new OneRowNim();
        isPlayer1Turn = true;
        gameStateDisplay.setText("");
        appendToGameStateDisplay("Game has been reset.\n" + game.getRules());
    }

    private void updateGameStateDisplay(String message) {
        gameStateDisplay.setText(message);
    }

    private void appendToGameStateDisplay(String message) {
        gameStateDisplay.append(message + "\n");
    }

    @Override
    public String getUserInput() {
        return userMoveInput.getText();
    }

    @Override
    public void report(String s) {
        appendToGameStateDisplay(s);
    }

    @Override
    public void prompt(String s) {
        appendToGameStateDisplay(s);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new OneRowNimGUI().setVisible(true);
            }
        });
    }
}
