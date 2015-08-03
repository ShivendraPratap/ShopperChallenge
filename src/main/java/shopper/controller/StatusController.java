package shopper.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import shopper.model.StatusEnum;
import shopper.model.entities.AppStatus;
import shopper.model.repositories.AppStatusRepository;
import shopper.util.DateRange;
import shopper.util.DateTimeUtil;
import shopper.util.FunnelReport;

import java.util.*;

/**
 * Created by ssingh9 on 8/2/15.
 */
@RestController
public class StatusController {

    @Autowired
    AppStatusRepository appStatusRepository;

    Logger logger = LoggerFactory.getLogger(StatusController.class);

    public StatusController() {
    }

    public StatusController(AppStatusRepository appStatusRepository) {
        this.appStatusRepository = appStatusRepository;
    }

    /**
     * * API to update the status of an existing application
     * @param sid : shopper id
     * @param status : new status
     * @return
     */
    @RequestMapping(value = "/update_status", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public AppStatus updateStatus(@RequestParam(value = "shopper_id") Long sid, @RequestParam(value = "new_status") StatusEnum status) {
        AppStatus appStatus;
        try {
            appStatus = appStatusRepository.findByShopperId(sid);
        } catch (Exception e) {
            throw new RuntimeException("Unable to find the shopper with given shopper id " + e.getMessage());
        }
        if (appStatus == null) {
            throw new RuntimeException("Unable to find the shopper with given shopper id");
        }
        DateTime dateTime = new DateTime();
        Date now = dateTime.toDate();
        appStatus.setDate(now);
        appStatus.setStatus(status);
        try {
            appStatusRepository.save(appStatus);
        } catch (Exception e) {
            throw new RuntimeException("Unable to change the status of the given shopper id " + e.getMessage());
        }
        return appStatus;
    }

    /**
     * * API to genrate the weekly funnel report
     * @param start_date
     * @param end_date
     * @return
     */
    @RequestMapping(value = "/funnels.json", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<DateRange, Map<String, Integer>> getFunnel(@RequestParam(value = "start_date") String start_date, @RequestParam(value = "end_date") String end_date) {
        FunnelReport funnelReport = new FunnelReport();

        DateTime dateTime = new DateTime();
        Date today = dateTime.toDate();
        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

        DateTime start = fmt.parseDateTime(start_date);
        DateTime end = fmt.parseDateTime(end_date);

        start = DateTimeUtil.calcPrevMonday(start.toLocalDate());
//        end = DateTimeUtil.calcNextSunday(end.toLocalDate()).plusDays(1).minusSeconds(1);

        DateTime mid = start.plusDays(7).minusSeconds(1);
        logger.debug("--------START REPORT GENERATION -----------------------");
        logger.debug("START :{} , END: {} ", start_date, end_date);
        logger.debug("-------------------------------");
        Date range_start, range_end;


        do {
            range_end = mid.toDate();
            range_start = start.toDate();
            logger.debug("Finding statuses between {} and {}",start, mid);
            logger.debug("-------------------------------");

            DateRange dr = new DateRange(start, mid);
            Map<String, Integer> status_map = new HashMap<String, Integer>();
            for (StatusEnum e : StatusEnum.values()) {
                int count = 0;

                for (AppStatus appStatus : appStatusRepository.findByDateBetweenAndStatus(range_start, range_end, e)) {
                    count++;
                }
                if (count != 0) {
                    status_map.put(e.toString(), count);
                }
            }

            funnelReport.addReport(dr, status_map);
            start = mid.plusSeconds(1);
            mid = start.plusDays(7).minusSeconds(1);
        } while (start.isBefore(end));

        return funnelReport.getReportMap();
    }

    /**
     * * API to init some sample values in the db
     * @return
     */
    @RequestMapping(value = "/init", method = RequestMethod.GET)
    public List<AppStatus> Initialize() {
        DateTime dateTime = new DateTime();
        Date today = dateTime.toDate();
        List<AppStatus> appStatusList = new ArrayList<AppStatus>();

        appStatusRepository.save(new AppStatus(1l, today, StatusEnum.applied));
        dateTime = dateTime.plusDays(7);
        Date tomorrow = dateTime.toDate();
        appStatusRepository.save(new AppStatus(2l, tomorrow, StatusEnum.applied));
        dateTime = dateTime.plusDays(8);
        tomorrow = dateTime.toDate();
        appStatusRepository.save(new AppStatus(3l, tomorrow, StatusEnum.hired));
        dateTime = dateTime.plusDays(1);
        tomorrow = dateTime.toDate();
        appStatusRepository.save(new AppStatus(4l, tomorrow, StatusEnum.onboarding_completed));
        dateTime = dateTime.plusDays(4);
        tomorrow = dateTime.toDate();
        appStatusRepository.save(new AppStatus(5l, tomorrow, StatusEnum.rejected));
        // fetch all customers
        logger.debug("Shoppers found with findAll():");
        logger.debug("-------------------------------");
        for (AppStatus appStatus : appStatusRepository.findAll()) {
            logger.debug(appStatus.toString());
            appStatusList.add(appStatus);
        }
        return appStatusList;
    }

    /**
     * * API to return all application statuses
     * @return
     */
    @RequestMapping(value = "/status", method = RequestMethod.GET)
    public List<AppStatus> getAllStatus() {
        List<AppStatus> appStatusList = new ArrayList<AppStatus>();
        logger.debug("Shoppers found with findAll():");
        logger.debug("-------------------------------");
        for (AppStatus appStatus : appStatusRepository.findAll()) {
            logger.debug(appStatus.toString());
            appStatusList.add(appStatus);
        }

        return appStatusList;
    }

}
