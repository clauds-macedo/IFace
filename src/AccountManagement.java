import java.util.Scanner;

public class AccountManagement extends Database {
    private static String loggedInUser;
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    public String getLoggedInUser() {
        return loggedInUser;
    }

    public boolean login(){
        String username = scanner.next();
        System.out.println("Senha:");
        String password = scanner.next();
        System.out.println("Login:");
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
}
