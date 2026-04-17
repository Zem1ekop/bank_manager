package kozhem.dev.services;


import kozhem.dev.model.Account;
import org.springframework.stereotype.Service;

//AccountService : Сервис для управления счетами. Содержит методы для создания счета,
// пополнения и снятия средств, перевода средств между счетами и закрытия счета.
@Service
public class AccountService {
    private UserService userService;

    private static Integer unique_account_id = 0;

    public AccountService(Account account, UserService userService) {
        this.userService = userService;
    }

    public Account createAccount() {
        Account account = new Account(unique_account_id, )

    }


    public static Integer getUnique_account_id() {
        return unique_account_id;
    }
}
