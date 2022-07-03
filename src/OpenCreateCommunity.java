public class OpenCreateCommunity implements Command {
    Community community;

    public OpenCreateCommunity(Community community) {
        this.community = community;
    }

    @Override
    public void execute() throws CommunityException {
        community.createCommunity();
    }

}
