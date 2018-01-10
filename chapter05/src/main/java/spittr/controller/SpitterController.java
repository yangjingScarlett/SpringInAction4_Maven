package spittr.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import spittr.model.Spitter;
import spittr.repository.SpitterRepository;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by yangjing on 2018/1/10
 */
@Setter
@Controller
@RequestMapping("/spitter")
public class SpitterController {

    @Autowired
    private SpitterRepository spitterRepository;

    @RequestMapping
    public String findSpitters(Model model) {
        List<Spitter> spitterList = spitterRepository.findSpitters();
        model.addAttribute("spitterList", spitterList);
        return "spitters";
    }

    @RequestMapping(value = "/register",method = RequestMethod.GET)
    public String registerForm() {
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@Valid Spitter spitter, Errors errors) {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spitterRepository.save(spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping("/{username}")
    public String showSpitter(@PathVariable String username,Model model){
        Spitter spitter = spitterRepository.findByUsername(username);
        model.addAttribute("spitter",spitter);
        return "spitter";
    }


}
