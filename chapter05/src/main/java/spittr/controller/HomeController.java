package spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yangjing on 2018/1/9
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String home(){
        return "home";
    }
}
