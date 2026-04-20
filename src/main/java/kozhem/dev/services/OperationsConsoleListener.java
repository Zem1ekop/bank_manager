package kozhem.dev.services;

import kozhem.dev.model.Account;
import kozhem.dev.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Компонент, который слушает консольный ввод и исполняет
 * соответствующие команды, используя сервисы.
 */
@Component
public class OperationsConsoleListener {

    private static final String account_create = "ACCOUNT_CREATE";
    private final String show_all_users = "SHOW_ALL_USERS";
    private final String account_close = "ACCOUNT_CLOSE";
    private final String account_withdraw = "ACCOUNT_WITHDRAW";
    private final String account_deposit = "ACCOUNT_DEPOSIT";
    private final String account_transfer = "ACCOUNT_TRANSFER";
    private final String user_create = "USER_CREATE";
    private final String exit = "EXIT";

    private UserService userService;
    private AccountService accountService;
    private Scanner scanner = new Scanner(System.in);


    public OperationsConsoleListener(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
        System.out.println("OperationConsoleListener создан");
    }

    public void start() {

        boolean isRunning = true;

        while (isRunning) {

            System.out.println();
            System.out.println("Please enter one of operation type:");

            String input = scanner.next();

            switch (input) {
                case user_create:

                    System.out.println("Enter login for new user:");
                    String new_login = scanner.next();

                    if (userService.getCreatedLogins().add(new_login)) {

                        User user = userService.createUser(new_login);
                        Account account = accountService.createAccount(user.getId());
                        user.getAccountList().add(account);
                        System.out.println("User created: " + user);
                    } else {
                        System.out.println("Пользователь с таким логином уже создан!");
                    }
                    break;

                case account_create:
                    System.out.println("Enter the user id for which to create an account:");

                    int inputId = scanner.nextInt();
                    User user = userService.findUserById(inputId);

                    if (!(user == null)) {
                        Account account = accountService.createAccount(inputId);
                        user.getAccountList().add(account);
                        System.out.printf("New account created with ID: %d for user: %s\n",
                                account.getAccountId(), user.getLogin());
                    } else {
                        System.out.println("User with this id not found");
                    }
                    break;

                case show_all_users:
                    System.out.println("List of all users:");
                    userService.getUsers().forEach(System.out::println);
                    break;

                case account_deposit:
                    System.out.println("Enter account ID:");
                    Integer idToDep = scanner.nextInt();
                    System.out.println("Enter amount to deposit:");
                    Integer amount = scanner.nextInt();
                    if (accountService.deposit(idToDep, amount)) {
                        System.out.printf("Amount %d deposited to account ID: %d", amount, idToDep);
                    } else {
                        System.out.printf("ACCOUNT WITH ID: %d NOT FOUND!\n", idToDep);
                    }
                    break;

                case account_close:
                    System.out.println("Enter account ID to close:");
                    Integer idToDel = scanner.nextInt();
                    if (accountService.closeAccount(idToDel)) {
                        System.out.printf("Account with ID: %d has been closed.\n", idToDel);
                    } else {
                        System.out.printf("ACCOUNT WITH ID: %d NOT FOUND!\n", idToDel);
                    }
                    break;

                case account_transfer:
                    System.out.println("Enter source account ID:");
                    Integer source = scanner.nextInt();
                    System.out.println("Enter target account ID:");
                    Integer target = scanner.nextInt();
                    System.out.println("Enter amount to transfer:");
                    Integer amountTransfer = scanner.nextInt();
                    if (accountService.transfer(source, target, amountTransfer)) {
                        System.out.printf("Amount %d transferred from account ID %d to account ID %d.", amountTransfer, source, target);
                    } else {
                        System.out.println("THIS OPERATION IS NOT POSSIBLE! PLEASE, TRY AGAIN!");
                    }
                    break;

                case exit:
                    isRunning = false;
                    break;

                default:
                    System.out.println("WRONG COMMAND! TRY AGAIN!");
            }


        }
    }
}




