package shopper.model.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import shopper.WebAppInitializer;
import shopper.model.entities.Shopper;

import java.util.List;

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
public class ShopperRepositoryTest {

    @Autowired
    private ShopperRepository shopperRepository;

    @Test
    public void testFindByFirstName() {
        Shopper shopper = new Shopper("Shivendra", "Singh","shivendra.mnnit@gmail.com");
        shopperRepository.save(shopper);
        List<Shopper> shopperList = shopperRepository.findByFirstName("Shivendra");

        assertThat(shopperList.size(), is(equalTo(1)));
        assertThat(shopperList.get(0).getFirstName(), is(equalTo("Shivendra")));
        assertThat(shopperList.get(0).getLastName(), is(equalTo("Singh")));
    }

}