import java.util.*;

public class User {

    public String username, password, nickname;
    ArrayList<Inbox> UserInbox = new ArrayList<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");
    public User(){}

    public User(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public void sendMessage(Inbox msg) {
        UserInbox.add(msg);
        System.out.println(UserInbox);
    }

    public void showMessages(String username, UserFriends userFriends) {
        if (userFriends.isFriendlistEmpty()) {
            System.out.println("Sua lista de amigos está vazia.");
            return;
        }
        System.out.println("De qual amigo deseja ver as mensagens?");
        seeInbox(userFriends);
        String friendToSeeInbox = scanner.next();
        if(!userFriends.isYourFriend(friendToSeeInbox)) {
            System.out.println("O usuário não é seu amigo.");
            return;
        }
        Boolean userHasMessagesWithYou = false;
        for (Inbox message : UserInbox) {
            if (message.toString().contains(username)
                && message.toString().contains(friendToSeeInbox)) {
                userHasMessagesWithYou = true;
                System.out.println(message);
            }
        }
        if(!userHasMessagesWithYou) System.out.println("O usuário não tem mensagens com você.");
    }
    public void seeInbox(UserFriends userFriends) {
        userFriends.showFriendList();
    }
}
