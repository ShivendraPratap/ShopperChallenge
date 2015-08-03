package shopper.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by ssingh9 on 8/2/15.
 */
public class DateRange {
    DateTime start;

    DateTime end;

    public DateRange(DateTime start, DateTime end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        return fmt.print(start) + "-" + fmt.print(end);
    }
}
