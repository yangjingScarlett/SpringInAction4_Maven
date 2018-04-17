package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import spittr.domain.Spitter;
import spittr.repository.SpitterRepository;

/**
 * Created by yangjing on 2018/4/9
 */
@RestController
@RequestMapping("/spitter")
public class SpitterController {

    @Autowired
    private SpitterRepository spitterRepository;

    //produces属性表明这个方法只会处理accept头部信息包含"application/json"的请求
    @RequestMapping(value = "/{username}", produces = "application/json", method = RequestMethod.GET)
    public Spitter findByUsername(@PathVariable String username) {
        return spitterRepository.findByUsername(username);
    }

}
