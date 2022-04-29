import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inbox extends Message {

    Map<String, ArrayList<List<Message>>> INBOX = new HashMap<String, ArrayList<List<Message>>>();
    UserFriends userFriends = new UserFriends();
    Database database = new Database();

    private String addressee;

    public Inbox(String addressee, String sender, String content) {
        super(content, addressee, sender);
        this.addressee = addressee;
    }

    @Override
    void sendMessage() {
        System.out.println(super.toString());
        if (!userFriends.isYourFriend(this.addressee)) {
            System.out.println("O usuário " + this.addressee + " não é seu amigo.");
            return;
        }
        if (!database.checkAccountExistence(this.addressee)) {
            System.out.println("Esse usuário não existe no nosso banco de dados.");
            return;
        }
        if (!INBOX.containsKey(this.addressee)) {
            INBOX.put(this.addressee, new ArrayList<List<Message>>());
        }
    }

}
