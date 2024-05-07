package coding.topic1;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class BatchProcessorTest {

    private static class TestBatchNode extends BatchNode {
        private boolean shouldSucceed;
        private int executionCount;

        public TestBatchNode(String name, boolean shouldSucceed) {
            super(name);
            this.shouldSucceed = shouldSucceed;
            this.executionCount = 0;
        }

        @Override
        public boolean execute() {
            executionCount++;
            return shouldSucceed;
        }

        public int getExecutionCount() {
            return executionCount;
        }
    }

    @Test
    public void testTopologicalSort() {
        BatchDependencyGraph dependencyGraph = new BatchDependencyGraph();
        BatchProcessor processor = new BatchProcessor(3, dependencyGraph);

        BatchNode a = new TestBatchNode("A", true);
        BatchNode b = new TestBatchNode("B", true);
        BatchNode c = new TestBatchNode("C", true);
        BatchNode d = new TestBatchNode("D", true);

        dependencyGraph.addDependency(d, c);
        dependencyGraph.addDependency(c, b);
        dependencyGraph.addDependency(b, a);

        List<BatchNode> sortedNodes = processor.topologicalSort();

        assertEquals(Arrays.asList(a, b, c, d), sortedNodes);
    }

    @Test
    public void testProcessAllSuccess() {
        BatchDependencyGraph dependencyGraph = new BatchDependencyGraph();
        BatchProcessor processor = new BatchProcessor(3, dependencyGraph);

        TestBatchNode a = new TestBatchNode("A", true);
        TestBatchNode b = new TestBatchNode("B", true);
        TestBatchNode c = new TestBatchNode("C", true);
        TestBatchNode d = new TestBatchNode("D", true);

        dependencyGraph.addDependency(d, c);
        dependencyGraph.addDependency(c, b);
        dependencyGraph.addDependency(b, a);

        processor.processAll();

        assertEquals(1, a.getExecutionCount());
        assertEquals(1, b.getExecutionCount());
        assertEquals(1, c.getExecutionCount());
        assertEquals(1, d.getExecutionCount());
    }

    @Test
    public void testProcessAllWithRetry() {
        BatchDependencyGraph dependencyGraph = new BatchDependencyGraph();
        BatchProcessor processor = new BatchProcessor(3, dependencyGraph);

        // B节点第一次执行失败，第二次执行成功
        TestBatchNode a = new TestBatchNode("A", true);
        TestBatchNode b = new TestBatchNode("B", false); // 初始设置为失败
        b.shouldSucceed = true; //
    }
}