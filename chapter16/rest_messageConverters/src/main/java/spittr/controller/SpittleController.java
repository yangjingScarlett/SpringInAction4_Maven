package spittr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import spittr.domain.Spittle;
import spittr.error.Error;
import spittr.error.SpittleNotFoundException;
import spittr.repository.SpittleRepository;

/**
 * Created by yangjing on 2018/4/9
 * 在这里介绍rest提供除了资源之外的内容：发送错误信息到客户端
 */
@RestController
@RequestMapping("/spittles")
public class SpittleController {

    @Autowired
    private SpittleRepository spittleRepository;

    //1.使用了ResponseEntity，就可以不用使用@ResponseBody,此时可以返回错误代码，但是没有响应体
//    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
//    public ResponseEntity<Spittle> findByIdWithResponseEntity(@PathVariable Long id) {
//        Spittle spittle = spittleRepository.findById(id);
//        HttpStatus status = spittle != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
//        return new ResponseEntity<Spittle>(spittle, status);
//    }

    //2.使用了ResponseEntity结合Error，返回响应体和错误代码
//    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
//    public ResponseEntity<?> findByIdWithError(@PathVariable Long id) {
//        Spittle spittle = spittleRepository.findById(id);
//        if (spittle == null) {
//            Error error = new Error(4, "Spittle [" + id + "] not found");
//            return new ResponseEntity<Error>(error, HttpStatus.NOT_FOUND);
//        } else {
//            return new ResponseEntity<Spittle>(spittle, HttpStatus.OK);
//        }
//    }

    //如果在控制器的任意处理方法中抛出SpittleNotFoundException异常，就会调用spittleNotFound()方法来处理异常。
    @ExceptionHandler(SpittleNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error spittleNotFound(SpittleNotFoundException e) {
        long id = e.getSpittleId();
        return new Error(4, "Spittle [" + id + "] not found");
    }

    @RequestMapping(value = "/{id}", produces = "application/json", method = RequestMethod.GET)
    public Spittle findByIdWithExceptionHandler(@PathVariable Long id) {
        Spittle spittle = spittleRepository.findById(id);
        if (spittle == null) {
            throw new SpittleNotFoundException(id);
        }
        return spittle;
    }

    // UriComponentsBuilder会预先配置已知的信息如host、端口以及Servlet内容。
    // 它会从处理器方法所对应的请求中获取这些基础信息。
    // 基于这些信息，代码会通过设置路径的方式构建UriComponents其余的部分。
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Spittle createSpittle(String message,
                                 UriComponentsBuilder ucb) {
        Spittle spittle = new Spittle(message);
        Spittle newSpittle = spittleRepository.save(spittle);
//        HttpHeaders httpHeaders = new HttpHeaders();
//        URI locationUri = ucb.path("/spittles/")
//                .path(String.valueOf(newSpittle.getId()))
//                .build()
//                .toUri();
//        httpHeaders.setLocation(locationUri);
//        return new ResponseEntity<Spittle>(newSpittle, httpHeaders, HttpStatus.CREATED);
        return newSpittle;
    }

}
