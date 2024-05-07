package coding.topic1;


import java.util.List;

/**
 * - 任务节点建模：设计一个任务节点类（例如DataProcessingNode），它可以表示每个节点的名称、
 * 其依赖的其他节点列表以及执行其数据处理逻辑的方法。
 *
 * @author zhanghaibing
 * @date 2024-03-29
 */
public class BatchProcessor {
    private int maxRetries;
    private BatchDependencyGraph dependencyGraph;

    public BatchProcessor(int maxRetries, BatchDependencyGraph dependencyGraph) {
        this.maxRetries = maxRetries;
        this.dependencyGraph = dependencyGraph;
    }

    public List<BatchNode> topologicalSort() {
        return dependencyGraph.topologicalSort();
    }

    /**
     * - 节点执行与重试机制：设计一个批处理执行器类（例如BatchProcessor），负责按照拓扑排序后的顺序执行每个节点的任务。
     * 当某个节点（例如节点D）执行失败时，仅重启该节点及其直接导致该节点执行的上游节点（即D的直接依赖节点）。执行器应具备
     * 设定最大重试次数的功能
     */
    public void process(BatchNode node) {
        boolean success = false;
        int retries = 0;

        while (!success && retries < maxRetries) {
            for (BatchNode dependencyNode : node.dependencies) {
                process(dependencyNode);
            }

            // 假设BatchNode类有execute方法来处理节点任务
            success = node.execute();

            if (!success) {
                retries++;
            } else {
                break;
            }
        }

        if (!success && retries == maxRetries) {
            System.out.println("Failed to process node " + node.name + " after " + maxRetries + " retries.");
        }
    }

    public void processAll() {
        List<BatchNode> sortedNodes = topologicalSort();

        for (BatchNode node : sortedNodes) {
            process(node);
        }
    }
}
