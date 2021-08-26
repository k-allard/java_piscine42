public class Program {

    public static void main(String[] args) {
        int number = 479598;

        int digit;

        int sum = 0;

        while (number > 0) {
            digit = number % 10;
            sum += digit;
            number = number / 10;
        }

        System.out.println(sum);
    }
}