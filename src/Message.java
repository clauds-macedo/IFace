import java.util.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;


public class Message {

    UserFriends userFriends;
    AccountManagement accountManagement;

    public static Map<String, ArrayList<List<String>>> INBOX;

    DateTimeFormatter date;

    public Message() {
        this.userFriends = new UserFriends();
        this.accountManagement = new AccountManagement();

        this.INBOX = new HashMap<>();
        this.date = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }

    void sendMessage() {
        if (this.userFriends.FRIENDLIST.get(this.accountManagement.getLoggedInUser()) == null) {
            System.out.println("Sem amigos.");
            return;
        }
        System.out.println("Pra quem deseja enviar a mensagem?");
        String addressee = this.accountManagement.scanner.next();
        System.out.println("Digite a mensagem");
        String messageContent = this.accountManagement.scanner.next();

        String[] msg_data =
                {
                    addressee,
                    messageContent,
                    this.date.format(LocalDateTime.now())
                };
        if (!this.INBOX.containsKey(addressee)) {
            this.INBOX.put(addressee, new ArrayList<List<String>>());
        }
        this.INBOX.get(addressee).add(Arrays.asList(msg_data));
        System.out.println("Mensagem enviada.");
    }

    void usersThatSentMeMessage() {

        List<String> inboxUserNames = new ArrayList<String>();

        for (List<String> name : this.INBOX.get(this.accountManagement.getLoggedInUser())) {

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

    void showMessages() {
        if (this.INBOX.get(this.accountManagement.getLoggedInUser()) == null
                ||
                this.INBOX.get(this.accountManagement.getLoggedInUser()).isEmpty()
        ) {
            System.out.println("Sem mensagens na caixa de entrada");
            return;
        }
        System.out.println("De qual usuário deseja ver as mensagens?");
        usersThatSentMeMessage();

        String username = this.accountManagement.scanner.next();

        if (!this.userFriends.isYourFriend(username)) {
            System.out.println("O usuário não é seu amigo.");
            return;
        }

        while (true) {
            Iterator<List<String>> userWhoSentMeMsg = INBOX.get(this.accountManagement.getLoggedInUser())
                    .iterator();
            while (userWhoSentMeMsg.hasNext()) {
                List<String> user_messages = userWhoSentMeMsg.next();
                if (user_messages.contains(username)) {
                    System.out.println(
                            user_messages.get(0) + "\n"
                                    + user_messages.get(1) + "\n"
                                    + user_messages.get(2) + "\n\n"
                    );
                }
            }
        }
    }

}
