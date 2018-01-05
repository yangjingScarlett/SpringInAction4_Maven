package cn.yang.knight;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by yangjing on 2018/1/2
 */
@Getter
@Setter
public class DamselRescuingKnight implements Knight {

    private RescueDamselQuest quest;

    public DamselRescuingKnight() {
        this.quest = new RescueDamselQuest();
    }//紧耦合

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }

}
