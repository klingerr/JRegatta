package de.klinger.adw.domain;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum AgeGroup {

    AK9, AK10;

//    public static AgeGroup getAgeGroupByBirthday(Date birthday) {
//        Date now = new Date();
//        long msYear = 1000L * 60 * 60 * 24 * 365;
//        int msDiff = Math.round((now.getTime() - birthday.getTime()) / msYear);
//
//        if (msDiff >= 10) {
//            return AK10;
//        }
//        return AK9;
//
//    }
    
    public static AgeGroup getAgeGroupByBirthday(Date birthday) {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date dueDate = null;
        try {
            dueDate = df.parse("01.01.2007");
        } catch (ParseException ex) {
            Logger.getLogger(AgeGroup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (birthday.before(dueDate)) {
            return AK10;
        }
        return AK9;
    }
}
