package Project1;

import java.util.*;

public class LogManager {

    private List<LogEntry> allLogs = new ArrayList<>();
    private Map<String, List<LogEntry>> accountLogs = new HashMap<>();

    // Point 1: Add Log
    public void addLog(LogEntry log) {
        allLogs.add(log);

        accountLogs.putIfAbsent(log.getAccountNumber(), new ArrayList<>());
        accountLogs.get(log.getAccountNumber()).add(log);
    }

    // Point 2: Get Logs by Account
    public List<LogEntry> getLogsByAccount(String accountNumber) {
        return accountLogs.getOrDefault(accountNumber, new ArrayList<>());
    }

    // Point 3: Recent N Logs
    public List<LogEntry> getRecentLogs(int n) {
        List<LogEntry> result = new ArrayList<>();
        int size = allLogs.size();

        for (int i = size - 1; i >= 0 && result.size() < n; i--) {
            result.add(allLogs.get(i));
        }
        return result;
    }

    // Point 4: Suspicious Activity
    public List<LogEntry> detectSuspicious() {

        List<LogEntry> suspicious = new ArrayList<>();

        for (LogEntry log : allLogs) {

            // Rule 1: Withdrawal > 50000
            if (log.getActionType() == ActionType.WITHDRAW &&
                    log.getAmount() > 50000) {
                suspicious.add(log);
            }

            // Rule 2: More than 3 FAILED_LOGIN in last 5 logs
            if (log.getActionType() == ActionType.FAILED_LOGIN) {

                List<LogEntry> accountList =
                        accountLogs.get(log.getAccountNumber());

                int count = 0;
                int start = Math.max(0, accountList.size() - 5);

                for (int i = start; i < accountList.size(); i++) {
                    if (accountList.get(i).getActionType() ==
                            ActionType.FAILED_LOGIN) {
                        count++;
                    }
                }

                if (count > 3) {
                    suspicious.add(log);
                }
            }
        }

        return suspicious;
    }

    // Point 5: Search by Action Type
    public List<LogEntry> searchByAction(ActionType actionType) {

        List<LogEntry> result = new ArrayList<>();

        for (LogEntry log : allLogs) {
            if (log.getActionType() == actionType) {
                result.add(log);
            }
        }
        return result;
    }
}
