package calgaryFlamesTracker.Comparators;

import calgaryFlamesTracker.Players.Skater;

import java.util.Comparator;

public class AssistComparator implements Comparator<Skater> {

    /**
     * Sorts by assists, from greatest to least. If same, then sort by jersey number.
     * @param o1 A skater
     * @param o2 Other Skater
     * @return Integer based on comparable value. -1 if less than, 0 if same, 1 if greater than
     */
    @Override
    public int compare(Skater o1, Skater o2) {
        if (o2.getAssists() > o1.getAssists()) {
            return 1;
        }
        if (o2.getAssists() < o1.getAssists()) {
            return -1;
        }
        if (o1.getJerseyNumber() > o2.getJerseyNumber()) {
            return 1;
        }
        if (o1.getJerseyNumber() < o2.getJerseyNumber()) {
            return -1;
        }

        return 0;
    }
}
