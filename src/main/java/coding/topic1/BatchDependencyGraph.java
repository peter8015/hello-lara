package coding.topic1;


import org.junit.Test;

import java.util.*;

/**
 * - 依赖关系管理：设计一个依赖关系图模型（例如DependencyGraph），
 * 用于存储所有节点及其依赖关系，并支持添加和修改节点及其依赖。
 *
 * @author zhanghaibing
 * @date 2024-03-29
 */
public class BatchDependencyGraph {
    /**
     * - 拓扑排序：实现一个拓扑排序算法，用于根据节点间的依赖关系确定节点的执行顺序。确保在无环的情况下，
     * 对于任何一对节点 u 和 v，若存在从 u 到 v 的路径，则在排序序列中 u 出现在 v 之前。
     */

    private final Map<BatchNode, List<BatchNode>> graph;

    public BatchDependencyGraph() {
        this.graph = new HashMap<>();
    }

    // 添加节点
    public void addNode(BatchNode node) {
        graph.putIfAbsent(node, new ArrayList<>());
    }

    // 添加依赖关系
    public void addDependency(BatchNode dependentNode, BatchNode dependencyNode) {
        if (dependentNode != null && dependencyNode != null) {
            addNode(dependentNode);
            addNode(dependencyNode);
            graph.get(dependentNode).add(dependencyNode);
        } else {
            throw new IllegalArgumentException("Both dependentNode and dependencyNode must not be null.");
        }
    }

    // 拓扑排序
    public List<BatchNode> topologicalSort() {
        List<BatchNode> sortedNodes = new ArrayList<>();
        Deque<BatchNode> queue = new LinkedList<>();

        // 初始化队列，放入无依赖的节点
        for (Map.Entry<BatchNode, List<BatchNode>> entry : graph.entrySet()) {
            if (entry.getValue().isEmpty()) {
                queue.offer(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            BatchNode currentNode = queue.poll();
            sortedNodes.add(currentNode);

            // 移除当前节点作为依赖的节点的依赖关系
            for (Map.Entry<BatchNode, List<BatchNode>> entry : graph.entrySet()) {
                entry.getValue().remove(currentNode);
                if (entry.getValue().isEmpty()) {
                    queue.offer(entry.getKey());
                }
            }
        }

        // 检查是否有环路（如果有环路，topologicalSort将无法完全遍历所有节点）
        if (sortedNodes.size() != graph.size()) {
            throw new IllegalStateException("Cycle detected in the dependency graph, cannot perform topological sort.");
        }

        return sortedNodes;
    }

    @Test
    public void test() {
//        Map<String, String> data = new LinkedHashMap<>();
        Map<String, String> data = new HashMap<>();

        data.put("A", "B");
        data.put("B", "C");
        data.put("C", "D");

        data.forEach((k, v) -> {
            System.out.println(k + " -> " + v);
        });

    }

}

