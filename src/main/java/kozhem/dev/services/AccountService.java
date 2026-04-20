package kozhem.dev.services;


import kozhem.dev.model.Account;
import kozhem.dev.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

//AccountService : Сервис для управления счетами. Содержит методы для создания счета,
// пополнения и снятия средств, перевода средств между счетами и закрытия счета.
@Service
public class AccountService {

    private final UserService userService;
    private Integer unique_account_id = 1;
    private final Integer defaultAmount;
    private Map<Integer,Account> accounts;

    public AccountService(UserService userService, @Value("${account.default-amount}") Integer defaultAmount, Map<Integer, Account> accounts) {
        this.userService = userService;
        this.defaultAmount = defaultAmount;
        this.accounts = new HashMap<>();
        System.out.println("AccountService создан");
    }

    public Account createAccount(Integer userId) {
        Account account = new Account(unique_account_id++, userId, defaultAmount);
        accounts.put(account.getAccountId(), account);
        return account;
    }

    public boolean closeAccount(Integer id) {
        for (User user : userService.getUsers()) {
            if (user.getAccountList().removeIf(acc -> acc.getAccountId().equals(id))) {
                return true;
            }
        }
        return false;
    }

    public boolean deposit(Integer id, Integer amount) {
        for (User user : userService.getUsers()) {
            for (Account account : user.getAccountList()) {
                if (account.getAccountId().equals(id)) {
                    account.setMoneyAmount(account.getMoneyAmount() + amount);
                    return true;
                }
            }
        }
        return false;
    }


    public boolean transfer(Integer source, Integer target, Integer amount) {
        Account sourceAcc = null;
        Account targetAcc = null;
        for (User user : userService.getUsers()) {
            for (Account account : user.getAccountList()) {
                if (account.getAccountId().equals(source)) {
                    sourceAcc = account;

                } else if (account.getAccountId().equals(target)) {
                    targetAcc = account;
                }
            }
        }
        if ((sourceAcc != null) && (targetAcc != null) && (sourceAcc.getMoneyAmount() >= amount)) {
            sourceAcc.setMoneyAmount(sourceAcc.getMoneyAmount() - amount);
            targetAcc.setMoneyAmount(targetAcc.getMoneyAmount() + amount);
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(Integer id, Integer amount) {
        for (User user : userService.getUsers()) {
            for (Account account : user.getAccountList()) {
                if (account.getAccountId().equals(id)) {
                    if (account.getMoneyAmount() >= amount) {
                        account.setMoneyAmount(account.getMoneyAmount() - amount);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Integer getUnique_account_id() {
        return unique_account_id;
    }

    public Map<Integer, Account> getAccounts() {
        return accounts;
    }
}
