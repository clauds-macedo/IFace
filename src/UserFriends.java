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

    void sendRequestToAUser() throws AccountException {
        System.out.println("Digite o nome de usuário que quer enviar o convite");
        String userToSendInvite = scanner.next();
        if (!this.database.checkAccountExistence(userToSendInvite)) {
            throw new AccountException();
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

    void checkIfUsersAreFriends() {
        if (this.FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()) == null) {
            return;
        }
        Iterator<String> REQUESTS = FRIENDREQUESTS
                                    .get(this.accountManagement.getLoggedInUser()).iterator();
        boolean isAdded = false;
        while (REQUESTS.hasNext()) {
            String REQUEST = REQUESTS.next();
            try {
                if (this.FRIENDREQUESTS.get(REQUEST).contains(this.accountManagement.getLoggedInUser())) {
                    if (!this.FRIENDLIST.containsKey(this.accountManagement.getLoggedInUser())) {
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
            } catch(NullPointerException e) {
                System.out.println("O usuário não existe.");
            }
        }
        if (isAdded) System.out.println("Amigo adicionado à sua lista de amigos.");
    }

    void showFriendRequests() {
         try {
             Iterator<String> REQUESTS = this.FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()).iterator();
             System.out.println("===Lista de convites===");
             while (REQUESTS.hasNext()) {
                 String REQUEST = REQUESTS.next();
                 System.out.println(REQUEST);
             }
             System.out.println("=======================\n");
         } catch(NullPointerException e) {
             System.out.println("Sem convites.");
         }
    }

    void showFriendList(){
        try {
            Iterator<String> FRIENDS = this.FRIENDLIST.get(this.accountManagement.getLoggedInUser()).iterator();

            System.out.println("===Lista de amigos===");
            while (FRIENDS.hasNext()) {
                String FRIEND = FRIENDS.next();
                System.out.println(FRIEND);
            }
            System.out.println("=======================\n");
        } catch(NullPointerException e) {
            System.out.println("Sem amigos adicionados.");
        }
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

    public void deleteYourFriendData() {
        if (FRIENDREQUESTS.get(accountManagement.getLoggedInUser()) != null) {
            FRIENDREQUESTS.remove(accountManagement.getLoggedInUser());
        }
        if (FRIENDLIST.get(accountManagement.getLoggedInUser()) != null) {
            FRIENDLIST.remove(accountManagement.getLoggedInUser());
        }
        List<String> FriendlistKeySet = new ArrayList<>(FRIENDLIST.keySet());
        List<String> FriendRequestKeySet = new ArrayList<>(FRIENDREQUESTS.keySet());

        Iterator<String> friendlistIterator = FriendlistKeySet.iterator();
        Iterator<String> friendRequestIterator = FriendRequestKeySet.iterator();

        while (friendlistIterator.hasNext()) {
            String currentUsername = friendlistIterator.next();
            if (FRIENDLIST.get(currentUsername) != null
                    &&
                FRIENDLIST.get(currentUsername).contains(accountManagement.getLoggedInUser())) {
                    FRIENDLIST.get(currentUsername).remove(accountManagement.getLoggedInUser());
            }
        }

        while (friendRequestIterator.hasNext()) {
            String currentUsername = friendRequestIterator.next();
            if (FRIENDREQUESTS.get(currentUsername) != null
                    &&
                FRIENDREQUESTS.get(currentUsername).contains(accountManagement.getLoggedInUser())) {
                    FRIENDREQUESTS.get(currentUsername).remove(accountManagement.getLoggedInUser());
            }
        }

    }
}
