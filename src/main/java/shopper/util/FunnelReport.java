package shopper.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ssingh9 on 8/2/15.
 */
public class FunnelReport {

    @JsonProperty
    Map<DateRange, Map<String, Integer>> report_map;

    public FunnelReport() {
        report_map = new HashMap<DateRange, Map<String, Integer>>();
    }

    public void addReport(DateRange dr, Map<String, Integer> dp_map) {
        report_map.put(dr, dp_map);
    }

    public Map<DateRange, Map<String, Integer>> getReportMap() {
        return report_map;
    }
}
