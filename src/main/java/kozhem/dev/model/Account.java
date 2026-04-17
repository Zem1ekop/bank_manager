package kozhem.dev.model;

public class Account {

    /** уникальный идентификатор счета */
    private Integer id;

    /** идентификатор пользователя, владельца счета */
    private Integer userId;

    /** текущий баланс счета */
    private Integer moneyAmount;

    /** флаг, указывающий, активен ли счёт */
   // private Boolean isActive;

    public Account(Integer id, Integer userId, Integer moneyAmount) {
        this.id = id;
        this.userId = userId;
        this.moneyAmount = moneyAmount;
    }


}
