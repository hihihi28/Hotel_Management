package com.manager.hotel.controller;


import com.manager.hotel.dto.RoomResponseDTO;
import com.manager.hotel.model.Room;
import com.manager.hotel.service.RoomService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller()
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // ADMIN //

    @GetMapping("/admin/admin-dashboard")
    public String getAll(Model model) {
        try {
            List<Room> rooms = roomService.findAllByAdmin();
            model.addAttribute("rooms", rooms);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "admin/admin_dashboard";
    }

    @PostMapping("/admin/add-new-room")
    public String addRoom(Room room, RedirectAttributes redirectAttributes) {
        try {
            roomService.addNewRoom(room);
            redirectAttributes.addFlashAttribute("message", "The Room has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/admin/admin-dashboard";
    }

    @PostMapping("/admin/update-room")
    public String updateRoom(Room room, RedirectAttributes redirectAttributes) {
        try {
            roomService.updateRoom(room);
            redirectAttributes.addFlashAttribute("message", "The Room has been updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/admin/admin-dashboard";
    }

    @PostMapping("/admin/delete-room")
    public String deleteRoom(Room room, RedirectAttributes redirectAttributes) {
        try {
            roomService.deleteRoom(room);
            redirectAttributes.addFlashAttribute("message", "The Room has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/admin/admin-dashboard";
    }

    // USER //
    @GetMapping("/user/user-dashboard")
    public String getAllByUser(Model model) {
        try {
            List<RoomResponseDTO> rooms = roomService.findAllByUser();
            model.addAttribute("rooms", rooms);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "user/user_dashboard";
    }
}
