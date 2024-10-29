package Calculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Classe que implementa uma calculadora simples com interface gráfica em Java.
 * Esta calculadora permite que o usuário insira dois números e selecione uma operação para realizar.
 * As operações suportadas são: adição, subtração, multiplicação e divisão.
 * O resultado da operação é exibido na interface gráfica.
 */
public class SimpleCalculatorGUI extends JFrame implements ActionListener {
    private JTextField numField1, numField2, resultField;
    private JButton addButton, subtractButton, multiplyButton, divideButton;

    /**
     * Construtor da classe SimpleCalculatorGUI.
     * Inicializa a interface gráfica da calculadora.
     */
    public SimpleCalculatorGUI() {
        setTitle("Simple Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        numField1 = new JTextField();
        numField2 = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        addButton = new JButton("+");
        subtractButton = new JButton("-");
        multiplyButton = new JButton("*");
        divideButton = new JButton("/");

        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);

        add(new JLabel("Number 1:"));
        add(numField1);
        add(new JLabel("Number 2:"));
        add(numField2);
        add(new JLabel("Result:"));
        add(resultField);
        add(addButton);
        add(subtractButton);
        add(multiplyButton);
        add(divideButton);

        setVisible(true);
    }

    /**
     * Método actionPerformed da interface ActionListener.
     * Executado quando um botão de operação é clicado.
     * Realiza a operação correspondente e exibe o resultado.
     * @param e o evento de ação que ocorreu
     * @throws se ocorrer uma divisão por zero
     * @throws NumberFormatException se o formato dos números de entrada for inválido
     */
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(numField1.getText());
            double num2 = Double.parseDouble(numField2.getText());
            double result = 0.0;

            if (e.getSource() == addButton) {
                result = num1 + num2;
            } else if (e.getSource() == subtractButton) {
                result = num1 - num2;
            } else if (e.getSource() == multiplyButton) {
                result = num1 * num2;
            } else if (e.getSource() == divideButton) {
                if (num2 == 0) {
                    resultField.setText("Error: Division by zero");
                    return;
                }
                result = num1 / num2;
            }

            resultField.setText(String.valueOf(result));
        } catch (NumberFormatException ex) {
            resultField.setText("Error: Invalid input");
        }
    }

    /**
     * Método main para executar a aplicação.
     * Cria uma instância da classe SimpleCalculatorGUI e a torna visível.
     * @param args os argumentos de linha de comando 
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SimpleCalculatorGUI();
            }
        });
    }
}