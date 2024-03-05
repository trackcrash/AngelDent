package com.example.first.controller;

import com.example.first.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminApiController {

    private final AdminService adminService;

    //예약 상태 변경
    @RequestMapping("/changeBookingStatus")
    public void changeBookingStatus(Long id) {
        adminService.changeBookingStatus(id);
    }

    //관리자 권한 변경
    @RequestMapping("/changeRole")
    public void changeRole(Long id) {
        adminService.changeRole(id);
    }

    //사용자 삭제
    @RequestMapping("/deleteUser")
    public void deleteUser(Long id) {
        adminService.deleteUser(id);
    }

}
