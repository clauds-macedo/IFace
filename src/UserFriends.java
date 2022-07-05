import java.util.*;

public class UserFriends {

    private static Map<String, ArrayList<String>> FRIENDLIST;
    private static Map<String, ArrayList<String>> FRIENDREQUESTS;

    Database database = new Database();
    AccountManagement accountManagement = new AccountManagement();

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    UserFriends(){
        FRIENDLIST = new HashMap<String, ArrayList<String>>();
        FRIENDREQUESTS = new HashMap<String, ArrayList<String>>();
    }

    public Map<String, ArrayList<String>> getFriendlistMap() {
        return FRIENDLIST;
    }

    public Map<String, ArrayList<String>> getRequestsList() {
        return FRIENDREQUESTS;
    }

    void sendRequestToAUser() throws AccountException {
        System.out.println("Digite o nome de usuário que quer enviar o convite");
        String userToSendInvite = scanner.next();
        if (!this.database.checkAccountExistence(userToSendInvite)) {
            throw new AccountException("A conta não existe no banco de dados.");
        }
        if (!FRIENDREQUESTS.containsKey(userToSendInvite)){
            FRIENDREQUESTS.put(userToSendInvite, new ArrayList<String>());
        }
        if (!userToSendInvite.equals(accountManagement.getLoggedInUser())){
            FRIENDREQUESTS.get(userToSendInvite).add(accountManagement.getLoggedInUser());
            System.out.println("Convite enviado.");
        }
        checkIfUsersAreFriends();
    }

    void checkIfUsersAreFriends() {
        if (FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()) == null) {
            return;
        }
        Iterator<String> REQUESTS = FRIENDREQUESTS
                                    .get(this.accountManagement.getLoggedInUser()).iterator();
        boolean isAdded = false;
        while (REQUESTS.hasNext()) {
            String REQUEST = REQUESTS.next();
            try {
                if (FRIENDREQUESTS.get(REQUEST).contains(this.accountManagement.getLoggedInUser())) {
                    if (!FRIENDLIST.containsKey(this.accountManagement.getLoggedInUser())) {
                        FRIENDLIST.put(this.accountManagement.getLoggedInUser(), new ArrayList<String>());
                        FRIENDLIST.put(REQUEST, new ArrayList<String>());
                    }
                    if (!FRIENDLIST.get(this.accountManagement.getLoggedInUser()).contains(REQUEST)) {
                        FRIENDLIST.get(this.accountManagement.getLoggedInUser()).add(REQUEST);
                    }
                    if (!FRIENDLIST.get(REQUEST).contains(this.accountManagement.getLoggedInUser())) {
                        FRIENDLIST.get(REQUEST).add(this.accountManagement.getLoggedInUser());
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
             Iterator<String> REQUESTS = FRIENDREQUESTS.get(this.accountManagement.getLoggedInUser()).iterator();
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
            Iterator<String> FRIENDS = FRIENDLIST.get(this.accountManagement.getLoggedInUser()).iterator();

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
        if (FRIENDLIST.isEmpty()) return false;
        if (FRIENDLIST.get(this.accountManagement.getLoggedInUser()) == null) return false;
        return FRIENDLIST.get(this.accountManagement.getLoggedInUser()).contains(friendName);
    }

    boolean isFriendlistEmpty (){
        return FRIENDLIST.isEmpty();
    }

    public void deleteYourFriendData(Map<String, ArrayList<String>> List) {
        if (List.get(accountManagement.getLoggedInUser()) != null) {
            List.remove(accountManagement.getLoggedInUser());
        }
        List<String> listKeySet = new ArrayList<>(List.keySet());
        Iterator<String> friendlistIterator = listKeySet.iterator();
        while (friendlistIterator.hasNext()) {
            String currentUsername = friendlistIterator.next();
            if (List.get(currentUsername) != null) {
                List.get(currentUsername).remove(accountManagement.getLoggedInUser());
            }
        }
    }
}
