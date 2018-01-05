package cn.yang.knight;

import cn.yang.knight.aspect.MinstrelNeeding;

import java.io.PrintStream;

/**
 * Created by yangjing on 2018/1/2
 */
public class SlayDragonQuest implements Quest {

    private PrintStream printStream;

    public SlayDragonQuest(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    @MinstrelNeeding
    public void embark() {
        printStream.println("Embarking on quest to slay the dragon!");
    }

}
