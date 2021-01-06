package cn.yang.knight;

import lombok.AllArgsConstructor;

/**
 * Created by yangjing on 2018/1/2
 */
@AllArgsConstructor
public class BraveKnight implements Knight {

    private Quest quest;

    @Override
    public void embarkOnQuest() {
        quest.embark();
    }

}
