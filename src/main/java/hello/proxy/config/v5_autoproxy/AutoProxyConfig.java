package hello.proxy.config.v5_autoproxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.v3_proxyfactory.advice.LogTraceAdvice;
import hello.proxy.trace.logtrace.LogTrace;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * [포인트컷은 2가지에 사용]
 * 1. 프록시 적용 여부 판단 - 생성 단계
 * 자동 프록시 생성기는 포인트컷을 사용해서 해당 빈이 프록시를 생성할 필요가 있는지 없는지 체크
 * 클래스 + 메서드 조건을 모두 비교 -> 모든 메서드를 체크하는데, 포인트컷 조건에 하나하나 매칭하고 만약 조건에 맞는 것이 하나라도 있으면 프록시를 생성
 * ex) orderControllerV1 은 request(), noLog() 가 있는데 request() 가 조건에 만족하므로 프록시를 생성
 *
 * 2. 어드바이스 적용 여부 판단 - 사용 단계
 * 프록시가 호출되었을 때 부가 기능인 어드바이스를 적용할지 말지 포인트컷을 보고 판단
 * orderControllerV1 은 이미 프록시가 걸려있다.
 * orderControllerV1 의 request() 는 현재 포인트컷 조건에 만족하므로 프록시는 어드바이스를 먼저 호출하고 target 을 호출
 * orderControllerV1 의 noLog() 는 현재 포인트컷 조건에 만족하지 않으므로 어드바이스를 호출하지 않고 바로 target 만 호출
 */
@Configuration
@Import({AppV1Config.class, AppV2Config.class})
public class AutoProxyConfig {

    // @Bean
    public Advisor getAdvisor1(LogTrace logTrace) {
        // pointcut
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("request*", "order*", "save*");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    // @Bean
    public Advisor getAdvisor2(LogTrace logTrace) {
        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..))");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }

    @Bean
    public Advisor getAdvisor3(LogTrace logTrace) {
        // pointcut
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* hello.proxy.app..*(..)) && !execution(* hello.proxy.app..noLog(..))");

        // advice
        LogTraceAdvice advice = new LogTraceAdvice(logTrace);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }
}
