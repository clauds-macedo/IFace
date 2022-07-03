public class CommandController {
    Command slot;

    public CommandController() {}

    public void setCommand(Command command) {
        slot = command;
    }

    public void commandWasSet() throws FriendsException, AccountException, FeedException, CommunityException {
        slot.execute();
    }
}
