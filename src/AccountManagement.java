import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AccountManagement extends Database {
    private static String loggedInUser;
    public static Boolean isLoggedIn = false;
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    public String getLoggedInUser() {
        return loggedInUser;
    }

    public boolean login() throws AccountException {
        System.out.println("Login:");
        String username = scanner.next();

        System.out.println("Senha:");
        String password = scanner.next();
        if (!compareWithUserDatabase(username, password)) {
            throw new AccountException("O usuário não existe no banco de dados.");
        }

        if (compareWithUserDatabase(username, password)) {
            this.loggedInUser = username;
            System.out.println("Logado com sucesso.\nSeja bem vindo, " + username);
            isLoggedIn = true;
            return true;
        }

        return false;
    }

    Boolean register() throws AccountException {
        System.out.println("Digite o login");
        String login = scanner.next();

        if (loginContainsSpace(login)) throw new AccountException("O login não pode conter espaços.");

        System.out.println("Digite a senha");
        String password = scanner.next();

        System.out.println("Digite o apelido");
        String nickname = scanner.next();

        if (!checkAccountExistence(login)) {
            throw new AccountException("O login já existe.");
        }

        insertUserOnDatabase(new User(login, password, nickname));
        System.out.println("Registrado com sucesso!");
        return true;
    }

    boolean loginContainsSpace(String login) {
        return (login.contains(" "));
    }


    public void selectOptionForChangingAccountInfo() {
        System.out.println("O que deseja mudar?\n"
                + "1: Senha\n"
                + "2: Apelido\n");
        int toChange;
        try {
            toChange = scanner.nextInt();
            typeNewInfo(toChange);
        } catch (InputMismatchException e) {
            System.out.println("Input invalido. Digite um numero, nao um caractere.");
            scanner.next();
        }
    }

    public void typeNewInfo(int toChange) {
        try {
            System.out.println("Digite o novo dado");
            String newData = scanner.next();
            changeAccountInfo(loggedInUser,
                    toChange,
                    newData);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Opcao invalida. Selecione entre 1 ou 2.");
        }
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
