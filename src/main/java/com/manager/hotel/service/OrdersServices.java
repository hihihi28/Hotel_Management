package com.manager.hotel.service;

import com.manager.hotel.commons.Constant;
import com.manager.hotel.dto.OrdersResponseDTO;
import com.manager.hotel.model.Orders;
import com.manager.hotel.model.Room;
import com.manager.hotel.model.User;
import com.manager.hotel.repository.OrdersRepository;
import com.manager.hotel.repository.RoomRepository;
import com.manager.hotel.repository.UserRepository;
import com.manager.hotel.security.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrdersServices {

    private final OrdersRepository ordersRepository;
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;


    public Long getCurrentUserId() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDetails.getId();
    }

    public OrdersServices(OrdersRepository ordersRepository, RoomRepository roomRepository, UserRepository userRepository) {
        this.ordersRepository = ordersRepository;
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

    public void addNewOrderByUser(Orders orders) {
        orders.setUserId(getCurrentUserId());
        orders.setCreateDate(new Date());
        orders.setDeleteFlg(Constant.DeleteFlg.NON_DELETE);
        orders.setStatus(Constant.OrderStatus.PENDING);
        ordersRepository.save(orders);
    }

    public void changeOrderStatus(Orders orders) {
        Orders oldOrders = ordersRepository.findById(orders.getId()).get();
        oldOrders.setStatus(orders.getStatus());
        oldOrders.setUpdateDate(new Date());
        ordersRepository.save(oldOrders);
    }

    public List<OrdersResponseDTO> getMyBookings() {
        List<Orders> ordersList = ordersRepository.findByUserIdAndDeleteFlg(getCurrentUserId(), Constant.DeleteFlg.NON_DELETE);
        List<OrdersResponseDTO> ordersResponseDTOList = new ArrayList<>();
        for(Orders orders: ordersList) {
            OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO(orders);
            Room room = roomRepository.findById(orders.getRoomId()).get();
            ordersResponseDTO.setName(room.getName());
            ordersResponseDTO.setType(room.getType());
            ordersResponseDTOList.add(ordersResponseDTO);
        }
        return ordersResponseDTOList;
    }

    public List<OrdersResponseDTO> getAllReservations() {
        List<Orders> ordersList = ordersRepository.findByDeleteFlg(Constant.DeleteFlg.NON_DELETE);
        List<OrdersResponseDTO> ordersResponseDTOList = new ArrayList<>();
        for(Orders orders: ordersList) {
            OrdersResponseDTO ordersResponseDTO = new OrdersResponseDTO(orders);
            Room room = roomRepository.findById(orders.getRoomId()).get();
            ordersResponseDTO.setName(room.getName());
            ordersResponseDTO.setType(room.getType());
            User user = userRepository.findById(orders.getUserId()).get();
            ordersResponseDTO.setUsername(user.getUsername());
            ordersResponseDTOList.add(ordersResponseDTO);
        }
        return ordersResponseDTOList;
    }

}
