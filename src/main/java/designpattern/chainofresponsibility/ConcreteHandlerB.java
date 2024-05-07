package designpattern.chainofresponsibility;


/**
 * @author zhanghaibing
 * @date 2024-03-14
 */
public class ConcreteHandlerB implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(String request) {
        if ("B".equals(request)) {
            System.out.println("ConcreteHandlerB 可以处理该请求: " + request);
        } else {
            if (nextHandler != null) {
                nextHandler.handleRequest(request);
            } else {
                System.out.println("无人可以处理该请求: " + request);
            }
        }
    }

    @Override
    public void setNext(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }
}