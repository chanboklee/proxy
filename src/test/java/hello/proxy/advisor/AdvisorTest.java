package hello.proxy.advisor;

import hello.proxy.common.ServiceImpl;
import hello.proxy.common.ServiceInterface;
import hello.proxy.common.advice.TimeAdvice;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;

public class AdvisorTest {

    /**
     * Advisor -> 하나의 PointCut + 하나의 Advice 로 구성
     * new DefaultPointcutAdvisor 을 선언
     * 앞서 addAdvice 를 통하여 생성하였을 때도 내부적으로는 new DefaultPointcutAdvisor 을 호출하여 Advisor 생성
     * 프록시 팩토리를 사용할 때 Advisor 은 필수이다.
     */
    @Test
    void advisorTest(){
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

    }
}
