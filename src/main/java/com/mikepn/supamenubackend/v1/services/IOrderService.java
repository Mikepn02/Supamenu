package com.mikepn.supamenubackend.v1.services;


import com.mikepn.supamenubackend.v1.dto.request.order.CreateOrderDTO;
import com.mikepn.supamenubackend.v1.dto.response.order.OrderResponseDTO;

public interface IOrderService {

    OrderResponseDTO createOrder(CreateOrderDTO dto);
}
