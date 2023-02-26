package hello.proxy.jdkdynamic;

import hello.proxy.jdkdynamic.code.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;

/**
 * proxy.call() 실행
 * JDK 동적 프록시는 InvocationHandler.invoke() 호출 -> TimeInvocationHandler 가 구현체로 있으므로 TimeInvocationHandler.invoke() 호출
 * AImpl 인스턴스의 call() 이 실행
 * AImpl 인스턴스의 call()의 실행이 끝나면 TimeInvocationHandler 로 응답이 돌아온다. 시간 로그를 출력하고 결과를 반환.
 */
@Slf4j
public class JdkDynamicProxyTest {

    @Test
    void dynamicA(){
        AInterface target = new AImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        AInterface proxy = (AInterface) Proxy.newProxyInstance(AInterface.class.getClassLoader(), new Class[]{AInterface.class}, handler);
        proxy.call();
        log.info("targetClass -> {}", target.getClass());
        log.info("proxyClass -> {}", proxy.getClass());
    }

    @Test
    void dynamicB(){
        BInterface target = new BImpl();
        TimeInvocationHandler handler = new TimeInvocationHandler(target);

        BInterface proxy = (BInterface) Proxy.newProxyInstance(BInterface.class.getClassLoader(), new Class[]{BInterface.class}, handler);
        proxy.call();
        log.info("targetClass -> {}", target.getClass());
        log.info("proxyClass -> {}", proxy.getClass());
    }
}
