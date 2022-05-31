public class FriendsException extends Exception {

    String message;

    public FriendsException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "FriendException: " + message;
    }

}
