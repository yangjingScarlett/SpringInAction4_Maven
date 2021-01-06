package cn.yang.knight;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangjing on 2018/1/2
 */
@Getter
@Setter
public class DamselRescuingKnight implements Knight {

    // it will be automatically set by Spring IOC, because we set up it in the /spring/knight.xml context
    private RescueDamselQuest quest;

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }

}
