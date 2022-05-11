import java.util.*;

public class Community {

    Map<String, ArrayList<String>> CommunityMembers = new HashMap<>();
    ArrayList<Community> CommunityList = new ArrayList<>();

    Scanner scanner = new Scanner(System.in).useDelimiter("\\n");

    private String communityName, communityDescription, communityOwner;

    public Community(){}
    public Community(String communityName, String communityDescription, String communityOwner) {
        this.communityName = communityName;
        this.communityDescription = communityDescription;
        this.communityOwner = communityOwner;
    }

    public void addCommunity(String communityName, String communityDescription, String communityOwner) {
        if (CommunityList.contains(communityName)) {
            System.out.println("A comunidade já existe.");
            return;
        }
        CommunityList.add(new Community(communityName, communityDescription, communityOwner));
        CommunityMembers.put(communityName, new ArrayList<>());
        CommunityMembers.get(communityName).add(communityOwner);
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

    public void addMember(String user) {
        System.out.println("Digite o nome da comunidade na qual deseja entrar");
        String communityName = scanner.next();
        if (CommunityMembers.get(communityName).contains(user)) {
            System.out.println("Você já está nessa comunidade");
            return;
        }
        CommunityMembers.get(communityName).add(user);
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
