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
import spitter.repository.SpitterRepository;

import java.util.List;

/**
 * Created by yangjing on 2018/3/13
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class, DataSourceConfig.class})
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    public void countTest(){
        log.info("spitter's count:{}",spitterRepository.count());
    }

    @Test
    @Transactional
    public void findAllTest(){
        List<Spitter> spitterList = spitterRepository.findAll();
        log.info("spitter's size : {}",spitterList.size());
        for(Spitter spitter : spitterList){
            log.info("spitter:[{},{},{},{},{},{}]",
                    spitter.getId(),spitter.getUsername(),spitter.getPassword(),
                    spitter.getFullName(),spitter.getEmail(),spitter.isUpdateByEmail());
        }
    }

    @Test
    @Transactional
    public void findByUsername(){
        Spitter spitter = spitterRepository.findByUsername("habuma");
        log.info("spitter:[{},{},{},{},{},{}]",
                spitter.getId(),spitter.getUsername(),spitter.getPassword(),
                spitter.getFullName(),spitter.getEmail(),spitter.isUpdateByEmail());
    }

    @Test
    @Transactional
    public void save_newSpitter(){
        Spitter spitter = new Spitter(null, "newbee", "letmein", "New Bee",
                "newbee@habuma.com", true);
        spitterRepository.save(spitter);
        log.info("spitter's count : {}",spitterRepository.count());
        Spitter saved = spitterRepository.findById(5);
        log.info("spitter:[{},{},{},{},{},{}]",
                saved.getId(),saved.getUsername(),saved.getPassword(),
                saved.getFullName(),saved.getEmail(),saved.isUpdateByEmail());
    }

    @Test
    @Transactional
    public void save_existingSpitter(){
        Spitter oldSpitter = spitterRepository.findById(4);
        log.info("oldSpitter:[{},{},{},{},{},{}]",
                oldSpitter.getId(),oldSpitter.getUsername(),oldSpitter.getPassword(),
                oldSpitter.getFullName(),oldSpitter.getEmail(),oldSpitter.isUpdateByEmail());
        Spitter newSpitter = new Spitter(4L, "arthur", "letmein", "Arthur Names",
                "arthur@habuma.com", false);
        spitterRepository.save(newSpitter);
        Spitter spitter = spitterRepository.findById(4);
        log.info("newSpitter:[{},{},{},{},{},{}]",
                spitter.getId(),spitter.getUsername(),spitter.getPassword(),
                spitter.getFullName(),spitter.getEmail(),spitter.isUpdateByEmail());
    }

}
