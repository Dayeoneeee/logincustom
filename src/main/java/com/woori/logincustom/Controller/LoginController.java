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

    @GetMapping("/result")  //로그인 성공한 사용자용 페이지
    public String result() {
        return "result";
    }

    @GetMapping("/user")    //일반사용자용 페이지
    public String user() {
        return "user";
    }

    @GetMapping("/admin")   //관리자용 페이지
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
        //섹션(서버의 저장공간) : 컴퓨터id+사용자정보로 저장
        //섹션을 저장해야 로그아웃 처리가 된다.
        //자동로그아웃 처리 추가(5분, 10분 동안 작업이 없으면 로그아웃)
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
