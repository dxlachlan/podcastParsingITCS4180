package com.example.danielcampbell.group21_hw5;

import java.util.Comparator;

/**
 * Created by danielcampbell on 10/29/17.
 */

public class KeywordComparator implements Comparator<Podcast> {

    private final String keyword;

    public KeywordComparator(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public int compare(Podcast o1, Podcast o2) {
        String title1 = o1.getPc_title();
        String title2 = o2.getPc_title();
        boolean o1Has = title1.startsWith(keyword);
        boolean o2Has = title2.startsWith(keyword);
        if (o1Has && !o2Has) return -1;
        else if (o2Has && !o1Has) return 1;
        else if (o1Has && o2Has) return 0;
        else

            return title1.length() - title2.length();
    }
}
