import java.util.*;

public class AccountManagement {

    Database database;
    Scanner scanner;
    User userInfo;

    String username;
    String password;
    String nickname;

    private static String loggedInUser;

    public AccountManagement(){
        this.database = new Database();
        this.scanner = new Scanner(System.in);
    }

    public boolean login(String username, String password){

//        remover comentario (testes)
//        System.out.println("Digite seu login: ");
//        String username = scanner.next();
//
//        System.out.println("Digite sua senha: ");
//        String password = scanner.next();

        if (!userExistsOnDatabase(username) || !compareWithUserDatabase(username, password)) {
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

    boolean compareWithUserDatabase(String userLogin, String userPassword){
        return (this.database.usersHashMap.get(userLogin).get(0).equals(userPassword));
    }

    void register(){

        this.username = scanner.next();

        if (!userExistsOnDatabase(this.username)){
            this.password = scanner.next();
            this.nickname = scanner.next();
            System.out.println("Registrado com sucesso!");
        }
        else {
            System.out.println("O usuário já existe no banco de dados.");
        }

    }

    boolean userExistsOnDatabase(String username) {
        return (Database.usersHashMap.containsKey(username));
    }

    User getNewRegisteredUser(){
        register();
        return new User(this.username, this.password, this.nickname);
    }

    String getLoggedInUser(){
        return this.loggedInUser;
    }

    void editProfile(){
        System.out.println("1: Alterar senha\n2: Alterar apelido\n");
        int index = scanner.nextInt();

        System.out.println("Digite a nova informação: ");
        String newInfo = scanner.next();

        this.database.change_account_info(this.getLoggedInUser(), index, newInfo);
    }



}
