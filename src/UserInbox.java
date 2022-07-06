import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class UserInbox {

    ArrayList<Inbox> UserInbox = new ArrayList<>();
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    void dataForSendingMessages(UserFriends userFriends, String sender) throws FriendsException{
        System.out.println("Pra quem deseja enviar a mensagem?");
        String addressee = scanner.next();
        if (!userFriends.isYourFriend(addressee)) {
            throw new FriendsException("O destinatário não é seu amigo.");
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
            throw new FriendsException("Sua lista de amigos está vazia.");
        }
        System.out.println("De qual amigo deseja ver as mensagens?");
        seeInbox(userFriends);
        String friendToSeeInbox = scanner.next();
        if(!userFriends.isYourFriend(friendToSeeInbox)) {
            throw new FriendsException("O usuário não é seu amigo");
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
        if(!userHasMessagesWithYou) throw new FriendsException("O usuário não tem mensagens com você.");
    }
    public void seeInbox(UserFriends userFriends) {
        userFriends.showFriendList();
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

}
