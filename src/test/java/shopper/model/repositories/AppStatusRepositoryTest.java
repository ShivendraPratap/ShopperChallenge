package shopper.model.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import shopper.WebAppInitializer;
import shopper.model.StatusEnum;
import shopper.model.entities.AppStatus;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;


/**
 * This is an integration test of the generated JpaRepository implementation
 *
 * @author ssingh9
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppInitializer.class)
@WebAppConfiguration
public class AppStatusRepositoryTest {

    @Autowired
    private AppStatusRepository appStatusRepository;

    @Test
    public void testFindByShopperId() throws Exception {
        AppStatus appStatus = new AppStatus(1l, new Date(), StatusEnum.applied);
        appStatusRepository.save(appStatus);
        AppStatus appStatusNew = appStatusRepository.findByShopperId(1l);
        assertThat(appStatusNew.getShopperId(), is(equalTo(appStatus.getShopperId())));
        assertThat(appStatusNew.getStatus(), is(equalTo(StatusEnum.applied)));
    }
}