package spittr.controller;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import spittr.model.Spitter;
import spittr.model.SpitterForm;
import spittr.repository.SpitterRepository;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by yangjing on 2018/1/17
 */
@Controller
@RequestMapping("/spitter")
public class MySpitterController {

    @Autowired
    private SpitterRepository spitterRepository;

    @RequestMapping(value = "/register", method = GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("spitter", new Spitter());
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(@Valid @ModelAttribute SpitterForm spitterForm, Errors errors,MultipartFile file) throws IllegalStateException, IOException {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        Spitter spitter = spitterForm.toSpitter();
        spitterRepository.save(spitter);
        //使用FileUtils.writeByteArrayToFile快速写文件到磁盘
        FileUtils.writeByteArrayToFile(new File("E:/upload/" + file.getOriginalFilename()), file.getBytes());
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(Model model, @PathVariable("username") String username) {
        model.addAttribute("spitter", spitterRepository.findByUsername(username));
        return "profile";
    }

}
