public class OpenEnterInCommunity implements Command {
    Community community;
    String user;
    public OpenEnterInCommunity(Community community, String user) {
        this.community = community;
        this.user = user;
    }

    @Override
    public void execute() throws CommunityException {
        community.addMember(user);
    }

}
