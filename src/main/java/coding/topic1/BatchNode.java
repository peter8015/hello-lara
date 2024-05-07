package coding.topic1;


import java.util.ArrayList;
import java.util.List;

/**
 * @author zhanghaibing
 * @date 2024-03-29
 */
public class BatchNode {
    public boolean visited;
    String name;
    List<BatchNode> dependencies;

    public BatchNode(String name) {
        this.name = name;
        this.dependencies = new ArrayList();
    }

    public BatchNode(String name, List<BatchNode> nodes) {
        this.name = name;
        this.dependencies = new ArrayList();
    }

    public boolean execute() {
        System.out.println("execute " + name);
        return true;
    }
}
