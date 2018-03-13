package test;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import spitter.config.DataSourceConfig;
import spitter.config.SpringConfig;
import spitter.domain.Spitter;
import spitter.domain.Spittle;
import spitter.repository.SpittleRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by yangjing on 2018/3/13
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, DataSourceConfig.class})
public class SpittleRepositoryTest {

    @Autowired
    private SpittleRepository spittleRepository;

    @Test
    public void countTest(){
        log.info("spittle's count : {}",spittleRepository.count());
    }

    @Test
    @Transactional
    public void saveTest(){
        log.info("spittle's count : {}",spittleRepository.count());
        Spitter spitter = spittleRepository.findById(13).getSpitter();
        Spittle spittle = new Spittle(null,spitter,"Un Nuevo Spittle from Art",new Date());
        Spittle saved = spittleRepository.save(spittle);
        log.info("spittle's count : {}",spittleRepository.count());
        Spitter savedSpitter = saved.getSpitter();
        log.info("spittle:[id:{}," +
                        "  spitter:[{}, {}, {}, {}, {}, {}], " +
                        "  message:{}," +
                        "  postedTime:{}]",
                saved.getId(),savedSpitter.getId(),savedSpitter.getUsername(),savedSpitter.getPassword(),
                savedSpitter.getFullName(),savedSpitter.getEmail(),savedSpitter.isUpdateByEmail(),
                saved.getMessage(),saved.getPostedTime());
    }

    @Test
    @Transactional
    public void findAllTest(){
        log.info("spittle's count : {}",spittleRepository.count());
        List<Spittle> spittleList = spittleRepository.findAll();
        for(Spittle spittle : spittleList){
            Spitter spitter = spittle.getSpitter();
            log.info("spittle:[id:{}," +
                            "  spitter:[{}, {}, {}, {}, {}, {}]," +
                            "  message:{}," +
                            "  postedTime:{}]",
                    spittle.getId(),spitter.getId(),spitter.getUsername(),spitter.getPassword(),
                    spitter.getFullName(),spitter.getEmail(),spitter.isUpdateByEmail(),
                    spittle.getMessage(),spittle.getPostedTime());
        }
    }

    @Test
    @Transactional
    public void deleteTest(){
        log.info("spittle's count : {}",spittleRepository.count());
        spittleRepository.delete(1);
        log.info("spittle's count : {}",spittleRepository.count());
    }

}
