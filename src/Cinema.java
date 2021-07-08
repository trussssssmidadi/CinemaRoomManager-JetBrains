import java.util.Objects;
import java.util.Scanner;

public class Cinema {
    public static Scanner sc = new Scanner(System.in);
    public static String[][] arr;
    public static int rows;
    public static int seats;
    public static int chsRow;
    public static int chsSeat;
    public static double countPercentage;
    public static int countTickets;
    public static int countCurrentIncome;
    public static int income;
    public static boolean end;



    public static void fillScheme() {
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        seats = sc.nextInt();
        arr = new String[rows + 1][seats + 1];
    }

    public static void sortScheme() {
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                arr[i][0] = Integer.toString(i);
                arr[0][j] = Integer.toString(j);
                arr[i][j] = "S";
                arr[0][0] = " ";
            }
        }
    }
    public static void printScheme() {
        System.out.println("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static void chooseSeat() {
        boolean checkIf = false;
        while (!checkIf) {
            try {
                System.out.println("Enter a row number:");
                chsRow = sc.nextInt();
                System.out.println("Enter a seat number in that row:");
                chsSeat = sc.nextInt();

                if (Objects.equals(arr[chsRow][chsSeat], "B")) {
                    System.out.println("That ticket has already been purchased!");
                    checkIf = false;

                } else if (arr[chsRow][chsSeat].equals(" ")) {
                    System.out.println("Wrong input!");
                    checkIf = false;

                } else {
                    arr[chsRow][chsSeat] = "B";
                    checkIf = true;
                }
            } catch (Exception e) {
                System.out.println("Wrong input!");
            }
        }
    }
    public static void calculateBoughtTickets() {
        if (arr[chsRow][chsSeat].equals("B")) {
            countTickets++;
        }
    }
    public static void calculatePercentage() {
        if (countCurrentIncome != 0) {
            countPercentage = countTickets * 100.0f / (rows * seats);
        }
    }
    public static void calculateIncome() {
        int totalNumberOfSeats = rows * seats;

        if (totalNumberOfSeats <= 60) {
            income = totalNumberOfSeats * 10;
        } else {
            int frontRows = rows / 2;
            int frontSeats = frontRows * seats;
            int backRows = rows - frontRows;
            int backSeats = backRows * seats;
            income = (frontSeats * 10) + (backSeats * 8);
        }
    }
    public static void calculatePrice() {
        if (rows * seats <= 60 || chsRow <= rows / 2) {
            System.out.println("Ticket price: $" + 10);
            countCurrentIncome += 10;
        } else {
            System.out.println("Ticket price: $" + 8);
            countCurrentIncome += 8;
        }
    }
    public static void menu() {
        end = false;
        System.out.println("\n" + "1. Show the seats" +
                "\n" + "2. Buy a ticket" +
                "\n" + "3. Statistics" +
                "\n" + "0. Exit");
        int menuChoice = sc.nextInt();

        if (menuChoice == 1) {
            printScheme();

        } else if (menuChoice == 2) {
            chooseSeat();
            calculatePrice();
            calculateBoughtTickets();

        } else if (menuChoice == 3) {
            calculateIncome();
            calculatePercentage();

            System.out.printf("Number of purchased tickets: %d%n", + countTickets);
            System.out.printf("Percentage: %.2f%% %n", countPercentage);
            System.out.printf("Current income: $%d%n", countCurrentIncome);
            System.out.printf("Total income: $%d%n", income);

        } else if (menuChoice == 0) {
            end = true;
        }
    }
    public static void main(String[] args) {
        fillScheme();
        sortScheme();
        do {
            menu();
        } while (!end);
    }
}
