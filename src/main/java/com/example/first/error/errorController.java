package com.example.first.error;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class errorController implements ErrorController {
     @RequestMapping("/error")
        public String error(HttpServletRequest request, Model model) {
         Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
         if (status != null) {
             int statusCode = Integer.parseInt(status.toString());
             if (statusCode == 404) {
                 model.addAttribute("error", "존재하지 않는 페이지입니다.");
                 return "error/error";
             } else if (statusCode == 500) {
                 model.addAttribute("error", "서버 에러");
                 return "error/error";
             } else if (statusCode == 403) {
                 model.addAttribute("error", "권한이 없습니다.");
                 return "error/error";
             } else if (statusCode == 401) {
                 model.addAttribute("error", "로그인이 필요합니다.");
                 return "error/error";
             } else if (statusCode == 400) {
                 model.addAttribute("error", "잘못된 요청입니다.");
                 return "error/error";
             }
             else {
                 model.addAttribute("error", "알 수 없는 에러");
                 return "error/error";
             }
         }
            return "error/error";
     }
}
