package kozhem.dev.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String s) {
        super("Пользователь с логином " + s + " уже создан!");
    }
}
