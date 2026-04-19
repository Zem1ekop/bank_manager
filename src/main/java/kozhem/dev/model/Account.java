package kozhem.dev.model;

import org.springframework.beans.factory.annotation.Value;

public class Account {

    /** уникальный идентификатор счета */
    private Integer accountId;

    /** идентификатор пользователя, владельца счета */
    private Integer userId;

    /** текущий баланс счета */
    private Integer moneyAmount;

    public Account(Integer accountId, Integer userId, Integer moneyAmount) {
        this.accountId = accountId;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }

    public Integer getAccountId() {
        return accountId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", userId=" + userId +
                ", moneyAmount=" + moneyAmount +
                '}';
    }
}
