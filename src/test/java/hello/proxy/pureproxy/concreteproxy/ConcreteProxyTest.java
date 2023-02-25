package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConCreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConCreteClient client = new ConCreteClient(concreteLogic);

        client.execute();
    }

    /**
     * 다형성에 의해 ConcreteLogic 에 concreteLogic 도 들어갈 수 있고 timeProxy 도 들어갈 수 있다.
     * -> 부모타입에 자식타입 할당
     */
    @Test
    void addProxy(){
        ConcreteLogic concreteLogic = new ConcreteLogic();
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        ConCreteClient client = new ConCreteClient(timeProxy);

        client.execute();
    }
}
