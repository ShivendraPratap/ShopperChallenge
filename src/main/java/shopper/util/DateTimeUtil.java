package shopper.util;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

/**
 * Created by ssingh9 on 8/2/15.
 */

public class DateTimeUtil {

    public static DateTime calcPrevMonday(LocalDate d) {
        return d.withDayOfWeek(DateTimeConstants.MONDAY).toDateTimeAtStartOfDay();
    }

    public static DateTime calcNextMonday(LocalDate d) {
        if (d.getDayOfWeek() >= DateTimeConstants.MONDAY) {
            d = d.plusWeeks(1);
        }
        return d.withDayOfWeek(DateTimeConstants.MONDAY).toDateTimeAtStartOfDay();
    }

    public static DateTime calcNextSunday(LocalDate d) {
        if (d.getDayOfWeek() >= DateTimeConstants.SUNDAY) {
            d = d.plusWeeks(1);
        }
        return d.withDayOfWeek(DateTimeConstants.SUNDAY).toDateTimeAtStartOfDay();
    }
}
