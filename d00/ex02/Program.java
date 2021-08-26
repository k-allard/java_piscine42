import java.util.Scanner;

public class Program {

    private static int getSumOfDigits(int number) {
        int digit;

        int sum = 0;

        while (number > 0) {
            digit = number % 10;
            sum += digit;
            number = number / 10;
        }

        return sum;
    }

    private static boolean isCoffeeRequest(int number) {
        number = getSumOfDigits(number);

        if (number <= 1) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        
        if (number <= 3) {
            return true;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        for (int i = 5; i * i <= number; i = i + 6) {
            if (number % i == 0 || number % (i + 2) == 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int currentRequest = 0, coffeeRequestsCount = 0;

        Scanner scanner = new Scanner(System.in);

        while (currentRequest != 42) {
            if (isCoffeeRequest(currentRequest = scanner.nextInt()))
                coffeeRequestsCount++;
        }

        System.out.println("Count of coffee-request - " + coffeeRequestsCount);
    }
}
