package designpattern.chainofresponsibility;

/**
 * @author zhanghaibing
 * @date 2024-03-14
 */
public class Client {
    public static void main(String[] args) {
        Handler handlerA = new ConcreteHandlerA();
        Handler handlerB = new ConcreteHandlerB();

        handlerA.setNext(handlerB);

        handlerA.handleRequest("A"); // ConcreteHandlerA 可以处理该请求: A
        handlerA.handleRequest("B"); // ConcreteHandlerB 可以处理该请求: B
        handlerA.handleRequest("C"); // 无人可以处理该请求: C
    }
}