import java.util.HashMap;

public class OpenRegister implements Command
{
    AccountManagement accountManagement;

    public OpenRegister(AccountManagement accountManagement) {
        this.accountManagement = accountManagement;
    }

    @Override
    public void execute() throws AccountException {
        accountManagement.register();
    }

}
