package spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yangjing on 2018/1/17
 */
@Controller
public class MyHomeController {

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("view", "This is a view!");
        return "home";
    }

}
