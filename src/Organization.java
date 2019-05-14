import java.util.ArrayList;

public class Organization {
    private String name;
    private ArrayList<Node> nodes;
    private ArrayList<Organization> deps;

    public Organization(String name) {
        this.name = name;
        this.nodes = new ArrayList<>();
        this.deps = new ArrayList<>();
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
}
