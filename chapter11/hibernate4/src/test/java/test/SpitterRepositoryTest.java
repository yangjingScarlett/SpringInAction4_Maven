package test;

import com.hibernate4.config.Hibernate4Config;
import com.hibernate4.domain.Spitter;
import com.hibernate4.repository.SpitterRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by yangjing on 2018/3/14
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Hibernate4Config.class)
public class SpitterRepositoryTest {

    @Autowired
    private SpitterRepository spitterRepository;

    @Test
    @Transactional
    public void countTest() {
        log.info("spitter's count : {}", spitterRepository.count());
    }

    @Test
    @Transactional
    public void saveTest() {
        log.info("spitter's count : {}", spitterRepository.count());
        Spitter spitter = new Spitter(null, "newbee", "letmein", "New Bee",
                "newbee@habuma.com", true);
        Spitter saved = spitterRepository.save(spitter);
        log.info("spitter's count : {}", spitterRepository.count());
        log.info("spitter: [" +
                        " id: {}," +
                        " username: {}," +
                        " password: {}," +
                        " fullName: {}," +
                        " email: {}," +
                        " updateByEmail: {}]",
                saved.getId(), saved.getUsername(), saved.getPassword(),
                saved.getFullName(), saved.getEmail(), saved.isUpdateByEmail());
    }

    @Test
    @Transactional
    public void findByIdTest() {
        Spitter spitter = spitterRepository.findById(3);
        log.info("spitter: [" +
                        " id: {}," +
                        " username: {}," +
                        " password: {}," +
                        " fullName: {}," +
                        " email: {}," +
                        " updateByEmail: {}]",
                spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                spitter.getFullName(), spitter.getEmail(), spitter.isUpdateByEmail());
    }

    @Test
    @Transactional
    public void findByUsernameTest() {
        Spitter spitter = spitterRepository.findByUsername("habuma");
        log.info("spitter: [" +
                        " id: {}," +
                        " username: {}," +
                        " password: {}," +
                        " fullName: {}," +
                        " email: {}," +
                        " updateByEmail: {}]",
                spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                spitter.getFullName(), spitter.getEmail(), spitter.isUpdateByEmail());
    }

    @Test
    @Transactional
    public void findAllTest() {
        List<Spitter> spitterList = spitterRepository.findAll();
        for (Spitter spitter : spitterList) {
            log.info("spitter: [" +
                            " id: {}," +
                            " username: {}," +
                            " password: {}," +
                            " fullName: {}," +
                            " email: {}," +
                            " updateByEmail: {}]",
                    spitter.getId(), spitter.getUsername(), spitter.getPassword(),
                    spitter.getFullName(), spitter.getEmail(), spitter.isUpdateByEmail());
        }
    }

}
