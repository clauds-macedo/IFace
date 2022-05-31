public class CommunityException extends Exception {

    String message;
    public CommunityException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return "CommunityException: " + message;
    }

}
