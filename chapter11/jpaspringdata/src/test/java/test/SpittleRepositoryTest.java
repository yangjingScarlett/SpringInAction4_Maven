package test;

import com.jpaspringdata.config.SpringDataConfig;
import com.jpaspringdata.domain.Spitter;
import com.jpaspringdata.domain.Spittle;
import com.jpaspringdata.repository.SpittleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by yangjing on 2018/3/15
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringDataConfig.class)
public class SpittleRepositoryTest {

    @Autowired
    private SpittleRepository spittleRepository;

    @Test
    @Transactional
    public void countTest() {
        log.info("spittle's count : {}", spittleRepository.count());
    }

    @Test
    @Transactional
    public void findByIdTest() {
        Spittle spittle = spittleRepository.findById(13);
        Spitter spitter = spittle.getSpitter();
        log.info("spittle:[id:{}," +
                        "  spitter:[{}, {}, {}, {}, {}, {}, {}], " +
                        "  message:{}," +
                        "  postedTime:{}]",
                spittle.getId(), spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                spitter.getFullname(), spitter.getEmail(), spitter.getUpdateByEmail(), spitter.getStatus(),
                spittle.getMessage(), spittle.getPostTime());
    }

    @Test
    @Transactional
    public void saveTest() {
        log.info("spittle's count : {}", spittleRepository.count());
        Spitter spitter = spittleRepository.findById(13).getSpitter();
        Spittle spittle = new Spittle(null, spitter, "Un Nuevo Spittle from Art", new Date());
        Spittle saved = spittleRepository.save(spittle);
        log.info("spittle's count : {}", spittleRepository.count());
        Spitter savedSpitter = saved.getSpitter();
        log.info("spittle:[id:{}," +
                        "  spitter:[{}, {}, {}, {}, {}, {}, {}], " +
                        "  message:{}," +
                        "  postedTime:{}]",
                saved.getId(), savedSpitter.getId(), savedSpitter.getUsername(), savedSpitter.getPassword(),
                savedSpitter.getFullname(), savedSpitter.getEmail(), savedSpitter.getUpdateByEmail(), savedSpitter.getStatus(),
                saved.getMessage(), saved.getPostTime());
    }

    @Test
    @Transactional
    public void findRecentTest() {
        List<Spittle> spittleList = spittleRepository.findRecent(11);
        for (Spittle spittle : spittleList) {
            Spitter spitter = spittle.getSpitter();
            log.info("spittle:[id:{}," +
                            "  spitter:[{}, {}, {}, {}, {}, {}, {}], " +
                            "  message:{}," +
                            "  postedTime:{}]",
                    spittle.getId(), spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                    spitter.getFullname(), spitter.getEmail(), spitter.getUpdateByEmail(), spitter.getStatus(),
                    spittle.getMessage(), spittle.getPostTime());
        }
    }

    @Test
    @Transactional
    public void findBySpitterIdTest() {
        List<Spittle> spittleList = spittleRepository.findBySpitterId(4);
        for (Spittle spittle : spittleList) {
            Spitter spitter = spittle.getSpitter();
            log.info("spittle:[id:{}," +
                            "  spitter:[{}, {}, {}, {}, {}, {}, {}], " +
                            "  message:{}," +
                            "  postedTime:{}]",
                    spittle.getId(), spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                    spitter.getFullname(), spitter.getEmail(), spitter.getUpdateByEmail(), spitter.getStatus(),
                    spittle.getMessage(), spittle.getPostTime());
        }
    }

    @Test
    @Transactional
    public void deleteTest() {
        log.info("spittle's count : {}", spittleRepository.count());
        spittleRepository.delete(1L);
        log.info("spittle's count : {}", spittleRepository.count());
    }
}
