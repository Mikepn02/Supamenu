package com.mikepn.supamenubackend.v1.services.impl;

import com.mikepn.supamenubackend.v1.Mapper.order.OrderMapper;
import com.mikepn.supamenubackend.v1.dto.request.order.CreateOrderDTO;
import com.mikepn.supamenubackend.v1.dto.request.order.OrderItemDTO;
import com.mikepn.supamenubackend.v1.dto.response.order.OrderResponseDTO;
import com.mikepn.supamenubackend.v1.enums.EOrderStatus;
import com.mikepn.supamenubackend.v1.exceptions.AppException;
import com.mikepn.supamenubackend.v1.models.*;
import com.mikepn.supamenubackend.v1.repositories.*;
import com.mikepn.supamenubackend.v1.services.IOrderService;
import com.mikepn.supamenubackend.v1.services.IUserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepository orderRepository;
    private final IUserService userService;
    private final IOrderItemRepository orderItemRepository;
    private final IRestaurantRepository restaurantRepository;
    private final ITemRepository iTemRepository;
    private final ICustomerRepository customerRepository;
    private final OrderMapper orderMapper;

    @Transactional
    @Override
    public OrderResponseDTO createOrder(CreateOrderDTO dto) {
       try{
           User loggedInUser = userService.getLoggedInUser();
           Customer customer = customerRepository.findByProfile(loggedInUser)
                   .orElseThrow(() -> new RuntimeException("Customer not found"));

           Restaurant restaurant = restaurantRepository.findById(dto.getRestaurantId())
                   .orElseThrow(() -> new RuntimeException("Restaurant not found"));


           Order order = Order.builder()
                   .customer(customer)
                   .restaurant(restaurant)
                   .status(EOrderStatus.PENDING)
                   .build();


           List<OrderItem> orderItems = new ArrayList<>();
           BigDecimal totalPrice = BigDecimal.ZERO;

           for (OrderItemDTO itemDTO: dto.getItems()) {
               Item item = iTemRepository.findById(itemDTO.getItemId())
                       .orElseThrow(() -> new RuntimeException("Item not found"));

               if(item.getQuantityAvailable() < itemDTO.getQuantity()){
                   throw new AppException("Not enough stock for item: " + item.getName());
               }

               item.setQuantityAvailable(item.getQuantityAvailable() - itemDTO.getQuantity());
               iTemRepository.save(item);

               OrderItem orderItem = OrderItem.builder()
                       .item(item)
                       .quantity(itemDTO.getQuantity())
                       .order(order)
                       .priceAtOrderTime(item.getPrice())
                       .build();

               orderItems.add(orderItem);
               totalPrice = totalPrice.add(BigDecimal.valueOf(item.getPrice() * itemDTO.getQuantity()));
           }

           order.setTotalPrice(totalPrice);
           order.setItems(orderItems);
           order = orderRepository.save(order);
           orderItemRepository.saveAll(orderItems);

           return orderMapper.toResponseDTO(order);
       } catch (Exception e) {
           throw new AppException("Failed to create order: " + e.getMessage());
       }
    }
}
