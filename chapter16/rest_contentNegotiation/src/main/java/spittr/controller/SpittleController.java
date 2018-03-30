package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import spittr.domain.Spittle;
import spittr.repository.SpittleCustomerRepository;
import spittr.repository.SpittleRepository;

import java.util.List;

/**
 * Created by yangjing on 2018/3/27
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    @Autowired
    private SpittleRepository spittleRepository;
    @Autowired
    private SpittleCustomerRepository spittleCustomerRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String spittles(@RequestParam(value = "max", defaultValue = MAX_LONG_AS_STRING) long max,
                           @RequestParam(value = "count", defaultValue = "20") int count,
                           Model model) {
        List<Spittle> spittleList = spittleCustomerRepository.findSpittles(max, count);
        model.addAttribute("spittleList", spittleList);
        return "spittles";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String spittle(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("spittle", spittleRepository.findById(id));
        return "spittle";
    }

}
