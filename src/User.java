public class User {

    public String username, password, nickname;
    private static String loggedInUser;
    Message message;

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public static void setLoggedInUser(String newLoggedInUser) {
        loggedInUser = newLoggedInUser;
    }

    public static String getLoggedInUser() {
        return loggedInUser;
    }

}
