package kozhem.dev.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    /** уникальный идентификатор пользователя */
    private Integer id;

    /** логин пользователя */
    private String login;

    /** список счетов пользователя */
    private List<Account> accountList;

    public User(Integer id, String login) {
        this.id = id;
        this.login = login;
        this.accountList = new ArrayList<>();
    }

    // Геттеры и сеттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", accountList=" + accountList +
                '}';
    }
}