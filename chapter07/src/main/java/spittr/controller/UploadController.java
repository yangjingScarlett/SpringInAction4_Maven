package spittr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by yangjing on 2018/1/26
 */
@Controller
public class UploadController {

    public String upload(MultipartFile multipartFile) {
        return "spillter";
    }

}
