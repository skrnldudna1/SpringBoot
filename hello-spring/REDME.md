### AOP (Aspect-Oriented Programming)

프로그램의 핵심 로직과 공통적으로 사용되는 부가 기능(로깅, 보안, 트랜잭션 등을)을 분리해 <br/>
코드의 가독성과 유지보수를 향상시키는 프로그래밍 기법
```
ex
@Component
@Aspect
public class LoggingAspect {

    // 특정 패키지와 메서드에 적용
    @Around("execution(* com.example.service..*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.println("START: " + joinPoint.getSignature());

        Object proceed = joinPoint.proceed(); // 실제 메서드 실행

        long executionTime = System.currentTimeMillis() - start;
        System.out.println("END: " + joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }
}


```

@Aspect: AOP 기능을 가진 클래스임을 나타냄.<br/>
@Around: 지정된 메서드 실행 전후에 코드를 실행.<br/>
execution(* com.example.service..*(..)): com.example.service 패키지의 모든 메서드에 적용.