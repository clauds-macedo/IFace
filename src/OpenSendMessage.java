public class OpenSendMessage implements Command {
    UserInbox userInbox;
    UserFriends userFriends;
    AccountManagement accountManagement;

    public OpenSendMessage(UserFriends userFriends, UserInbox userInbox, AccountManagement accountManagement) {
        this.userInbox = userInbox;
        this.userFriends = userFriends;
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws FriendsException {
        userInbox.dataForSendingMessages(userFriends, accountManagement.getLoggedInUser());
    }

}
