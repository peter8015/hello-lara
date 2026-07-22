package com.lara.project1;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

// 任务接口
public abstract class TaskNode {
    private final String id;
    private final Set<TaskNode> dependencies = new HashSet<>();

    public TaskNode(String id) {
        this.id = id;
    }

    // 添加上游依赖节点
    public TaskNode dependsOn(TaskNode parent) {
        this.dependencies.add(parent);
        return this;
    }

    public String getId() {
        return id;
    }

    public Set<TaskNode> getDependencies() {
        return dependencies;
    }

    // 具体业务逻辑
    public abstract void run() throws Exception;
}