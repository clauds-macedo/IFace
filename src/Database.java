import java.lang.reflect.Array;
import java.util.*;

public class Database {

    public static Map<Object, ArrayList<String>> usersHashMap;

    public Database(){
        this.usersHashMap = new HashMap<Object, ArrayList<String>>();
    }

    public void addNewUser(User userInfo){
        if (!this.usersHashMap.containsKey(userInfo.username)){
            this.usersHashMap.put(userInfo.username, new ArrayList<String>());
        }
        this.usersHashMap.get(userInfo.username).add(userInfo.password);
        this.usersHashMap.get(userInfo.username).add(userInfo.nickname);
    }

    public void showUserDatabase(){
        System.out.println(this.usersHashMap.keySet());
    }

    public void changeAccountInfo(String username, int index, String newInfo){
        String[] newUserData = {this.usersHashMap.get(username).get(0),
                                this.usersHashMap.get(username).get(1)};
        newUserData[index - 1] = newInfo;

        ArrayList<String> wrapNewData = new ArrayList<>();

        wrapNewData.add(newUserData[0]);
        wrapNewData.add(newUserData[1]);

        this.usersHashMap.put(username, wrapNewData);
        System.out.println("Dado alterado com sucesso.");
    }

    boolean checkAccountExistence(String user) {
        return this.usersHashMap.containsKey(user);
    }
}
