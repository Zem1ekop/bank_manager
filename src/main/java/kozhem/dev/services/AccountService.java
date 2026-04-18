package kozhem.dev.services;


import kozhem.dev.model.Account;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//AccountService : Сервис для управления счетами. Содержит методы для создания счета,
// пополнения и снятия средств, перевода средств между счетами и закрытия счета.
@Service
public class AccountService {
    private static Integer unique_account_id = 1;

    public AccountService() {
        System.out.println("AccountService создан");
    }


    public Account createAccount(Integer userId, Integer moneyAmount) {
        return new Account(unique_account_id++, userId, moneyAmount);

    }


    public static Integer getUnique_account_id() {
        return unique_account_id;
    }
}
