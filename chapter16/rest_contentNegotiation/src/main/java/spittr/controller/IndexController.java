package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.domain.Spitter;
import spittr.repository.SpitterRepository;

/**
 * Created by yangjing on 2018/3/27
 */
@Controller
public class IndexController {

    @Autowired
    private SpitterRepository spitterRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String processLogin(String username, String password) {
        Spitter spitter = spitterRepository.findByUsername(username);
        if (spitter.getPassword().equals(password)) {
            return "redirect:/spittles";
        } else {
            return "login";
        }
    }

}
