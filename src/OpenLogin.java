public class OpenLogin implements Command {
    AccountManagement accountManagement;

    public OpenLogin(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws AccountException {
        accountManagement.login();
    }

}
