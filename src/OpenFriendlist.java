public class OpenFriendlist implements Command {
    UserFriends userFriends;

    public OpenFriendlist(UserFriends userFriends) {
        this.userFriends = userFriends;
    }

    @Override
    public void execute() throws FriendsException {
        userFriends.showFriendList();
    }

}
