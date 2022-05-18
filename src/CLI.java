import java.util.InputMismatchException;
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
        boolean isInputValid = false;
        while (true) {
            if (!isLoggedIn) {
                System.out.println("1: Registrar\n" +
                        "2: Logar\n" +
                        "3: Database");
                try {
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
                catch (InputMismatchException e) {
                    System.out.println("Input invalido. Digite um numero, nao um caractere. \nDigite novamente.");
                    scanner.next();
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
                    accountManagement.selectOptionForChangingAccountInfo();
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

                    try {
                        community.addCommunity(
                                communityName,
                                communityDescription,
                                accountManagement.getLoggedInUser());
                    } catch (CommunityException e) {
                        System.out.println(e.getMessage());
                    }
                }

                else if (OPTION == 6) {
                    community.showCommunity();
                }

                else if (OPTION == 7) {
                    try {
                        community.addMember(accountManagement.getLoggedInUser());
                    }
                    catch (CommunityException e) {
                        System.out.println(e.userIsMember());
                    }
                }

                else if (OPTION == 8) {
                    try {
                        user.dataForSendingMessages(userFriends, accountManagement.getLoggedInUser());
                    } catch(FriendsException e) {
                        System.out.println(e.userIsNotYourFriend());
                    }
                }
                else if (OPTION == 9) {
                    try {
                        user.showMessages(accountManagement.getLoggedInUser(), userFriends);
                    } catch(FriendsException e) {
                        System.out.println(e.emptyFriendlist());
                    }
                }
                else if (OPTION == 10) {
                    user.showFeed(userFriends, accountManagement.getLoggedInUser());
                }
                else if (OPTION == 11) {
                    user.dataForPostOnFeed(accountManagement.getLoggedInUser());
                }
                else if (OPTION == 12) {
                    database.
                            deleteUserInfo(accountManagement.getLoggedInUser(), userFriends, user, community);
                    isLoggedIn = false;
                }
            }
        }
    }
}
