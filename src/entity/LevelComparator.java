package entity;

import utils.Pair;

import java.util.Comparator;

public class LevelComparator implements Comparator<Level> {

    @Override
    public int compare(Level o1, Level o2) {
        return o1.getLevel() - o2.getLevel();
    }
}
