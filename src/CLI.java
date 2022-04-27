import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class CLI {

    Scanner scanner;
    AccountManagement accountManagement;
    Database database;
    UserFriends userFriends;
    Community community;
    DateTimeFormatter date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    Map<String, ArrayList<List<String>>> INBOX = new HashMap<String, ArrayList<List<String>>>();

    private static boolean isLoggedIn;
    List<List<String>> FEED = new ArrayList<>();


    public CLI(){
        this.scanner = new Scanner(System.in).useDelimiter("\\n");
        this.database = new Database();
        this.accountManagement = new AccountManagement();
        this.userFriends = new UserFriends();
        this.community = new Community();
    }

    public void show_cli(){

        database.add_user_in_db(new User("teste", "1", "2"));
        database.add_user_in_db(new User("baldoino", "1", "2"));
        database.add_user_in_db(new User("claudemir", "1", "2"));
        while (true){
            if (!isLoggedIn) {
                System.out.println("1: Registrar\n" +
                        "2: Logar\n" +
                        "3: Database");

                int OPTION = scanner.nextInt();

                if (OPTION == 0) break;

                else if (OPTION == 1) {
                    this.database.add_user_in_db(accountManagement.getNewRegisteredUser());
                } else if (OPTION == 2) {
                    String login = scanner.next();
                    String pass = scanner.next();
                    if (accountManagement.login(login, pass)) {
                        this.isLoggedIn = true;
                    }
                } else if (OPTION == 3) {
                    this.database.show_database();
                }
            }
            else {
                System.out.println("User: " + this.accountManagement.getLoggedInUser());

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

                else if (OPTION == 1) {
                    this.accountManagement.editProfile();
                }

                else if (OPTION == 2) {
                    System.out.println("Digite o nome de usuário que quer enviar o convite");
                    String friendToInvite = scanner.next();
                    this.userFriends.send_invite(friendToInvite);
                }

                else if (OPTION == 3) {
                    this.userFriends.show_requests();
                }

                else if (OPTION == 4) {
                    this.userFriends.show_friendlist();
                }

                else if (OPTION == 5) {
                    community.create_community();
                }

                else if (OPTION == 6) {
                    community.show_communities();
                }

                else if (OPTION == 7) {
                    community.enter_in_community();
                }

                else if (OPTION == 8) {
                    send_message();
                }

                else if (OPTION == 9) {
                    show_messages();
                }

                else if (OPTION == 10) {
                    show_feed();
                }

                else if (OPTION == 11) {
                    System.out.println("O que est� pensando?");
                    String post = scanner.next();
                    System.out.println("Quem pode ver sua publica��o?\n"
                            + "1: Apenas amigos\n"
                            + "2: Todos podem ver");

                    String post_view = scanner.next();

                    add_post_to_feed(post, post_view);
                }

                else if (OPTION == 12) {
                    System.out.println("O que deseja mudar?\n"
                            + "1: Senha\n"
                            + "2: Apelido\n");
                    int toChange = scanner.nextInt();
                    if (toChange > 0 && toChange < 3) {
                        System.out.println("Digite o novo dado");
                        String newData = scanner.next();
                        database.change_account_info(accountManagement.getLoggedInUser(),
                                toChange,
                                newData);
                    }
                }
                else if (OPTION == 13) {
                    System.out.println("Você tem certeza que quer remover sua conta?" +
                            "Essa ação pode ser irreversível.");
                    System.out.println("1: Sim\n2: Não");
                    String userChoice = scanner.next();
                    if (userChoice.equals("1")) {
                        database.getUsersHashMap().remove(accountManagement.getLoggedInUser());
                        remove_account(userFriends.getFriendREQUESTS());
                        remove_account(userFriends.getFriendlist());
                        System.out.println("Conta removida!");
                        isLoggedIn = false;
                    }
                }
            }
        }
    }


    void send_message() {
        System.out.println("Digite o nome de usuário que deseja enviar mensagem");
        String addressee = scanner.next();
        System.out.println("Digite o conteúdo da mensagem: ");
        String msg_to_send = scanner.next();
        if (userFriends.is_a_friend(addressee)) {
            String[] msg_data =
                    {
                            accountManagement.getLoggedInUser(),
                            msg_to_send,
                            this.date.format(LocalDateTime.now())
                    };
            if (!this.INBOX.containsKey(addressee)) {
                this.INBOX.put(addressee, new ArrayList<List<String>>());
            }
            this.INBOX.get(addressee).add(Arrays.asList(msg_data));
        }
    }

    void users_who_sent_messages() {

        List<String> inboxUserNames = new ArrayList<String>();

        for (List<String> name : this.INBOX.get(accountManagement.getLoggedInUser())) {

            if (!inboxUserNames.contains(name.get(0))) {
                inboxUserNames.add(name.get(0));
            }

        }
        System.out.println("=====CAIXA DE ENTRADA=====");
        for (String user_in_inbox : inboxUserNames) {
            System.out.println(user_in_inbox + "\n");
        }
        System.out.println("==========================");
    }

    void show_messages() {
        System.out.println("De qual usu�rio deseja ver as mensagens?");
        users_who_sent_messages();
        String username = scanner.next();
        Iterator<List<String>> username_sent_me_msg = INBOX.get(accountManagement.getLoggedInUser()).iterator();

        while (username_sent_me_msg.hasNext()) {
            List<String> user_messages = username_sent_me_msg.next();
            if (user_messages.contains(username)) {
                System.out.println(
                        user_messages.get(0) + "\n"
                        + user_messages.get(1) + "\n"
                        + user_messages.get(2) + "\n\n");
            }
        }
    }

    void show_feed() {

        if (this.FEED.isEmpty()) {
            System.out.println("N�o tem nada no feed.");
            return;
        }

        for (List<String> post : this.FEED) {

            if (accountManagement.getLoggedInUser().equals(post.get(0))) {
                System.out.println("Publicado por voc� \n"
                        + post.get(1) + "\n"
                        + "�s " + post.get(3) + "\n");
            }
            else if (!accountManagement.getLoggedInUser().equals(post.get(0))) {
                if (post.get(2).equals("2")) {
                    System.out.println("Publicado por " + post.get(0) + "\n"
                            + post.get(1) + "\n"
                            + "�s " + post.get(3) + "\n");
                }
                else {
                    if (userFriends.is_a_friend(post.get(0))) {
                        System.out.println("Publicado por " + post.get(0) + "\n"
                                + post.get(1) + "\n"
                                + "�s " + post.get(3) + "\n");
                    }
                }
            }
        }
    }

    void add_post_to_feed(String post_body, String visualization_config) {

        String[] new_post = {accountManagement.getLoggedInUser(),
                post_body,
                visualization_config,
                this.date.format(LocalDateTime.now())};
        this.FEED.add(Arrays.asList(new_post));
        System.out.println("Post adicionado.");

    }
    void remove_account(Map<String, ArrayList<String>> map) {

        List<String> keySet = new ArrayList<>(userFriends.getFriendlist().keySet());
        Iterator<String> keySet_itr = keySet.iterator();
        Iterator <List<String>> posts = FEED.iterator();

        while (posts.hasNext()) {

        }

        while(keySet_itr.hasNext()) {
            String actualName = keySet_itr.next();
            if (map.get(actualName).contains(accountManagement.getLoggedInUser())){
                map.get(actualName).remove(accountManagement.getLoggedInUser());
            }
        }

    }
}
