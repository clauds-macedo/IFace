public class OpenShowMessages implements Command {
    User user;
    UserFriends userFriends;
    AccountManagement accountManagement;

    public OpenShowMessages(UserFriends userFriends, User user, AccountManagement accountManagement) {
        this.user = user;
        this.userFriends = userFriends;
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws FriendsException {
        user.showMessages(accountManagement.getLoggedInUser(), userFriends);
    }

}
