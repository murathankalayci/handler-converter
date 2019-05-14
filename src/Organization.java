import java.util.ArrayList;
import java.util.HashMap;

public class Organization {
    private String name;
    private ArrayList<Node> nodes;
    private ArrayList<Organization> deps;
    private HashMap<Organization, Integer> depMap;

    public Organization(String name) {
        this.name = name;
        this.nodes = new ArrayList<>();
        this.deps = new ArrayList<>();
        this.depMap = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public ArrayList<Organization> getDeps() {
        return deps;
    }

    public void populateMap() {
        for (int i = 0; i < deps.size(); i++) {
            if (depMap.containsKey(deps.get(i))){
                depMap.put(deps.get(i),depMap.get(deps.get(i)) + 1);
            }else {
                depMap.put(deps.get(i), 1);
            }
        }
    }

    public HashMap<Organization, Integer> getDepMap() {
        return depMap;
    }
}
