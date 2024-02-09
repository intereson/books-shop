package by.intereson.ebookservice.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Aspect
@Component
public class TransactionAspect {
    @Before("execution(* by.intereson.ebookservice.services..*(..)) " +
            "&& @annotation(org.springframework.transaction.annotation.Transactional)")
    public void beforeTransactionMethod(){
        if(TransactionSynchronizationManager.isActualTransactionActive()){
            System.out.println("New transaction");
        } else {
            System.out.println("Old transaction continue");
        }
    }
}
