public class AccountException extends Exception {

    private String message;

    public AccountException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "AccountException: " + message;
    }

}
