public class OpenPostOnFeed implements Command {
    User user;
    AccountManagement accountManagement;

    public OpenPostOnFeed(User user, AccountManagement accountManagement) {
        this.user = user;
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws FeedException {
        user.dataForPostOnFeed(accountManagement.getLoggedInUser());
    }

}
