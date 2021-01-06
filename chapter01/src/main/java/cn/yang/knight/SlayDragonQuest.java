package cn.yang.knight;

import cn.yang.knight.aspect.MinstrelNeeding;

import java.io.PrintStream;
import lombok.AllArgsConstructor;

/**
 * Created by yangjing on 2018/1/2
 */
@AllArgsConstructor
public class SlayDragonQuest implements Quest {

    private PrintStream printStream;

    @Override
    @MinstrelNeeding
    public void embark() {
        printStream.println("Embarking on quest to slay the dragon!");
    }

}
