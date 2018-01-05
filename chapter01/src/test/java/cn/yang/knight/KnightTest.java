package cn.yang.knight;

import cn.yang.knight.config.KnightConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

/**
 * Created by yangjing on 2018/1/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = KnightConfig.class)//加载上下文，否则无法找到bean
public class KnightTest {

    @Autowired
    private Knight knight;

    @Test
    public void knightShouldEmbarkOnQuest() {
        Quest quest = mock(Quest.class);
        BraveKnight braveKnight = new BraveKnight(quest);
        braveKnight.embarkOnQuest();
        verify(quest, times(1)).embark();
    }

    @Test
    public void testAspect() {
        knight.embarkOnQuest();
    }

}
