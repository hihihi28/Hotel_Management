package com.manager.hotel.repository;

import com.manager.hotel.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserIdAndDeleteFlg(Long userId, String deleteFlg);

    List<Orders> findByDeleteFlg(String deleteFlg);

    @Query("""
        SELECT o 
        FROM Orders o
        WHERE o.roomId = :roomId
          AND o.status IN ('Pending', 'Approved')
    """)
    List<Orders> findOrdersByRoomIdAndStatus(@Param("roomId") Long roomId);
}
