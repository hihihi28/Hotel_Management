package com.manager.hotel.dto;

import com.manager.hotel.model.Orders;

public class OrdersResponseDTO {
    private Long id;
    private Long userId;
    private Long roomId;
    private String name;
    private String type;
    private String checkinDate;
    private String checkoutDate;
    private Long price;
    private String status;
    private String username;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(String checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(String checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public OrdersResponseDTO(Orders order) {
        this.id = order.getId();
        this.userId = order.getUserId();
        this.roomId = order.getRoomId();
        this.checkinDate = order.getCheckinDate();
        this.checkoutDate = order.getCheckoutDate();
        this.price = order.getPrice();
        this.status = order.getStatus();
    }


}
