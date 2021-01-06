package d_mixedConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by yangjing on 2018/1/3
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SoundSystemConfig.class)
public class ImportXmlToJavaConfig {

    @Autowired
    private MediaPlayer mediaPlayer;

    @Test
    public void playing() {
        mediaPlayer.play();
    }

}
