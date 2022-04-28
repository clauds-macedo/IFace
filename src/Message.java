import java.util.*;

public class Message extends AbsMessage {

    private String kindOfAdressee;
    AccountManagement accountManagement = new AccountManagement();
    public Message(String addressee, String messageContent, String sender, String kindOfAdressee){
        super(addressee, messageContent, sender);
        this.kindOfAdressee = kindOfAdressee;
    }

    public void usersWhoSentMessages() {
        List<Message> users = new ArrayList<Message>();
        for(List<Message> usernames : INBOX.get(accountManagement.getLoggedInUser())) {
            System.out.println(usernames);
        }
    }

    public void showMessage() {

    }

}