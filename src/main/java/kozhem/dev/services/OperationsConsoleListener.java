package kozhem.dev.services;

import org.springframework.stereotype.Component;

import java.util.Scanner;

/** Компонент, который слушает консольный ввод и исполняет
 *  соответствующие команды, используя сервисы.*/
@Component
public class OperationsConsoleListener {

    private final String account_create = "ACCOUNT_CREATE";
    private final String show_all_users = "SHOW_ALL_USERS";
    private final String account_close = "ACCOUNT_CLOSE";
    private final String account_withdraw = "ACCOUNT_WITHDRAW";
    private final String account_deposit = "ACCOUNT_DEPOSIT";
    private final String account_transfer = "ACCOUNT_TRANSFER";
    private final String user_create = "USER_CREATE";

    private UserService userService;
    private AccountService accountService;
    private Scanner scanner;

    public OperationsConsoleListener(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (scanner.hasNext()) {
            String input = scanner.next();
            switch (input) {
                case user_create:
                    System.out.println("Enter login for new user:");
                    String login = scanner.next();
                    // Предполагаем ввод: USER_CREATE name age
//                    if (scanner.hasNext()) {
//                        String name = scanner.next();
//                        if (scanner.hasNextInt()) {
//                            int age = scanner.nextInt();
//                            userService.createUser(name, age);
//                        }
//                    }
                    break;
                case show_all_users:
                    userService.getAllUsers().forEach(System.out::println);
                    break;
                case account_create:
                    // Предполагаем ввод: ACCOUNT_CREATE userId
                    if (scanner.hasNextLong()) {
                        long userId = scanner.nextLong();
                        accountService.createAccount(userId);
                    }
                    break;
                case account_close:
                    // Предполагаем ввод: ACCOUNT_CLOSE accountId
                    if (scanner.hasNextLong()) {
                        long accountId = scanner.nextLong();
                        accountService.closeAccount(accountId);
                    }
                    break;
                case account_withdraw:
                    // Предполагаем ввод: ACCOUNT_WITHDRAW accountId amount
                    if (scanner.hasNextLong()) {
                        long accountId = scanner.nextLong();
                        if (scanner.hasNextDouble()) {
                            double amount = scanner.nextDouble();
                            accountService.withdraw(accountId, amount);
                        }
                    }
                    break;
                case account_deposit:
                    // Предполагаем ввод: ACCOUNT_DEPOSIT accountId amount
                    if (scanner.hasNextLong()) {
                        long accountId = scanner.nextLong();
                        if (scanner.hasNextDouble()) {
                            double amount = scanner.nextDouble();
                            accountService.deposit(accountId, amount);
                        }
                    }
                    break;
                case account_transfer:
                    // Предполагаем ввод: ACCOUNT_TRANSFER fromAccountId toAccountId amount
                    if (scanner.hasNextLong()) {
                        long fromAccountId = scanner.nextLong();
                        if (scanner.hasNextLong()) {
                            long toAccountId = scanner.nextLong();
                            if (scanner.hasNextDouble()) {
                                double amount = scanner.nextDouble();
                                accountService.transfer(fromAccountId, toAccountId, amount);
                            }
                        }
                    }
                    break;
                default:
                    System.out.println("Неизвестная команда: " + input);
                    break;
            }
        }
    }

    public void stop() {
        scanner.close();
    }
}