package spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.domain.Spittle;

/**
 * Created by yangjing on 2018/4/9
 */
@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/spittles/create", method = RequestMethod.GET)
    public String createSpittle(Model model) {
        model.addAttribute("spittle", new Spittle());
        return "createSpittle";
    }

}
