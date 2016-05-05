package de.klinger.adw.domain;

import java.util.Date;

public enum AgeGroup {

    AK9, AK10;

    public static AgeGroup getAgeGroupByBirthday(Date birthday) {
        Date now = new Date();
        long msYear = 1000L * 60 * 60 * 24 * 365;
        int msDiff = Math.round((now.getTime() - birthday.getTime()) / msYear);

        if (msDiff >= 10) {
            return AK10;
        }
        return AK9;

    }
}
