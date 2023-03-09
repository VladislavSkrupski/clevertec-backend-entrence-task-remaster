package ru.clevertec.backendtest.service.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.backendtest.model.discountCard.DiscountCard;
import ru.clevertec.backendtest.service.util.cache.Cache;
import ru.clevertec.backendtest.service.util.cache.CacheFactory;

import java.util.Objects;

@Aspect
@Component
public class DiscountCardServiceAspect {
    private final Cache<Integer, Object> discountCardCache;

    @Autowired
    public DiscountCardServiceAspect(CacheFactory cacheFactory) {
        this.discountCardCache = cacheFactory.createCache();
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.DiscountCardService.getById(..))")
    private void getById() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.DiscountCardService.create(..))")
    private void create() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.DiscountCardService.update(..))")
    private void update() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.DiscountCardService.deleteById(..))")
    private void deleteById() {
    }

    @Around(value = "getById()", argNames = "joinPoint")
    private Object getFromCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer id = (Integer) joinPoint.getArgs()[0];
        DiscountCard discountCard = (DiscountCard) discountCardCache.get(id);
        return discountCard != null ? discountCard : joinPoint.proceed(joinPoint.getArgs());
    }

    @AfterReturning(value = "getById()", returning = "returnedValue")
    private void putInCacheIfNotExist(Object returnedValue) {
        if (Objects.nonNull(returnedValue))
            if (discountCardCache.get(((DiscountCard) returnedValue).getId()) == null)
                discountCardCache.put(((DiscountCard) returnedValue).getId(), returnedValue);
    }

    @After(value = "create()")
    private void putInCache(JoinPoint joinPoint) {
        Object discountCard = joinPoint.getArgs()[0];
        if (Objects.nonNull(discountCard))
            discountCardCache.put(((DiscountCard) discountCard).getId(), discountCard);
    }

    @After(value = "update()")
    private void updateInCache(JoinPoint joinPoint) {
        Object discountCard = joinPoint.getArgs()[0];
        if (Objects.nonNull(discountCard))
            discountCardCache.put(((DiscountCard) discountCard).getId(), discountCard);
    }

    @After(value = "deleteById()")
    private void deleteFromCache(JoinPoint joinPoint) {
        Integer id = (Integer) joinPoint.getArgs()[0];
        discountCardCache.delete(id);
    }
}
