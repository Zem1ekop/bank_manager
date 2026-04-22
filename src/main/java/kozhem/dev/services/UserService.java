package kozhem.dev.services;

import kozhem.dev.exceptions.UserAlreadyExistsException;
import kozhem.dev.exceptions.UserNotFoundException;
import kozhem.dev.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//    UserService : Сервис для управления пользователями. Содержит методы для создания пользователя,

//    поиска пользователя по ID и получения списка всех пользователей.

@Service
public class UserService {

    private Integer uniqueUserId = 1;
    private List<User> users = new ArrayList<>();
    private Set<String> createdLogins = new HashSet<>();

    public UserService() {
        System.out.println("UserService создан");
    }

    public User createUser(String login) {
        if (!createdLogins.add(login)) {
            throw new UserAlreadyExistsException(login);
        }
        User user = new User(uniqueUserId, login);
        users.add(user);
        uniqueUserId++;
        return user;
    }


    public User findUserById(Integer id) {
        for (User user : users) {
            if (user.getId().equals(id)) return user;
        }
        throw  new UserNotFoundException(id);
    }

    public Set<String> getCreatedLogins() {
        return createdLogins;
    }

    public List<User> getUsers() {
        return users;
    }
}





