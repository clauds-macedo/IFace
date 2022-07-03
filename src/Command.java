public interface Command {
    public void execute() throws AccountException, FeedException, FriendsException, CommunityException;
}
