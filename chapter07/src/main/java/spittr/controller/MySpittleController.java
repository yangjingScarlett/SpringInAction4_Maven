package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spittr.exception.DuplicateSpittleException;
import spittr.exception.SpittleNotFoundException;
import spittr.model.Spittle;
import spittr.model.SpittleForm;
import spittr.repository.SpittleRepository;

import java.util.Date;

/**
 * Created by yangjing on 2018/1/17
 */
@Controller
@RequestMapping("/spittles")
@PropertySource(value = "classpath:prop.properties", encoding = "utf-8")
public class MySpittleController{

    @Autowired
    private SpittleRepository spittleRepository;

    private static final String MAX = "2048";
    private static final String COUNT = "2";

    @RequestMapping
    public String findSpittles(Model model, @RequestParam(defaultValue = MAX) long max,
                               @RequestParam(defaultValue = COUNT) int count) {
        model.addAttribute("spittleList", spittleRepository.findSpittles(max, count));
        return "spittles";
    }

    @RequestMapping("/{id}")
    public String findOne(Model model, @PathVariable("id") long id) {
        Spittle spittle = spittleRepository.findOne(id);
        if (spittle == null){
            throw new SpittleNotFoundException();
        }
        model.addAttribute("spittle", spittle);
        return "spittle";
    }

    //与HTML中的标签中的name对应的值会自动传递到后台，这里的spittleForm就保存了name为spittleForm的form的值
    @RequestMapping(value = "/spittleForm", method = RequestMethod.POST)
    public String saveSpittle(SpittleForm spittleForm) {
        try {
            spittleRepository.save(new Spittle(null, spittleForm.getMessage(), new Date(),
                    spittleForm.getLatitude(), spittleForm.getLongitude()));
            return "redirect:/spittles";
        }catch (DuplicateSpittleException e){
            return "error/duplicate";
        }
    }

    //可以处理控制器中所有方法抛出的DuplicateSpittleException异常
    @ExceptionHandler(DuplicateSpittleException.class)
    public String handleNotFound() {
        return "error/duplicate";
    }

}
