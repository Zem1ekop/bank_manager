package kozhem.dev.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException(String s) {

        super("Пользователь с логином " + s + " уже создан!");
    }
}
