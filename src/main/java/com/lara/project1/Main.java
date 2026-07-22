package com.lara.project1;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws Exception {
        // 创建线程池
        ExecutorService executor = Executors.newFixedThreadPool(4);
        DagEngine engine = new DagEngine(executor);

        // 构造具体任务节点
        TaskNode taskA = new TaskNode("Task-A") {
            @Override
            public void run() throws Exception {
                Thread.sleep(1000); // 模拟耗时逻辑
            }
        };

        TaskNode taskB = new TaskNode("Task-B") {
            @Override
            public void run() throws Exception {
                Thread.sleep(500);
            }
        };

        TaskNode taskC = new TaskNode("Task-C") {
            @Override
            public void run() throws Exception {
                Thread.sleep(300);
            }
        };

        TaskNode taskD = new TaskNode("Task-D") {
            @Override
            public void run() throws Exception {
                Thread.sleep(400);
            }
        };

        // 配置依赖关系
        taskC.dependsOn(taskA);
        taskD.dependsOn(taskA).dependsOn(taskB);

        // 注册节点到引擎
        engine.addNode(taskA);
        engine.addNode(taskB);
        engine.addNode(taskC);
        engine.addNode(taskD);

        System.out.println("--- 开始执行 DAG ---");
        long start = System.currentTimeMillis();

        engine.execute();

        System.out.println("--- DAG 执行完毕，总耗时: " + (System.currentTimeMillis() - start) + " ms ---");

        executor.shutdown();
    }
}