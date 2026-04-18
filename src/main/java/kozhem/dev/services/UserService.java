package kozhem.dev.services;

import kozhem.dev.model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//    UserService : Сервис для управления пользователями. Содержит методы для создания пользователя,
//    поиска пользователя по ID и получения списка всех пользователей.
@Service
public class UserService {

    private static Integer unique_user_id = 1;
    private static List<User> users = new ArrayList<>();
    private static Set<String> created_logins = new HashSet<>();

    public UserService() {
        System.out.println("UserService создан");
    }

    public User createUser(String login) {
        User user = new User(unique_user_id, login);
        users.add(user);
        unique_user_id++;
        return user;
    }

    public User findUserById(Integer id) {
        User result = null;
        for (User user : users) {
            if (user.getId() == id) result = user;
        }
        return result;
    }

    public static Set<String> getCreated_logins() {
        return created_logins;
    }

    public static List<User> getUsers() {
        return users;
    }
}





