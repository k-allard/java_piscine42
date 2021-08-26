import java.util.Scanner;

public class Program {

    static int MAX_CODE_VALUE = 65535;
    static int MAX_NUM_OF_CHARS = 10;
    static int MAX_HEIGHT_OF_CHART = 10;
    static int POS_CHAR = 0;
    static int POS_COUNT = 1;
    static int POS_DRAW = 2;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String line = scanner.nextLine();

        int size = line.length();

        if (size > 0) {
            int[][] stats = getCharsStats(line, size);
            int[][] sortedStats = sortCharsStats(stats);
            int printSize = sortedStats.length;
            if (printSize > MAX_NUM_OF_CHARS)
                printSize = MAX_NUM_OF_CHARS;
            printCharsStat(sortedStats, printSize);
        }
    }

    static int[] resetCharsCount() {
        int[] charsCount = new int[MAX_CODE_VALUE];

        for (int i = 0; i < MAX_CODE_VALUE; i++) {
            charsCount[i] = 0;
        }

        return charsCount;
    }

    static int[] getCharsCount(String inputString, int size) {
        int[] charsCount = resetCharsCount();

        char[] inputArray = inputString.toCharArray();

        for (int i = 0; i < size; i++) {
            char ch = inputArray[i];
            charsCount[ch]++;
        }

        return charsCount;
    }

    static int[][] getCharsStats(String inputString, int size) {

        int[] charsCount = getCharsCount(inputString, size);

        int[][] charsStatsTemp = new int[size][2];

        int charIndex = 0;

        for (int i = 0; i < MAX_CODE_VALUE; i++) {
            if (charsCount[i] > 0) {
                charsStatsTemp[charIndex][POS_CHAR] = i;
                charsStatsTemp[charIndex][POS_COUNT] = charsCount[i];
                charIndex++;
            }
        }

        int[][] charsStats = new int[charIndex][2];

        for (int i = 0; i < charIndex; i++) {
            charsStats[i][POS_CHAR] = charsStatsTemp[i][POS_CHAR];
            charsStats[i][POS_COUNT] = charsStatsTemp[i][POS_COUNT];
        }

        return charsStats;
    }

    static boolean isLess(int[] item1, int[] item2) {
        if (item1[POS_COUNT] < item2[POS_COUNT])
            return true;

        if (item1[POS_COUNT] == item2[POS_COUNT]) {
            char char1 = (char) item1[POS_CHAR];
            char char2 = (char) item2[POS_CHAR];
            return char1 > char2;
        }

        return false;
    }

    static int[][] sortCharsStats(int[][] charsStats) {
        for (int i = 1; i < charsStats.length; i++) {
            for (int j = i; j > 0 && isLess(charsStats[j - 1], charsStats[j]); j--) {
                int tmpChar = charsStats[j - 1][POS_CHAR];
                int tmpCount = charsStats[j - 1][POS_COUNT];
                charsStats[j - 1][POS_CHAR] = charsStats[j][POS_CHAR];
                charsStats[j - 1][POS_COUNT] = charsStats[j][POS_COUNT];
                charsStats[j][POS_CHAR] = tmpChar;
                charsStats[j][POS_COUNT] = tmpCount;
            }
        }

        return charsStats;
    }

    static int[][] preparePrint(int[][] charsStats, int printSize) {
        int[][] stats = new int[printSize][3];

        int max = charsStats[0][POS_COUNT];

        for (int i = 0; i < printSize; i++) {
            stats[i][POS_CHAR] = charsStats[i][POS_CHAR];
            stats[i][POS_COUNT] = charsStats[i][POS_COUNT];
            stats[i][POS_DRAW] = ((MAX_HEIGHT_OF_CHART * charsStats[i][POS_COUNT])) / (max);
        }

        return stats;
    }

    static void printCharsStat(int[][] sortedCharsStats, int printSize) {
        int[][] stats = preparePrint(sortedCharsStats, printSize);

        String[][] print = new String[printSize][12];

        for (int i = 0; i < printSize; i++) {
            print[i][0] = "  " + (char) (stats[i][POS_CHAR]);
            for (int j = 0; j < stats[i][POS_DRAW]; j++) {
                print[i][1 + j] = "  #";
            }
            if (stats[i][POS_COUNT] >= 100) {
                print[i][1 + stats[i][POS_DRAW]] = "" + stats[i][POS_COUNT];
            }
            else if (stats[i][POS_COUNT] >= 10) {
                print[i][1 + stats[i][POS_DRAW]] = " " + stats[i][POS_COUNT];
            }
            else {
                print[i][1 + stats[i][POS_DRAW]] = "  " + stats[i][POS_COUNT];
            }
            for (int j = 2 + stats[i][POS_DRAW]; j < 12; j++) {
                print[i][j] = "";
            }
        }

        for (int j = 11; j >= 0; j--) {
            for (int i = 0; i < printSize; i++) {
                System.out.print(print[i][j]);
            }
            System.out.println();
        }
    }
}
