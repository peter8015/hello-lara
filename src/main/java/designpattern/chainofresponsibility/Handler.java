package designpattern.chainofresponsibility;


/**
 * @author zhanghaibing
 * @date 2024-03-14
 */
public interface Handler {
    void handleRequest(String request);
    void setNext(Handler nextHandler);
}
