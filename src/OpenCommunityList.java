public class OpenCommunityList implements Command {
    Community community;

    public OpenCommunityList(Community community) {
        this.community = community;
    }

    @Override
    public void execute() throws CommunityException {
        community.showCommunity();
    }

}
