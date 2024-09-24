package service.impl;

import infrastructure.annotation.Log;
import model.OrderDetails;
import service.api.crud.OrderDetailsService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProxyOrderDetailsService implements OrderDetailsService {

    private final OrderDetailsService proxy;

    public ProxyOrderDetailsService(OrderDetailsService proxy) {
        this.proxy = proxy;
    }

    @Override
    public OrderDetails save(OrderDetails entity) {
        Method proxyMethod = null;
        try {
            proxyMethod = proxy.getClass().getMethod("save", OrderDetails.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        proxyMethod.setAccessible(true);
        boolean isPresents = Arrays.stream(proxyMethod.getAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(Log.class));

        if (isPresents) {
            String methodName = proxyMethod.getName();
            String originClassName = proxyMethod.getDeclaringClass().getName();
            System.out.println("Start invoke method %s from %s class".formatted(methodName, originClassName));
            long startTime = System.currentTimeMillis();

//            try {
//                proxyMethod.invoke(entity);
//
//            } catch (IllegalAccessException | InvocationTargetException e) {
//                throw new RuntimeException(e);
//            }
            proxy.save(entity);
            proxyMethod.setAccessible(false);

            long wastedTime = System.currentTimeMillis() - startTime;
            System.out.println("The method %s has been successfully completed; Wasted time: %d millis".formatted(methodName, wastedTime));

        }
        return entity;
    }

    @Override
    public OrderDetails update(OrderDetails entity) {
        return null;
    }

    @Override
    public Optional<OrderDetails> getById(UUID id) {
        return Optional.empty();
    }

    @Override
    public List<OrderDetails> getAll() {
        return List.of();
    }

    @Override
    public void delete(OrderDetails entity) {

    }

    private void log() {
        Method proxyMethod = null;
        try {
            proxyMethod = proxy.getClass().getMethod("save", OrderDetails.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        proxyMethod.setAccessible(true);
        boolean isPresents = Arrays.stream(proxyMethod.getAnnotations())
                .anyMatch(annotation -> annotation.annotationType().equals(Log.class));

        if (isPresents) {
            String methodName = proxyMethod.getName();
            System.out.println("Start invoke method %s from %s class".formatted(methodName, originClassName));
                       proxyMethod.setAccessible(false);
            System.out.println("The method %s has been successfully completed; Wasted time: %d millis".formatted(methodName, wastedTime));

        }
    }
}
