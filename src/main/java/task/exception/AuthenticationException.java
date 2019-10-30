package task.exception;

import java.text.MessageFormat;

public class AuthenticationException extends ApplicationException {

    public AuthenticationException(String message, String email, String password) {
        super(getFormattedString(message, (Object) new String[]{email, password}));
    }

    public AuthenticationException(String s, String nickanme) {
        super(getFormattedString(s, (Object) new String[]{nickanme}));
    }

    private static String getFormattedString(String message, Object... args){
        return MessageFormat.format(message, args);
    }
}
