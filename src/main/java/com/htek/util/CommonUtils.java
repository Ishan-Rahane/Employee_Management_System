package com.htek.util;

import com.htek.model.Transaction;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonUtils {

    public static final String DATE_TIME_FORMATE_11 = "dd-MM-yyyy HH:mm:ss";

    public static List<Transaction> txn_hist=new ArrayList<>();

//    UUID uuid = java.util.UUID.randomUUID();

    public static String formatDate(String date) throws ParseException {
        if (date.isEmpty()) {
            return null;
        }
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(parser.parse(date));
    }

    public static Date dateFormat(String date) throws ParseException {
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatter=new SimpleDateFormat(CommonUtils.DATE_TIME_FORMATE_11);
        String newDate = formatter.format(parser.parse(date));
        Date parsedDate=formatter.parse(newDate);
        return parsedDate;
    }

    public static String formatDate(Date date, String dateFormat) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    public static boolean isWithinRange(Date testDate, Date startDate, Date endDate) {
        return !(testDate.before(startDate) || testDate.after(endDate));
    }


}
