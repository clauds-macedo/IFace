public class OpenDeleteAccount implements Command {
    Community community;
    User user;
    AccountManagement accountManagement;
    UserFriends userFriends;
    Database database;

    public OpenDeleteAccount(Database database, AccountManagement accountManagement, Community community, User user, UserFriends userFriends) {
        this.community = community;
        this.user = user;
        this.accountManagement = accountManagement;
        this.userFriends = userFriends;
        this.database = database;
    }

    @Override
    public void execute() throws CommunityException {
        database.deleteUserInfo(accountManagement.getLoggedInUser(), userFriends, user, community);
    }

}
