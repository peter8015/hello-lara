package designpattern.chainofresponsibility;



/**
 * @author zhanghaibing
 * @date 2024-03-14
 */
public class ConcreteHandlerA implements Handler {
    private Handler nextHandler;

    @Override
    public void handleRequest(String request) {
        if ("A".equals(request)) {
            System.out.println("ConcreteHandlerA 可以处理该请求: " + request);
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

