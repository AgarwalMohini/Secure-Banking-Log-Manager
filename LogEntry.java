package Project1;

import java.time.LocalDateTime;

public class LogEntry {

    private static int counter = 1;

    private int logId;
    private String accountNumber;
    private ActionType actionType;
    private double amount;
    private LocalDateTime timestamp;
    private Status status;

    public LogEntry(String accountNumber, ActionType actionType, double amount, Status status) {
        this.logId = counter++;
        this.accountNumber = accountNumber;
        this.actionType = actionType;
        this.amount = amount;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

    public String getAccountNumber() { return accountNumber; }
    public ActionType getActionType() { return actionType; }
    public double getAmount() { return amount; }

    @Override
    public String toString() {
        return "LogID: " + logId +
                "  Account: " + accountNumber +
                "  Action: " + actionType +
                "  Amount: " + amount +
                "  Status: " + status +
                "  Time: " + timestamp;
    }
}
