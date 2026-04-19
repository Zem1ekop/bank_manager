package kozhem.dev.services;


import kozhem.dev.model.Account;
import kozhem.dev.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//AccountService : Сервис для управления счетами. Содержит методы для создания счета,
// пополнения и снятия средств, перевода средств между счетами и закрытия счета.
@Service
public class AccountService {

    private final UserService userService;
    private Integer unique_account_id = 1;
    private final Integer defaultAmount;

    public AccountService(UserService userService, @Value("${account.default-amount}") Integer defaultAmount) {
        this.userService = userService;
        this.defaultAmount = defaultAmount;
        System.out.println("AccountService создан");
    }

    public Account createAccount(Integer userId) {
        return new Account(unique_account_id++, userId, defaultAmount);

    }

    public boolean closeAccount(Integer id) {
        for (User user : userService.getUsers()) {
            if (user.getAccountList().removeIf(acc -> acc.getAccountId().equals(id))) {
                return true;
            }
        }
        return false;
    }

    public void deposit(Integer amount) {

    }

    public Integer getUnique_account_id() {
        return unique_account_id;
    }
}
