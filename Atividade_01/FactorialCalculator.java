// Isabella Arao 9265732, Marina Fagundes 9265405


import java.util.Scanner;

public class FactorialCalculator {

    // Recursive method to calculate factorial
    public static int factorialRecursive(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorialRecursive(n - 1);
    }

    // Iterative method to calculate factorial
    public static int factorialIterative(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    // função para somar um int de 1 ate n 
    public static int sumOfIntegers(int n) {
        int sum = 0; //inicialização da variável soma 
        for (int i = 1; i <= n; i++) {
            sum += i; // atualização do valor de soma 
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // objeto scanner que permite entrada de valores pelo teclado 
        System.out.print("Enter a positive integer: ");
        int number; // variavel que recebe a entrada 
        while (true) { // loop infinito que verifica se o numero é um inteiro positivo 
            try { // estrutura try-catch para lidar com erros 
                number = Integer.parseInt(scanner.nextLine()); //  nextLine: método que lê a entrada ate ter uma quebra de linha; Integer.parseInt converte a string de entrada em inteiro 
                if (number < 0) { // testa se o número é negativo 
                    System.out.println("Error: Please enter a positive integer.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) { // erro para quando a entrada é invalida 
                System.out.println("Error: Invalid input. Please enter a positive integer.");
            }
        }
        
        System.out.println("Factorial of " + number + " (Recursive): " + factorialRecursive(number));
        System.out.println("Factorial of " + number + " (Iterative): " + factorialIterative(number));
        System.out.println("Sum of integers from 1 to " + number + ": " + sumOfIntegers(number)); // chamada da funcao de soma 
        
        scanner.close();
    }
}
