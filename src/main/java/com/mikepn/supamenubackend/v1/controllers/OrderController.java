package com.mikepn.supamenubackend.v1.controllers;

import com.mikepn.supamenubackend.v1.dto.request.order.CreateOrderDTO;
import com.mikepn.supamenubackend.v1.dto.response.order.OrderResponseDTO;
import com.mikepn.supamenubackend.v1.payload.ApiResponse;
import com.mikepn.supamenubackend.v1.services.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
@Tag(name = "Order")
public class OrderController {

    private final IOrderService orderService;


    @PostMapping("/place-order")
    @Operation(summary = "Place Order")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> placeOrder(@Valid @RequestBody CreateOrderDTO dto){
        try{
            OrderResponseDTO response = orderService.createOrder(dto);
            return ApiResponse.success("Order Placed Successfully", HttpStatus.CREATED, response);
        } catch (Exception e) {
            return ApiResponse.fail("Failed to place order", HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
