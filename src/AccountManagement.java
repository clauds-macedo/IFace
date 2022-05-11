import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagement extends Database {
    private static String loggedInUser;
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    public String getLoggedInUser() {
        return loggedInUser;
    }

    public boolean login(){
        System.out.println("Login:");
        String username = scanner.next();

        System.out.println("Senha:");
        String password = scanner.next();
        if (!checkAccountExistence(username) || !compareWithUserDatabase(username, password)) {
            System.out.println("Usuário ou senha inválidos.");
            return false;
        }

        if (compareWithUserDatabase(username, password)) {
            this.loggedInUser = username;
            System.out.println("Logado com sucesso.\nSeja bem vindo, " + username);
            return true;
        }

        return false;
    }

    void register() {
        System.out.println("Digite o login");
        String login = scanner.next();

        System.out.println("Digite a senha");
        String password = scanner.next();

        System.out.println("Digite o apelido");
        String nickname = scanner.next();
        if (checkAccountExistence(login)) {
            System.out.println("O usuário já existe no banco de dados.");
            return;
        }
        insertUserOnDatabase(new User(login, password, nickname));
        System.out.println("Registrado com sucesso!");
    }

    public void dataForChangingAccountInfo() {
        System.out.println("O que deseja mudar?\n"
                + "1: Senha\n"
                + "2: Apelido\n");
        int toChange = scanner.nextInt();
        if (toChange < 0 && toChange > 3) {
            System.out.println("Opção invalida.");
            return;
        }
        System.out.println("Digite o novo dado");
        String newData = scanner.next();
        changeAccountInfo(loggedInUser,
                toChange,
                newData);
    }

    public void changeAccountInfo(String username, int index, String newInfo){
        String[] newUserData = {usersDatabase.get(username).get(0),
                usersDatabase.get(username).get(1)};
        newUserData[index - 1] = newInfo;

        ArrayList<String> wrapNewData = new ArrayList<>();

        wrapNewData.add(newUserData[0]);
        wrapNewData.add(newUserData[1]);

        usersDatabase.put(username, wrapNewData);
        System.out.println("Dado alterado com sucesso.");
    }
}
