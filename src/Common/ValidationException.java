package Common;

import java.util.List;

public class ValidationException extends Exception {
    private final List<String> errorMessages;

    public ValidationException(List<String> errorMessages) {
        super(String.join("; ", errorMessages));
        this.errorMessages = errorMessages;
    }

    public List<String> getErrorMessages() {
        return this.errorMessages;
    }
}
