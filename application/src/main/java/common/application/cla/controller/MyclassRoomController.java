package common.application.cla.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/class")
public class MyclassRoomController {

    @GetMapping("myclassroom")
    public String myclassroom(){
        String url = "cla/myclassroom/list";
        return url;
    }
    
}
