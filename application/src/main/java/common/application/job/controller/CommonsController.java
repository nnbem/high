package common.application.job.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;




@Controller
@RequestMapping("/job")
public class CommonsController {
    @GetMapping("/login")
    public String loginForm() {
        String url = "/job/login/login";
        return url;
    }
    
    @GetMapping("/login/findpwd")
    public String findPwd() {
        String url = "/job/login/findpwd";
        return url;
    }
    
    @GetMapping("/mypage/info")
    public String info() {
        String url = "job/mypage/info/info";
        return url;
    }
    
}
