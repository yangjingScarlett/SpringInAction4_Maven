package spittr.controller;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.model.Spittle;
import spittr.repository.SpittleRepository;

import java.util.List;

/**
 * Created by yangjing on 2018/1/9
 */
@Controller
@RequestMapping("/spittles")
@Setter
public class SpittleController {

    @Autowired
    private SpittleRepository spittleRepository;

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    // 与方法spittles(Model model)等效。逻辑视图的名称将会根据请求路径推断得出。因为这个方法处理针对“/spittles”的GET请求，
    // 因此视图的名称将会是spittles（去掉开头的斜线）
    // http://localhost:8080/spittles?max=10&count=10
    @RequestMapping
    public List<Spittle> spittles(@RequestParam(defaultValue = MAX_LONG_AS_STRING) long max,
                                  @RequestParam(defaultValue = "2") int count) {
        return spittleRepository.findSpittles(max, count);
    }

//    @RequestMapping
//    public String spittles(Model model) {
//        model.addAttribute("spittleList", spittleRepository.findSpittles(Long.MAX_VALUE, 20));
//        return "spittles";
//    }

    @RequestMapping("/{id}")
    public String spittle(@PathVariable long id, Model model) {
        model.addAttribute("spittle", spittleRepository.findOne(id));
        return "spittle";
    }

}
