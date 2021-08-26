import java.util.Scanner;

public class Program {

    private static void printWeeklyGraph(int weekNumber, int progress) {
        System.out.print("Week " + weekNumber + " ");

        for (int i = 0; i < progress; i++) {
            System.out.print("=");
        }

        System.out.println(">");
    }

    private static int getDigit(long gradesList, int numOfWeeks, int i) {
        i = numOfWeeks - i;
        while (--i > 0) {
            gradesList /= 10;
        }
        return (int) (gradesList % 10);
    }

    public static void main(String[] args) {
        String currentWeekNum;

        Scanner scanner = new Scanner(System.in);

        int weekCounter = 1;

        long gradesList = 0;

        int currGrade;

        while (!((currentWeekNum = scanner.nextLine()).equals("42"))) {
            if (!currentWeekNum.equals("Week " + weekCounter++)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            gradesList *= 10;
            int tempMinGrade = 0;
            for (int i = 0; i < 5; i++) {
                currGrade = scanner.nextInt();
                tempMinGrade = (tempMinGrade == 0) ?
                        currGrade :
                        (currGrade < tempMinGrade) ? currGrade : tempMinGrade;
            }
            gradesList += tempMinGrade;
            scanner.nextLine();
        }
        for (int i = 1; i < weekCounter; i++) {
            printWeeklyGraph(i, getDigit(gradesList, weekCounter, i));
        }
    }
}
