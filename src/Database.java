import java.util.*;

public class Database {
    public static Map<Object, ArrayList<String>> usersDatabase;
    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    public Database(){
        this.usersDatabase = new HashMap<Object, ArrayList<String>>();
    }

    public void insertUserOnDatabase(User userInfo){
        if (!this.usersDatabase.containsKey(userInfo.username)){
            this.usersDatabase.put(userInfo.username, new ArrayList<String>());
        }
        this.usersDatabase.get(userInfo.username).add(userInfo.password);
        this.usersDatabase.get(userInfo.username).add(userInfo.nickname);
    }

    public void showUserDatabase(){
        System.out.println(this.usersDatabase.keySet());
    }

    boolean checkAccountExistence(String user) throws AccountException {
        if (!this.usersDatabase.containsKey(user)) {
            throw new AccountException();
        }
        return true;
    }

    boolean compareWithUserDatabase(String userLogin, String userPassword) {
        return (usersDatabase.get(userLogin).get(0).equals(userPassword));
    }

    public void deleteUserInfo(String loggedInUser,
                               UserFriends userFriends,
                               User user,
                               Community community) {
        System.out.println("Tem certeza que deseja excluir todos os seus dados?" +
                "Essa ação é irreversível\n" +
                "1 - Sim\n" +
                "2 - Voltar");
        String isSure = scanner.next();
        if (!isSure.equals("1")) {
            return;
        }
        usersDatabase.remove(loggedInUser);
        userFriends.deleteYourFriendData();
        user.deleteMessages(loggedInUser);
        user.deletePostOnFeed(loggedInUser);
        community.deleteCommunities(loggedInUser);
    }

}
