package Project1;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        LogManager manager = new LogManager();

        while (true) {

            System.out.println("\n1. Add Log");
            System.out.println("2. Get Logs by Account");
            System.out.println("3. Get Recent Logs");
            System.out.println("4. Detect Suspicious Activity");
            System.out.println("5. Search by Action");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Account Number: ");
                    String acc = sc.next();

                    System.out.println("Select Action:");
                    for (ActionType a : ActionType.values()) {
                        System.out.println(a);
                    }

                    ActionType action =
                            ActionType.valueOf(sc.next());

                    System.out.print("Amount: ");
                    double amount = sc.nextDouble();

                    System.out.println("Status (SUCCESS/FAILED): ");
                    Status status =
                            Status.valueOf(sc.next());

                    manager.addLog(
                            new LogEntry(acc, action, amount, status)
                    );

                    System.out.println("Log Added!");
                    break;

                case 2:
                    System.out.print("Enter Account: ");
                    String account = sc.next();

                    List<LogEntry> logs =
                            manager.getLogsByAccount(account);

                    logs.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Enter N: ");
                    int n = sc.nextInt();

                    manager.getRecentLogs(n)
                            .forEach(System.out::println);
                    break;

                case 4:
                    manager.detectSuspicious()
                            .forEach(System.out::println);
                    break;

                case 5:
                    System.out.println("Enter Action Type:");
                    ActionType type =
                            ActionType.valueOf(sc.next());

                    manager.searchByAction(type)
                            .forEach(System.out::println);
                    break;

                case 6:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}
