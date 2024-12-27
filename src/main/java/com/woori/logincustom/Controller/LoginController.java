package com.woori.logincustom.Controller;

import com.woori.logincustom.DTO.LoginDTO;
import com.woori.logincustom.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/")    //시작페이지(모든 권한)
    public String index() {
        return "index";
    }

    @GetMapping("/result")
    public String result() {
        return "result";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/login")   //사용자 로그인 페이지로 이동
    public String login() {
        return "login";
    }

    //사용자 로그아웃을 작업할 때 반드시 섹션을 제거하는 부분을 추가
    @GetMapping("/logout")  //로그아웃 처리
    private String logout(HttpSession session) {
        session.invalidate();  //세션에서 로그인 정보를 제거

        return "redirect:/login";
    }

    //회원가입(가입폼->가입처리)
    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerProc(LoginDTO loginDTO) {
        loginService.saveUser(loginDTO);;

        return "redirect:/login";
    }
}
