package com.manager.hotel.dto;

import com.manager.hotel.commons.Constant;
import com.manager.hotel.model.Room;

public class RoomResponseDTO {
    private Long id;
    private String name;
    private String type;
    private Long price;
    private String status;

    public RoomResponseDTO(Room room) {
        this.id = room.getId();
        this.name = room.getName();
        this.type = room.getType();
        this.price = room.getPrice();
        this.status = Constant.OrderStatus.EMPTY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.status = status;
    }
}
