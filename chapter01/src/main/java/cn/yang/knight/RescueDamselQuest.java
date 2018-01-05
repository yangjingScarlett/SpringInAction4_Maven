package cn.yang.knight;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by yangjing on 2018/1/2
 */
@Slf4j
public class RescueDamselQuest implements Quest {

    @Override
    public void embark() {
        log.info("Embarking on a quest to rescue the damsel.");
    }

}
