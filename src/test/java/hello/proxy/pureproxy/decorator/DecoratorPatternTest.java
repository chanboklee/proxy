package hello.proxy.pureproxy.decorator;

import hello.proxy.pureproxy.decorator.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * Decorator Pattern
 * -> 부가기능 추가
 */
@Slf4j
public class DecoratorPatternTest {

    /**
     * client -> realComponent
     */
    @Test
    void noDecorator(){
        Component realComponent = new RealComponent();
        DecoratorPatternClient client = new DecoratorPatternClient(realComponent);

        client.execute();
    }

    /**
     * client -> MessageDecorator -> realComponent
     */
    @Test
    void decorator1(){
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        DecoratorPatternClient client = new DecoratorPatternClient(messageDecorator);

        client.execute();
    }

    /**
     * client -> TimeDecorator -> MessageDecorator -> realComponent
     */
    @Test
    void decorator2(){
        Component realComponent = new RealComponent();
        Component messageDecorator = new MessageDecorator(realComponent);
        Component timeDecorator = new TimeDecorator(messageDecorator);
        DecoratorPatternClient client = new DecoratorPatternClient(timeDecorator);

        client.execute();
    }
}
