package com.example.first.service;

import com.example.first.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final UserRepository userRepository;
    private final BoardService boardService;
    private final BookingService bookingService;

    public boolean isAdmin(Long id) {
        return userRepository.findById(id).get().getRole().equals("ROLE_ADMIN");
    }

    public void deleteUser(Long id) {
        if(isAdmin(id)) {
            throw new RuntimeException("관리자는 삭제할 수 없습니다.");
        }else if(userRepository.findById(id).isEmpty()) {
            throw new RuntimeException("존재하지 않는 사용자입니다.");
        }else {
            userRepository.deleteById(id);
        }
    }

    public void changeRole(Long id) {
        if(isAdmin(id)) {
            userRepository.findById(id).get().setRole("ROLE_USER");
        }else {
            userRepository.findById(id).get().setRole("ROLE_ADMIN");
        }
    }

    public void changeBookingStatus(Long id) {
        bookingService.changeBookingStatus(id);
    }
}
