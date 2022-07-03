public class OpenShowRequests implements Command {
    UserFriends userFriends;

    public OpenShowRequests(UserFriends userFriends) {
        this.userFriends = userFriends;
    }

    @Override
    public void execute() throws FriendsException {
        userFriends.showFriendRequests();
    }

}
