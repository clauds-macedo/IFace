public class CommunityException extends Exception {

    @Override
    public String getMessage() {
        return "A comunidade ja existe.";
    }

    public String userIsMember() {
        return "O usuario ja existe nessa comunidade.";
    }

}
