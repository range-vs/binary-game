package entity.comparators;

import entity.NameEducation;

import java.util.Comparator;

public class NameEducationComparator implements Comparator<NameEducation> {

    @Override
    public int compare(NameEducation o1, NameEducation o2) {
        return o1.getId() - o2.getId();
    }

}
