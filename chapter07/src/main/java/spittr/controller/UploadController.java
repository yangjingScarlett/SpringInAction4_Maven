package spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangjing on 2018/1/26
 */
@Controller
public class UploadController {

    @RequestMapping(value = "/fileUpload", method = RequestMethod.GET)
    public String upload() {
        return "uploadForm";
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public String processUpload(@RequestPart("file") MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return "redirect:/";
    }

}
