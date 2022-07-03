public class OpenEditInfo implements Command {
    AccountManagement accountManagement;

    public OpenEditInfo(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws AccountException {
        accountManagement.selectOptionForChangingAccountInfo();
    }

}
