import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserFriends {

    static Map<String, ArrayList<String>> FRIENDLIST;
    static Map<String, ArrayList<String>> FRIENDREQUESTS;
    Database database;
    AccountManagement accountManagement;

    UserFriends(){
        this.FRIENDLIST = new HashMap<String, ArrayList<String>>();
        this.FRIENDREQUESTS = new HashMap<String, ArrayList<String>>();
        this.database = new Database();
        this.accountManagement = new AccountManagement();
    }

    void sendRequestToAUser(String userToSendInvite){
        if (!this.database.checkAccountExistence(userToSendInvite)){
            return;
        }
        if (!this.FRIENDREQUESTS.containsKey(userToSendInvite)){
            this.FRIENDREQUESTS.put(userToSendInvite, new ArrayList<String>());
        }
        if (!userToSendInvite.equals(accountManagement.getLoggedInUser())){
            this.FRIENDREQUESTS.get(userToSendInvite).add(accountManagement.getLoggedInUser());
            System.out.println("Convite enviado.");
        }
        checkIfUsersAreFriends();
    }

    void checkIfUsersAreFriends(){
        if (this.FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()) == null) {
            return;
        }

        Iterator<String> REQUESTS = FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()).iterator();
        boolean isAdded = false;

        while (REQUESTS.hasNext()) {
            String REQUEST = REQUESTS.next();
            if (this.FRIENDREQUESTS.get(REQUEST).contains(this.accountManagement.getLoggedInUser())) {
                if(!this.FRIENDLIST.containsKey(this.accountManagement.getLoggedInUser())) {
                    this.FRIENDLIST.put(this.accountManagement.getLoggedInUser(), new ArrayList<String>());
                    this.FRIENDLIST.put(REQUEST, new ArrayList<String>());
                }
                this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).add(REQUEST);
                this.FRIENDLIST.get(REQUEST).add(this.accountManagement.getLoggedInUser());
                isAdded = true;
            }
        }
        if (isAdded) System.out.println("Amigo adicionado à sua lista de amigos.");
    }

    void showFriendRequests(){
        if (this.FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()) == null) {
            System.out.println("Sem convites.");
            return;
        }

        Iterator<String> REQUESTS = this.FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()).iterator();

        System.out.println("===Lista de convites===");

        while (REQUESTS.hasNext()) {
            String REQUEST = REQUESTS.next();
            System.out.println(REQUEST);
        }

        System.out.println("=======================\n");
    }

    void showFriendList(){
        if (this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()) == null) {
            System.out.println("Sem amigos.");
            return;
        }

        Iterator<String> FRIENDS = this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).iterator();

        System.out.println("===Lista de amigos===");

        while (FRIENDS.hasNext()) {
            String FRIEND = FRIENDS.next();
            System.out.println(FRIEND);
        }

        System.out.println("=======================\n");
    }

    boolean isYourFriend(String friend_name) {
        if (this.FRIENDLIST.isEmpty()) return false;
        if (this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()) == null) return false;
        if (this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).contains(friend_name)) return true;
        return false;
    }

    boolean isFriendlistEmpty (){
        return this.FRIENDLIST.isEmpty();
    }

}
