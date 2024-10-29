
/*Isabella Arao 9265732, Marina Fagundes 9265405*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class GuessingGame {
    private int secretNumber; // número que será adivinhado
    private int attempts; // número de tentativas

    public void setup(int min, int max) {
        secretNumber = (int) (Math.random() * (max - min + 1)) + min; // seleciona número aleatório para ser o número secreto escolhido
        attempts = 0;
    }

    public void acceptGuess(int guess) {
        countGuess();
        feedback(guess); // retorna um feedback para jogada
    }

    private void feedback(int guess) {
        if (guess < secretNumber) { // se o número for menor, imprime mensagem para escolher um maior
            System.out.println("Tente um número maior.");
        } else if (guess > secretNumber) { // se o número for maior, imprime mensagem para escolher um menor
            System.out.println("Tente um número menor.");
        } else {
            System.out.println("Parabéns! Você acertou o número em " + attempts + " tentativas."); // fim do jogo
        }
    }

    public void countGuess() {
        attempts++; //contabiliza tentativas
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in); // inicializa o scanner

        int min = 1;
        int max = 100;
        setup(min, max); // configura o jogo

        while (true) {
            System.out.print("Digite seu palpite (entre " + min + " e " + max + "): "); // imprime mensagem para o usuário
            try {
                int guess = scanner.nextInt(); // recebe o chute
                if (guess < min || guess > max) { // condições para lidar com erros na entrada do usuário
                    System.out.println("Erro: O palpite deve estar entre " + min + " e " + max + "."); // chute fora do intervalo
                    continue;
                }
                acceptGuess(guess);
                if (guess == secretNumber) {
                    break;
                }
            } catch (InputMismatchException e) { // chute com tipo diferente do esperado
                System.out.println("Erro: Por favor, digite um número inteiro válido.");
                scanner.next(); // Limpar o buffer do scanner
            }
        }
        scanner.close(); // fecha scanner
    }

    public static void main(String[] args) {
        GuessingGame game = new GuessingGame(); // inicializa o guessinggame
        game.playGame(); // chama o método para jogar o jogo
    }
}