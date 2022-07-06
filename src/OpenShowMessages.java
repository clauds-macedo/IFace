public class OpenShowMessages implements Command {
    UserInbox userInbox;
    UserFriends userFriends;
    AccountManagement accountManagement;

    public OpenShowMessages(UserFriends userFriends, UserInbox userInbox, AccountManagement accountManagement) {
        this.userInbox = userInbox;
        this.userFriends = userFriends;
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws FriendsException {
        userInbox.showMessages(accountManagement.getLoggedInUser(), userFriends);
    }

}
