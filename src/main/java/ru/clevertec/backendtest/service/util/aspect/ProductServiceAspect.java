package ru.clevertec.backendtest.service.util.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.clevertec.backendtest.model.product.Product;
import ru.clevertec.backendtest.service.util.cache.Cache;
import ru.clevertec.backendtest.service.util.cache.CacheFactory;

import java.util.List;
import java.util.Objects;

@Aspect
@Component
public class ProductServiceAspect {
    private final Cache<Integer, Object> productCache;

    @Autowired
    public ProductServiceAspect(CacheFactory cacheFactory) {
        this.productCache = cacheFactory.createCache();
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.ProductService.getById(..))")
    private void getById() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.ProductService.getAllById(..))")
    private void getAllById() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.ProductService.getAll(..))")
    private void getAll() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.ProductService.create(..))")
    private void create() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.ProductService.update(..))")
    private void update() {
    }

    @Pointcut("execution(* ru.clevertec.backendtest.service.ProductService.deleteById(..))")
    private void deleteById() {
    }

    @Around(value = "getById()", argNames = "joinPoint")
    private Object getFromCache(ProceedingJoinPoint joinPoint) throws Throwable {
        Integer id = (Integer) joinPoint.getArgs()[0];
        Product product = (Product) productCache.get(id);
        return product != null ? product : joinPoint.proceed(joinPoint.getArgs());
    }

    @AfterReturning(value = "getById()", returning = "returnedValue")
    private void putInCacheIfNotExist(Object returnedValue) {
        if (Objects.nonNull(returnedValue))
            if (productCache.get(((Product) returnedValue).getId()) == null)
                productCache.put(((Product) returnedValue).getId(), returnedValue);
    }

    @Around(value = "getAllById()", argNames = "joinPoint")
    private List<Object> getAllByIdFromCache(ProceedingJoinPoint joinPoint) {
        List<Integer> ids = (List<Integer>) joinPoint.getArgs()[0];
        return ids.stream().map(id -> {
            Product product = (Product) productCache.get(id);
            try {
                return product != null ? product : joinPoint.proceed(joinPoint.getArgs());
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }).toList();
    }

    @AfterReturning(value = "getAllById()", returning = "returnedValue")
    private void putAllByIdInCacheIfNotExist(Object returnedValue) {
        List<Object> products = (List<Object>) returnedValue;
        products.stream().filter(Objects::nonNull).forEach(product -> {
            if (productCache.get(((Product) product).getId()) == null)
                productCache.put(((Product) product).getId(), product);
        });
    }

    @AfterReturning(value = "getAll()", returning = "returnedValue")
    private void putAllInCacheIfNotExist(Object returnedValue) {
        List<Object> products = (List<Object>) returnedValue;
        products.stream().filter(Objects::nonNull).forEach(product -> {
            if (productCache.get(((Product) product).getId()) == null)
                productCache.put(((Product) product).getId(), product);
        });
    }

    @After(value = "create()")
    private void putInCache(JoinPoint joinPoint) {
        Object product = joinPoint.getArgs()[0];
        if (Objects.nonNull(product))
            productCache.put(((Product) product).getId(), product);
    }

    @After(value = "update()")
    private void updateInCache(JoinPoint joinPoint) {
        Object product = joinPoint.getArgs()[0];
        if (Objects.nonNull(product))
            productCache.put(((Product) product).getId(), product);
    }

    @After(value = "deleteById()")
    private void deleteFromCache(JoinPoint joinPoint) {
        Integer id = (Integer) joinPoint.getArgs()[0];
        productCache.delete(id);
    }
}
