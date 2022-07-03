public class OpenSendMessage implements Command {
    User user;
    UserFriends userFriends;
    AccountManagement accountManagement;

    public OpenSendMessage(UserFriends userFriends, User user, AccountManagement accountManagement) {
        this.user = user;
        this.userFriends = userFriends;
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws FriendsException {
        user.dataForSendingMessages(userFriends, accountManagement.getLoggedInUser());
    }

}
