public class OpenSendRequest implements Command {
    UserFriends userFriends;

    public OpenSendRequest(UserFriends userFriends) {
        this.userFriends = userFriends;
    }

    @Override
    public void execute() throws AccountException {
        userFriends.sendRequestToAUser();
    }

}
