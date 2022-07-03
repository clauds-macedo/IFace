import java.util.*;

public class CLI {

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    AccountManagement accountManagement = new AccountManagement();
    UserFriends userFriends = new UserFriends();
    Community community = new Community();
    User user = new User();
    Database database = new Database();
    boolean isLoggedIn = false;
    public void showCLI() throws AccountException, FriendsException, FeedException, CommunityException {

        List<Command> commandList = new ArrayList<>();

        OpenLogin openLogin = new OpenLogin(accountManagement);
        OpenRegister openRegister = new OpenRegister(accountManagement);
        OpenEditInfo openEditInfo = new OpenEditInfo(accountManagement);
        OpenSendRequest openSendRequest = new OpenSendRequest(userFriends);
        OpenShowRequests openShowRequests = new OpenShowRequests(userFriends);
        OpenFriendlist openFriendlist = new OpenFriendlist(userFriends);
        OpenCreateCommunity openCreateCommunity = new OpenCreateCommunity(community);
        OpenCommunityList openCommunityList = new OpenCommunityList(community);
        OpenEnterInCommunity openEnterInCommunity = new OpenEnterInCommunity(community, accountManagement.getLoggedInUser());
        OpenSendMessage openSendMessage = new OpenSendMessage(userFriends, user, accountManagement);
        OpenShowMessages openShowMessages = new OpenShowMessages(userFriends, user, accountManagement);
        OpenFeed openFeed = new OpenFeed(userFriends, user, accountManagement);
        OpenPostOnFeed openPostOnFeed = new OpenPostOnFeed(user, accountManagement);
        OpenDeleteAccount openDeleteAccount = new OpenDeleteAccount(database, accountManagement, community, user, userFriends);

        database.insertUserOnDatabase(new User("claudemir", "123", "1"));
        database.insertUserOnDatabase(new User("meteu", "essa", "1"));
        database.insertUserOnDatabase(new User("baldoino", "123", "1"));

        CommandController commandController = new CommandController();

        while (true) {
            if (!isLoggedIn) {
                commandList.clear();
                commandList.add(openRegister);
                commandList.add(openLogin);

                System.out.println("" +
                        "1: Registrar\n" +
                        "2: Logar\n" +
                        "3: Database");

                try {
                    int OPTION = scanner.nextInt();
                    try {
                        commandController.setCommand(commandList.get(OPTION - 1));
                        commandController.commandWasSet();
                        if (OPTION == 2) isLoggedIn = true;
                    } catch(AccountException | IndexOutOfBoundsException e) {
                        System.out.println(e.getMessage());
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Input invalido. Digite um numero, nao um caractere. \nDigite novamente.");
                    scanner.next();
                }
            }
            else {
                commandList.clear();
                commandList.add(openEditInfo);
                commandList.add(openSendRequest);
                commandList.add(openShowRequests);
                commandList.add(openFriendlist);
                commandList.add(openCreateCommunity);
                commandList.add(openCommunityList);
                commandList.add(openEnterInCommunity);
                commandList.add(openSendMessage);
                commandList.add(openShowMessages);
                commandList.add(openFeed);
                commandList.add(openPostOnFeed);
                commandList.add(openDeleteAccount);
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

                try {
                    int OPTION = scanner.nextInt();

                    if (OPTION == 999) {
                        isLoggedIn = false;
                    } else {
                        try {
                            commandController.setCommand(commandList.get(OPTION - 1));
                            commandController.commandWasSet();
                        } catch(AccountException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } catch(InputMismatchException | IndexOutOfBoundsException e) {
                    System.out.println("Digite apenas números ou uma opção válida.");
                    scanner.next();
                }
            }
        }
    }
}