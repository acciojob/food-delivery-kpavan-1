package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;
    @Override
    public OrderDto createOrder(OrderDto order) {
        OrderEntity orderEntity=OrderEntity.builder().orderId(order.getOrderId()).id(order.getId()).cost(order.getCost()).items(order.getItems()).userId(order.getUserId()).status(order.isStatus()).build();
        orderRepository.save(orderEntity);
        return OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        return OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity=orderRepository.findByOrderId(orderId);
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setCost(order.getCost());
        orderEntity.setId(order.getId());
        orderEntity.setItems(order.getItems());
        orderEntity.setStatus(order.isStatus());
        orderEntity.setUserId(order.getUserId());
        orderEntity=orderRepository.save(orderEntity);
        return OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build();
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        orderRepository.delete(orderRepository.findByOrderId(orderId));
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderDto> orderDtos=null;
        List<OrderEntity> orderEntities= (List<OrderEntity>) orderRepository.findAll();
        for(OrderEntity orderEntity:orderEntities){
            orderDtos.add(OrderDto.builder().orderId(orderEntity.getOrderId()).id(orderEntity.getId()).cost(orderEntity.getCost()).items(orderEntity.getItems()).status(orderEntity.isStatus()).userId(orderEntity.getUserId()).build());
        }
        return orderDtos;
    }
}