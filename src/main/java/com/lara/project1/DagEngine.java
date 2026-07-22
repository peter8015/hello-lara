package com.lara.project1;

import java.util.*;
import java.util.concurrent.*;

public class DagEngine {
    private final Map<String, TaskNode> nodeMap = new ConcurrentHashMap<>();
    private final ExecutorService executor;

    public DagEngine(ExecutorService executor) {
        this.executor = executor;
    }

    // 添加节点
    public void addNode(TaskNode node) {
        nodeMap.put(node.getId(), node);
    }

    // 执行 DAG
    public void execute() throws Exception {
        // 1. 成环检测
        if (hasCycle()) {
            throw new IllegalStateException("DAG 中检测到环，无法继续执行！");
        }

        // 2. 映射存储每个节点的 CompletableFuture 状态
        Map<String, CompletableFuture<Void>> futures = new HashMap<>();

        // 3. 构建任务依赖链并调度
        for (TaskNode node : nodeMap.values()) {
            buildFutureRecursive(node, futures);
        }

        // 4. 等待所有节点执行完毕
        CompletableFuture.allOf(futures.values().toArray(new CompletableFuture[0])).join();
    }

    // 递归构建 CompletableFuture 依赖关系
    private CompletableFuture<Void> buildFutureRecursive(TaskNode node, Map<String, CompletableFuture<Void>> futures) {
        if (futures.containsKey(node.getId())) {
            return futures.get(node.getId());
        }

        List<CompletableFuture<Void>> parentFutures = new ArrayList<>();
        for (TaskNode dep : node.getDependencies()) {
            parentFutures.add(buildFutureRecursive(dep, futures));
        }

        CompletableFuture<Void> currentFuture;
        if (parentFutures.isEmpty()) {
            // 没有依赖的任务，直接异步提交
            currentFuture = CompletableFuture.runAsync(() -> runTask(node), executor);
        } else {
            // 依赖所有父节点完成后，再触发当前任务
            CompletableFuture<Void> allParents = CompletableFuture.allOf(parentFutures.toArray(new CompletableFuture[0]));
            currentFuture = allParents.thenRunAsync(() -> runTask(node), executor);
        }

        futures.put(node.getId(), currentFuture);
        return currentFuture;
    }

    private void runTask(TaskNode node) {
        try {
            System.out.println("[" + Thread.currentThread().getName() + "] 开始执行节点: " + node.getId());
            node.run();
            System.out.println("[" + Thread.currentThread().getName() + "] 完成节点: " + node.getId());
        } catch (Exception e) {
            throw new RuntimeException("节点 " + node.getId() + " 执行失败", e);
        }
    }

    // 基于 DFS 算法进行成环检测 (White/Gray/Black 三色标记法)
    private boolean hasCycle() {
        Map<String, Integer> state = new HashMap<>(); // 0: unvisited, 1: visiting, 2: visited
        for (String id : nodeMap.keySet()) {
            if (dfsCycle(nodeMap.get(id), state)) {
                return true;
            }
        }
        return false;
    }

    private boolean dfsCycle(TaskNode node, Map<String, Integer> state) {
        int status = state.getOrDefault(node.getId(), 0);
        if (status == 1) return true; // 发现环
        if (status == 2) return false;

        state.put(node.getId(), 1); // 标记为正在访问
        for (TaskNode dep : node.getDependencies()) {
            if (dfsCycle(dep, state)) return true;
        }
        state.put(node.getId(), 2); // 标记为访问完毕
        return false;
    }
}