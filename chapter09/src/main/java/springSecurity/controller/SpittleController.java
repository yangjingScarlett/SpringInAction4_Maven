package springSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springSecurity.model.Spittle;
import springSecurity.model.SpittleForm;
import springSecurity.repository.SpittleRepositorty;

import java.util.Date;
import java.util.List;

/**
 * Created by yangjing on 2018/2/12
 */
@Controller
@RequestMapping("/spittles")
public class SpittleController {

    private static final String MAX_LONG_AS_STRING = "9223372036854775807";

    @Autowired
    private SpittleRepositorty spittleRepositorty;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model, @RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
                          @RequestParam(value="count", defaultValue="20") int count) {
        List<Spittle> spittleList = spittleRepositorty.findAll(max, count);
        model.addAttribute("spittleList", spittleList);
        return "spittles";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String fingById(Model model,@PathVariable(name = "id") Long id){
        Spittle spittle = spittleRepositorty.findById(id);
        model.addAttribute("spittle",spittle);
        return "spittle";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String saveSpittle(SpittleForm spittleForm){
        spittleRepositorty.save(new Spittle(null,spittleForm.getMessage(),new Date(),
                spittleForm.getLatitude(),spittleForm.getLongitude()));
        return "redirect:/spittles";
    }

}
