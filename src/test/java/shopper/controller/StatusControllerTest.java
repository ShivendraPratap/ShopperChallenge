package shopper.controller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shopper.model.StatusEnum;
import shopper.model.entities.AppStatus;
import shopper.model.repositories.AppStatusRepository;
import shopper.util.DateTimeUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class StatusControllerTest {

    @Test
    public void testUpdateStatus() throws Exception {
        final AppStatusRepository appStatusRepository = mock(AppStatusRepository.class);

        Date date = new Date();
        final AppStatus appStatus = new AppStatus(1l, date, StatusEnum.applied);
        stub(appStatusRepository.findOne(1l)).toReturn(appStatus);
        stub(appStatusRepository.findByShopperId(1l)).toReturn(appStatus);

        final StatusController controller = new StatusController(appStatusRepository);
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        date = new Date();
        final AppStatus appStatusnew = new AppStatus(1l, date, StatusEnum.hired);

        // Empty content
        mockMvc
                .perform(
                        post("/update_status")
                                .param("shopper_id", "1")
                                .param("new_status", "hired"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("hired")));
    }


    @Test
    public void testGetFunnel() throws Exception {
        final AppStatusRepository appStatusRepository = mock(AppStatusRepository.class);
        final StatusController controller = new StatusController(appStatusRepository);
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();


        DateTime dateTime = new DateTime();
        Date today = dateTime.toDate();
        DateTime prev;
        DateTime next;
        prev = DateTimeUtil.calcPrevMonday(dateTime.toLocalDate());
        next = prev.plusDays(7).minusSeconds(1);

        // Add first with status applied
        List<AppStatus> appStatusList = new ArrayList<AppStatus>();
        final AppStatus appStatus1 = new AppStatus(1l, today, StatusEnum.applied);
        appStatusList.add(appStatus1);

        stub(appStatusRepository.findByDateBetweenAndStatus(prev.toDate(), next.toDate(), StatusEnum.applied)).toReturn(appStatusList);

        // Add second with status hired
        appStatusList = new ArrayList<AppStatus>();
        final AppStatus appStatus2 = new AppStatus(2l, dateTime.plusDays(1).toDate(), StatusEnum.hired);
        appStatusList.add(appStatus2);

        stub(appStatusRepository.findByDateBetweenAndStatus(prev.toDate(), next.toDate(), StatusEnum.hired)).toReturn(appStatusList);

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");

        // Check one application
        mockMvc
                .perform(
                        get("/funnels.json")
                                .param("start_date", fmt.print(prev))
                                .param("end_date", fmt.print(next)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("\"" + StatusEnum.applied + "\":1")))
                .andExpect(content().string(containsString("\"" + StatusEnum.hired + "\":1")));

    }
}