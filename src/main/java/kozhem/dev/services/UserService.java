package kozhem.dev.services;

import kozhem.dev.model.User;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//    UserService : Сервис для управления пользователями. Содержит методы для создания пользователя,
//    поиска пользователя по ID и получения списка всех пользователей.
@Service
public class UserService {
    private List<User> users;
    private AccountService accountService;

    private static Integer unique_user_ID = 0;
    private Set<String> created_logins = new HashSet<>();

    public void createUser(String login) {
        User user = new User(unique_user_ID, login);
        unique_user_ID++;
        created_logins.add(login);
    }


}
