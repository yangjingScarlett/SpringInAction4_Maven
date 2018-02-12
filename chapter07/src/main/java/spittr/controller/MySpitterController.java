package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.model.Spitter;
import spittr.repository.SpitterRepository;

import javax.servlet.http.Part;
import javax.validation.Valid;
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

//    @RequestMapping(value = "/register", method = POST)
//    public String processRegistration(
//            //此时是以.tmp文件的形式保存在upload文件夹中
//            @RequestPart(value = "profilePicture", required = false) byte[] profilePicture,
//            @Valid Spitter spitter,
//            Errors errors) throws IOException {
//        if (errors.hasErrors()) {
//            return "registerForm";
//        }
//
//        spitterRepository.save(spitter);
//        return "redirect:/spitter/" + spitter.getUsername();
//    }

//    @RequestMapping(value = "/register", method = POST)
//    public String processRegistration(@Valid SpitterForm spitterForm,
//                                      Errors errors) throws IllegalStateException, IOException {
//        if (errors.hasErrors()) {
//            return "registerForm";
//        }
//        Spitter spitter = spitterForm.toSpitter();
//        spitterRepository.save(spitter);
//        MultipartFile prifilePicture = spitterForm.getProfilePicture();
//        prifilePicture.transferTo(new File("e:/upload/" + spitter.getUsername() + ".jpg"));
//        return "redirect:/spitter/" + spitter.getUsername();
//    }

    @RequestMapping(value = "/register", method = POST)
    public String processRegistration(
            //Part和MultipartFile的作用类似，用write(String fileName)方法替代了transferTo(File file)方法
            @RequestPart(value = "profilePicture", required = false) Part profilePicture,
            @Valid Spitter spitter,
            RedirectAttributes model,
            Errors errors) throws IllegalStateException, IOException {
        if (errors.hasErrors()) {
            return "registerForm";
        }
        spitterRepository.save(spitter);
        profilePicture.write("e:/upload/" + profilePicture.getSubmittedFileName());
        model.addFlashAttribute("spitter", spitter);
        return "redirect:/spitter/" + spitter.getUsername();
    }

    @RequestMapping(value = "/{username}", method = GET)
    public String showSpitterProfile(Model model, @PathVariable("username") String username) {
        if (!model.containsAttribute("spitter")) {
            model.addAttribute("spitter", spitterRepository.findByUsername(username));
        }
        return "profile";
    }

}
