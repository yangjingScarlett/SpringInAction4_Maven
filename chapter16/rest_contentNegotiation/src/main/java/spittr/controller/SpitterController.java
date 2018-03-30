package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.domain.Spitter;
import spittr.repository.SpitterRepository;

import javax.validation.Valid;

/**
 * Created by yangjing on 2018/3/27
 */
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    @Autowired
    private SpitterRepository spitterRepository;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model) {
        model.addAttribute("spitter", new Spitter());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegister(@Valid Spitter spitter, Errors errors) {
        if (errors.hasErrors()) {
            return "register";
        } else {
            Spitter oldSpitter = spitterRepository.findByUsername(spitter.getUsername());
            if (oldSpitter == null) {
                spitterRepository.save(spitter);
                return "redirect:/spitter/" + spitter.getUsername();
            } else {
                return "register";
            }
        }
    }

    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    public String showSpitter(@PathVariable String username, Model model) {
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute("spitter", spitter);
        return "profile";
    }

}
