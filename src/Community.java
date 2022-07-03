import java.util.*;

public class Community {

    Map<String, ArrayList<String>> CommunityMembers = new HashMap<>();
    ArrayList<Community> CommunityList = new ArrayList<>();
    AccountManagement accountManagement = new AccountManagement();

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    private String communityName, communityDescription, communityOwner;

    public Community(){}
    public Community(String communityName, String communityDescription, String communityOwner) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
        this.communityOwner = communityOwner;
    }

    public void addCommunity(String communityName, String communityDescription, String communityOwner) throws CommunityException {
        if (CommunityMembers.containsKey(communityName)) {
            throw new CommunityException("A comunidade não existe");
        }
        CommunityList.add(new Community(communityName, communityDescription, communityOwner));
        CommunityMembers.put(communityName, new ArrayList<>());
        CommunityMembers.get(communityName).add(communityOwner);
    }

    public void createCommunity() {
        System.out.println("Digite o nome da comunidade que deseja criar");
        String communityName = scanner.next();

        System.out.println("Digite a descrição da comunidade que deseja criar");
        String communityDescription = scanner.next();

        try {
                addCommunity(
                    communityName,
                    communityDescription,
                accountManagement.getLoggedInUser());
        } catch (CommunityException e) {
            System.out.println(e.getMessage());
        }
    }


    public void showCommunity() {
        for (Community community : CommunityList) {
            System.out.println(
                    "Comunidade: " + community.communityName +
                    "\nDescrição: " + community.communityDescription +
                    "\nDono: " + community.communityOwner +
                    "\nMembros: " + CommunityMembers.get(community.communityName) + "\n"
            );
        }
    }

    public void addMember(String user) throws CommunityException {
        System.out.println("Digite o nome da comunidade na qual deseja entrar");
        try {
            String communityName = scanner.next();
            if (CommunityMembers.get(communityName).contains(user)) {
                throw new CommunityException("Você já está nessa comunidade com esse nome.");
            }
            CommunityMembers.get(communityName).add(user);
        }
        catch(NullPointerException e) {
            System.out.println("A comunidade não existe.");
        }

    }

    public void deleteCommunities(String communityOwner) {
        Iterator<Community> community = CommunityList.iterator();
        while (community.hasNext()) {
            Community currentCommunity = community.next();
            if (currentCommunity.communityOwner.equals(communityOwner)) {
                community.remove();
            }
        }
    }
}
