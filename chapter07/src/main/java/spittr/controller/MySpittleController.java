package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
public class MySpittleController {

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
        model.addAttribute("spittle", spittleRepository.findOne(id));
        return "spittle";
    }

    //与HTML中的标签中的name对应的值会自动传递到后台，这里的spittleForm就保存了name为spittleForm的form的值
    @RequestMapping(value = "/spittleForm", method = RequestMethod.POST)
    public String saveSpittle(SpittleForm spittleForm) {
        spittleRepository.save(new Spittle(7l, spittleForm.getMessage(), new Date(),
                spittleForm.getLatitude(), spittleForm.getLongitude()));
        return "redirect:/spittles";
    }

}
