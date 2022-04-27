import java.util.Scanner;

public class CLI {
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    AccountManagement accountManagement = new AccountManagement();

    boolean isLoggedIn = false;
    public void showCLI() {
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
                    accountManagement.register("claudemir", "123", "clauds");
                }
                else if (OPTION == 2) {
                    if (accountManagement.login()){
                        isLoggedIn = true;
                    }
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
                        + "8: Enviar mensagem\n"
                        + "9: Ver caixa de entrada\n"
                        + "10: Ver feed de notícias\n"
                        + "11: Adicionar postagem ao feed\n"
                        + "12: Alterar dados da conta\n"
                        + "13: Remover sua conta\n"
                        + "999: Logout");

                int OPTION = scanner.nextInt();

                if (OPTION == 999) {
                    this.isLoggedIn = false;
                }

//                else if (OPTION == 1) {
//                    this.accountManagement.editProfile();
//                }

//                else if (OPTION == 2) {
//                    System.out.println("Digite o nome de usuário que quer enviar o convite");
//                    String friendToInvite = scanner.next();
//                    this.userFriends.sendRequestToAUser(friendToInvite);
//                }
//
//                else if (OPTION == 3) {
//                    this.userFriends.showFriendRequests();
//                }
//
//                else if (OPTION == 4) {
//                    this.userFriends.showFriendList();
//                }
//
//                else if (OPTION == 5) {
//                    community.create_community();
//                }
//
//                else if (OPTION == 6) {
//                    community.showCommunities();
//                }
//
//                else if (OPTION == 7) {
//                    community.enterInCommunity();
//                }
//
//                else if (OPTION == 8) {
//                    this.message.sendMessage();
//                }
//
//                else if (OPTION == 9) {
//                    this.message.showMessages();
//                }
               }
            }
        }
    }
