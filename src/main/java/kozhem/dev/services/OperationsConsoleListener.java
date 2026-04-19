package kozhem.dev.services;

import kozhem.dev.model.Account;
import kozhem.dev.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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

        String new_login;
        String input;
        while (isRunning) {

            System.out.println();
            System.out.println("Please enter one of operation type:");
            input = scanner.next();

            switch (input) {
                case user_create:

                    System.out.println("Enter login for new user:");
                    new_login = scanner.next();

                    if (UserService.getCreatedLogins().add(new_login)) {

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
                    UserService.getUsers().forEach(System.out::println);
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




