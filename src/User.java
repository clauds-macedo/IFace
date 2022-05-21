import java.util.*;

public class User {

    public String username, password, nickname;

    ArrayList<Inbox> UserInbox = new ArrayList<>();
    ArrayList<Feed> UserFeed = new ArrayList<>();

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    public User(){}

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    void dataForSendingMessages(UserFriends userFriends, String sender) throws FriendsException{
        System.out.println("Pra quem deseja enviar a mensagem?");
        String addressee = scanner.next();
        if (!userFriends.isYourFriend(addressee)) {
            throw new FriendsException();
        }
        System.out.println("Digite o que deseja enviar.");
        String messageContent = scanner.next();
        sendMessage(new Inbox(addressee, sender, messageContent));
        System.out.println("Mensagem enviada com sucesso.");
    }

    public void sendMessage(Inbox msg) {
        UserInbox.add(msg);
    }

    public void showMessages(String username, UserFriends userFriends) throws FriendsException {
        if (userFriends.isFriendlistEmpty()) {
            System.out.println("Sua lista de amigos está vazia.");
            return;
        }
        System.out.println("De qual amigo deseja ver as mensagens?");
        seeInbox(userFriends);
        String friendToSeeInbox = scanner.next();
        if(!userFriends.isYourFriend(friendToSeeInbox)) {
            throw new FriendsException();
        }
        Boolean userHasMessagesWithYou = false;
        for (Inbox message : UserInbox) {
            if (
                    message.getSender().equals(username)
                                    &&
                    message.getAddressee().equals(friendToSeeInbox)
                                    ||
                    message.getAddressee().equals(username)
                                    &&
                    message.getSender().equals(friendToSeeInbox)
            ) {
                userHasMessagesWithYou = true;
                System.out.println(message);
            }
        }
        if(!userHasMessagesWithYou) System.out.println("O usuário não tem mensagens com você.");
    }
    public void seeInbox(UserFriends userFriends) {
        userFriends.showFriendList();
    }

    public void dataForPostOnFeed(String sender) throws FeedException {
        System.out.println("No que está pensando?");
        String messageContent = scanner.next();

        if (messageContent.isEmpty()) {
            throw new FeedException("O corpo da mensagem não pode estar vazio.");
        }

        System.out.println("Selecione uma opção: \n" +
                "f - Postar para amigos\n" +
                "p - Postar em modo público");
        String postVisibility = scanner.next();
        if (!(postVisibility.equals("p") || postVisibility.equals("f"))) {
            throw new FeedException("Opção inválida.");
        }
        postOnFeed(new Feed(messageContent,
                sender,
                postVisibility.toLowerCase()));
    }
    public void postOnFeed(Feed post) {
        UserFeed.add(post);
    }
    public void showFeed(UserFriends userFriends, String loggedInUser) {
        for (Feed post : UserFeed) {
            if (post.getVisibility().equals("f")) {
                if (userFriends.isYourFriend(post.getSender())
                    || post.getSender().equals(loggedInUser)) {
                    System.out.println(post);
                }
            }
            else if (post.getVisibility().equals("p")) {
                System.out.println(post);
            }
        }
    }
    void deleteMessages(String loggedInUser) {
        Iterator<Inbox> message = UserInbox.iterator();
        while (message.hasNext()) {
            Inbox currentMessage = message.next();
            if (currentMessage.getSender().equals(loggedInUser)
                    ||
                    currentMessage.getAddressee().equals(loggedInUser)) {
                message.remove();
            }
        }
    }
    void deletePostOnFeed(String loggedInUser) {
        Iterator<Feed> post = UserFeed.iterator();
        while (post.hasNext()) {
            Feed currentPost = post.next();
            if (currentPost.getSender().equals(loggedInUser)) {
                post.remove();
            }
        }
    }
}
