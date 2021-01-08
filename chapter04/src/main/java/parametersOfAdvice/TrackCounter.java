package parametersOfAdvice;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjing on 2018/1/8
 */
@Aspect
public class TrackCounter {

    private Map<Integer, Integer> countTrackMap = new HashMap<>();

    @Pointcut("execution(* *.play(int)) && args(trackNum)")
    public void trackPlayed(int trackNum) {
    }

    @Before(value = "trackPlayed(trackNum)", argNames = "trackNum")
    public void countTrack(int trackNum) {
        int currentCount = getPlaytCount(trackNum) + 1;
        countTrackMap.put(trackNum, currentCount);
        System.out.println("Play " + currentCount + " times!");
    }

    public int getPlaytCount(int trackNum) {
        return countTrackMap.getOrDefault(trackNum, 0);
    }

}
