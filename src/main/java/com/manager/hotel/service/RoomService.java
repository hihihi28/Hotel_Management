package com.manager.hotel.service;

import com.manager.hotel.commons.Constant;
import com.manager.hotel.dto.RoomResponseDTO;
import com.manager.hotel.model.Orders;
import com.manager.hotel.model.Room;
import com.manager.hotel.repository.OrdersRepository;
import com.manager.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    private final OrdersRepository ordersRepository;

    public RoomService(RoomRepository roomRepository, OrdersRepository ordersRepository) {
        this.roomRepository = roomRepository;
        this.ordersRepository = ordersRepository;
    }

    public List<Room> findAllByAdmin() {
        return roomRepository.findByDeleteFlg(Constant.DeleteFlg.NON_DELETE);
    }

    public List<RoomResponseDTO> findAllByUser() {
        List<Room> roomList = roomRepository.findByDeleteFlg(Constant.DeleteFlg.NON_DELETE);
        List<RoomResponseDTO> roomResponseDTOList = new ArrayList<>();
        for (Room room: roomList) {
            RoomResponseDTO roomResponseDTO = new RoomResponseDTO(room);

            // Kiểm tra phòng có đang ở trng thái Pending hoặc Approved, nếu có thì đổi status mặc định từ Empty -> [Pending, Approved]
            List<Orders> getOrderByRoomAndStatus = ordersRepository.findOrdersByRoomIdAndStatus(room.getId());
            if (!getOrderByRoomAndStatus.isEmpty()) {
                roomResponseDTO.setStatus(getOrderByRoomAndStatus.get(0).getStatus());
            }
            roomResponseDTOList.add(roomResponseDTO);
        }
        return roomResponseDTOList;
    }

    public void addNewRoom(Room room) {
        room.setCreateDate(new Date());
        room.setDeleteFlg(Constant.DeleteFlg.NON_DELETE);
        roomRepository.save(room);
    }

    public void updateRoom(Room room) {
        Optional<Room> oldRoom = roomRepository.findById(room.getId());
        if (oldRoom.isEmpty()) return ;
        oldRoom.get().setName(room.getName());
        oldRoom.get().setType(room.getType());
        oldRoom.get().setPrice(room.getPrice());
        oldRoom.get().setUpdateDate(new Date());
        roomRepository.save(oldRoom.get());
    }

    public void deleteRoom(Room room) {
        Optional<Room> oldRoom = roomRepository.findById(room.getId());
        if (oldRoom.isEmpty()) return ;
        oldRoom.get().setDeleteFlg(Constant.DeleteFlg.DELETE);
        oldRoom.get().setUpdateDate(new Date());
        roomRepository.save(oldRoom.get());
    }

    public Optional<Room> getById(long id) {
        return roomRepository.findById(id);
    }
}
