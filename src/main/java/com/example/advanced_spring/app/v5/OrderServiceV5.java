package com.example.advanced_spring.app.v5;

import com.example.advanced_spring.app.v3.OrderRepositoryV3;
import com.example.advanced_spring.trace.callback.TraceTemplate;
import com.example.advanced_spring.trace.logtrace.LogTrace;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceV5 {

    private final OrderRepositoryV3 orderRepository;
    private final TraceTemplate template;

    public OrderServiceV5(OrderRepositoryV3 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId) {
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
