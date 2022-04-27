import java.util.*;

public class Community {

    static Map<String, ArrayList<String>> COMMUNITIES;
    AccountManagement accountManagement;
    Database database;
    Scanner scanner;

    public Community(){
        this.COMMUNITIES = new HashMap<String, ArrayList<String>>();
        this.accountManagement = new AccountManagement();
        this.scanner = new Scanner(System.in);
    }

    void create_community() {
        System.out.println("Digite o nome da comunidade que deseja criar.");
        String community_name = this.scanner.useDelimiter("\\n").next();

        if (this.COMMUNITIES.containsKey(community_name)) {
            System.out.println("Essa comunidade já existe!");
            return;
        }

        System.out.println("Digite a descrição da comunidade que deseja criar.");
        String description = this.scanner.useDelimiter("\\n").next();

        this.COMMUNITIES.put(community_name, new ArrayList<String>());
        this.COMMUNITIES.get(community_name).add(this.accountManagement.getLoggedInUser()); //dono vai sempre estar na pos. 0 do array de membros
        this.COMMUNITIES.get(community_name).add(description);

        System.out.println("Comunidade criada!");
        return;
    }

    void show_communities(){
        if (this.COMMUNITIES.isEmpty()) {
            System.out.println("Nenhuma comunidade criada.");
            return;
        }

        Iterator<String> COMMUNITIES= this.COMMUNITIES.keySet().iterator();
        while (COMMUNITIES.hasNext()) {
            String COMMUNITY = COMMUNITIES.next();
            int qtMembers = this.COMMUNITIES.get(COMMUNITY).size() - 1;
            System.out.println(
                    "========================================\n"
                            + "Comunidade: " + COMMUNITY + "\n"
                            + "Dono: " + this.COMMUNITIES.get(COMMUNITY).get(0) + "\n"
                            + "Qtd. membros: "  + qtMembers
            );
            if (this.COMMUNITIES.get(COMMUNITY).contains(this.accountManagement.getLoggedInUser())) {
                System.out.println("Você está nessa comunidade.\n"
                        + "========================================\n");
            }
            else {
                System.out.println("========================================");
            }
        }
    }

    void enter_in_community() {
        String community_name = scanner.useDelimiter("\\n").next();
        if (this.COMMUNITIES.isEmpty()) {
            return;
        }
        else if (!this.COMMUNITIES.containsKey(community_name)) {
            System.out.println("Comunidade não existe.");
            return;
        }

        if (this.COMMUNITIES.get(community_name).contains(this.accountManagement.getLoggedInUser())) {
            System.out.println("Você já está nessa comunidade.");
            return;
        }

        this.COMMUNITIES.get(community_name).add(this.accountManagement.getLoggedInUser());
        System.out.println("Entrou na comunidade " + community_name + "com sucesso! \n");
    }
}
