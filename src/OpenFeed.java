public class OpenFeed implements Command {
    User user;
    UserFriends userFriends;
    AccountManagement accountManagement;

    public OpenFeed(UserFriends userFriends, User user, AccountManagement accountManagement) {
        this.user = user;
        this.userFriends = userFriends;
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws FriendsException {
        user.showFeed(userFriends, accountManagement.getLoggedInUser());
    }

}
