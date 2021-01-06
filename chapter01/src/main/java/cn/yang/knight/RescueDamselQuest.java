package cn.yang.knight;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by yangjing on 2018/1/2
 */
public class RescueDamselQuest implements Quest {

    private static final Logger log = LoggerFactory.getLogger(RescueDamselQuest.class);

    @Override
    public void embark() {
        log.info("Embarking on a quest to rescue the damsel.");
    }

}
