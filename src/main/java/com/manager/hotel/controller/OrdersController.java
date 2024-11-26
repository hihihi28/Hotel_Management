package com.manager.hotel.controller;

import com.manager.hotel.dto.OrdersResponseDTO;
import com.manager.hotel.model.Orders;
import com.manager.hotel.service.OrdersServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class OrdersController {

    private final OrdersServices ordersServices;

    public OrdersController(OrdersServices ordersServices) {
        this.ordersServices = ordersServices;
    }

    @GetMapping("/admin/reservations")
    public String getAllReservations(Model model) {
        try {
            List<OrdersResponseDTO> orders = ordersServices.getAllReservations();
            model.addAttribute("orders", orders);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "admin/reservations";
    }

    @PostMapping("/admin/change-order-status")
    public String changeOrderStatus(Orders orders, RedirectAttributes redirectAttributes) {
        try {
            ordersServices.changeOrderStatus(orders);
            redirectAttributes.addFlashAttribute("message", "The Order has been updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/admin/reservations";
    }

    @PostMapping("/user/add-new-order")
    public String addNewOrders(Orders orders, RedirectAttributes redirectAttributes) {
        try {
            ordersServices.addNewOrderByUser(orders);
            redirectAttributes.addFlashAttribute("message", "The Order has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/user/bookings";
    }

    @GetMapping("/user/bookings")
    public String getMyBooking(Model model) {
        try {
            List<OrdersResponseDTO> orders = ordersServices.getMyBookings();
            model.addAttribute("orders", orders);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "user/bookings";
    }
}
