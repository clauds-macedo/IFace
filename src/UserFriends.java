import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UserFriends {

    private static Map<String, ArrayList<String>> FRIENDLIST;
    private static Map<String, ArrayList<String>> FRIENDREQUESTS;
    Database database;
    AccountManagement accountManagement;
    Map getFriendREQUESTS(){
        return FRIENDREQUESTS;
    }
    Map getFriendlist(){
        return FRIENDLIST;
    }
    UserFriends(){
        this.FRIENDLIST = new HashMap<String, ArrayList<String>>();
        this.FRIENDREQUESTS = new HashMap<String, ArrayList<String>>();
        this.database = new Database();
        this.accountManagement = new AccountManagement();
    }

    void send_invite(String userToSendInvite){
        if (!this.database.check_if_account_exists(userToSendInvite)){
            return;
        }
        if (!this.FRIENDREQUESTS.containsKey(userToSendInvite)){
            this.FRIENDREQUESTS.put(userToSendInvite, new ArrayList<String>());
        }
        if (!userToSendInvite.equals(accountManagement.getLoggedInUser())){
            this.FRIENDREQUESTS.get(userToSendInvite).add(accountManagement.getLoggedInUser());
            System.out.println("Convite enviado.");
        }
        check_if_are_friends();
    }

    void check_if_are_friends(){
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
                if (!this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).contains(REQUEST)) {
                    this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).add(REQUEST);
                }
                if (!this.FRIENDLIST.get(REQUEST).contains(this.accountManagement.getLoggedInUser())) {
                    this.FRIENDLIST.get(REQUEST).add(this.accountManagement.getLoggedInUser());
                }
                isAdded = true;
            }
        }
        if (isAdded) System.out.println("Amigo adicionado à sua lista de amigos.");
    }

    void show_requests(){
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

    void show_friendlist(){
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

    boolean is_a_friend(String friend_name) {
        if (this.FRIENDLIST.isEmpty()) return false;
        if (this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()) == null) return false;
        if (this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).contains(friend_name)) return true;
        return false;
    }

    boolean isFriendlistEmpty (){
        return this.FRIENDLIST.isEmpty();
    }

}
