import java.util.*;

public class UserFriends {

    public static Map<String, ArrayList<String>> FRIENDLIST;
    public static Map<String, ArrayList<String>> FRIENDREQUESTS;

    Database database = new Database();
    AccountManagement accountManagement = new AccountManagement();

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    UserFriends(){
        this.FRIENDLIST = new HashMap<String, ArrayList<String>>();
        this.FRIENDREQUESTS = new HashMap<String, ArrayList<String>>();
    }

    void sendRequestToAUser(){
        System.out.println("Digite o nome de usuário que quer enviar o convite");
        String userToSendInvite = scanner.next();
        if (!this.database.checkAccountExistence(userToSendInvite)) {
            System.out.println("O usuário não existe no banco de dados.");
            return;
        }
        if (!this.FRIENDREQUESTS.containsKey(userToSendInvite)){
            this.FRIENDREQUESTS.put(userToSendInvite, new ArrayList<String>());
        }
        if (!userToSendInvite.equals(accountManagement.getLoggedInUser())){
            this.FRIENDREQUESTS.get(userToSendInvite).add(accountManagement.getLoggedInUser());
            System.out.println("Convite enviado.");
        }
        System.out.println(FRIENDREQUESTS);
        checkIfUsersAreFriends();
    }

    void checkIfUsersAreFriends() {
        if (this.FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()) == null) {
            return;
        }
        Iterator<String> REQUESTS = FRIENDREQUESTS
                                    .get(this.accountManagement.getLoggedInUser()).iterator();
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

    void showFriendRequests() {
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

    boolean isYourFriend(String friendName) {
        if (this.FRIENDLIST.isEmpty()) return false;
        if (this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()) == null) return false;
        if (this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).contains(friendName)) return true;
        return false;
    }

    boolean isFriendlistEmpty (){
        return this.FRIENDLIST.isEmpty();
    }

}
