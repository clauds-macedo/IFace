import java.util.Scanner;

public class CLI {

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    AccountManagement accountManagement = new AccountManagement();
    UserFriends userFriends = new UserFriends();
    Community community = new Community();
    User user = new User();
    Database database = new Database();
    boolean isLoggedIn = false;

    public void showCLI() {
        database.insertUserOnDatabase(new User("claudemir", "123", "1"));
        database.insertUserOnDatabase(new User("meteu", "essa", "1"));
        database.insertUserOnDatabase(new User("baldoino", "123", "1"));

        while (true) {
            if (!isLoggedIn) {
                System.out.println("1: Registrar\n" +
                        "2: Logar\n" +
                        "3: Database");
                int OPTION = scanner.nextInt();
                if (OPTION == 999) {
                    break;
                }
                else if (OPTION == 1) {
                    accountManagement.register();
                }
                else if (OPTION == 2) {
                    if (accountManagement.login()){
                        isLoggedIn = true;
                    }
                }
                else if (OPTION == 3) {
                    database.showUserDatabase();
                }
            }
            else {
                System.out.println("User: " + accountManagement.getLoggedInUser());

                System.out.println("Escolha uma opção:\n"
                        + "1: Editar perfil\n"
                        + "2: Adicionar amigo\n"
                        + "3: Mostrar pedidos de amizade\n"
                        + "4: Ver lista de amigos\n"
                        + "5: Criar comunidade\n"
                        + "6: Ver lista de comunidades\n"
                        + "7: Entrar em uma comunidade\n"
                        + "8: Enviar mensagem para um amigo\n"
                        + "9: Ver caixa de entrada\n"
                        + "10: Ver feed de notícias\n"
                        + "11: Adicionar postagem ao feed\n"
                        + "12: Remover sua conta\n"
                        + "999: Logout");

                int OPTION = scanner.nextInt();

                if (OPTION == 999) {
                    this.isLoggedIn = false;
                }

                else if (OPTION == 1) {
                    accountManagement.dataForChangingAccountInfo();
                }

                else if (OPTION == 2) {
                    userFriends.sendRequestToAUser();
                }

                else if (OPTION == 3) {
                    userFriends.showFriendRequests();
                }

                else if (OPTION == 4) {
                    this.userFriends.showFriendList();
                }

                else if (OPTION == 5) {
                    System.out.println("Digite o nome da comunidade que deseja criar");
                    String communityName = scanner.next();

                    System.out.println("Digite a descrição da comunidade que deseja criar");
                    String communityDescription = scanner.next();

                    community.addCommunity(
                            communityName,
                            communityDescription,
                            accountManagement.getLoggedInUser());
                }

                else if (OPTION == 6) {
                    community.showCommunity();
                }

                else if (OPTION == 7) {
                    community.addMember(accountManagement.getLoggedInUser());
                }

                else if (OPTION == 8) {
                    user.dataForSendingMessages(userFriends, accountManagement.getLoggedInUser());
                }
                else if (OPTION == 9) {
                    user.showMessages(accountManagement.getLoggedInUser(), userFriends);
                }
                else if (OPTION == 10) {
                    user.showFeed(userFriends, accountManagement.getLoggedInUser());
                }
                else if (OPTION == 11) {
                    user.dataForPostOnFeed(accountManagement.getLoggedInUser());
                }
                else if (OPTION == 12) {
                    database.
                            deleteUserInfo(accountManagement.getLoggedInUser(), userFriends, user);
                    isLoggedIn = false;
                }
           }
        }
    }
}
