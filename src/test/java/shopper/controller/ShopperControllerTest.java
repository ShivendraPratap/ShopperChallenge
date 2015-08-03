package shopper.controller;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import shopper.model.entities.Shopper;
import shopper.model.repositories.AppStatusRepository;
import shopper.model.repositories.ShopperRepository;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ShopperControllerTest {


    @Test
    public void testAddShopper() throws Exception {
        final ShopperRepository shopperRepository = mock(ShopperRepository.class);
        final AppStatusRepository appStatusRepository = mock(AppStatusRepository.class);

        Shopper shopper = new Shopper("Shivendra", "Singh","shivendra.mnnit@gmail.com");
        stub(shopperRepository.findOne(0l)).toReturn(shopper);
        stub(shopperRepository.save(shopper)).toReturn(shopper);

        final ShopperController controller = new ShopperController(shopperRepository, appStatusRepository);
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        //Valid request

        mockMvc
                .perform(
                        post("/addshopper")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                .sessionAttr("shopper", shopper)
                                .param("firstName", "Shivendra")
                                .param("lastName", "Singh")
                                .param("email", "shivendra.mnnit@gmail.com")
                                .param("phone", "123456")
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("confirm")
                );
    }

    @Test
    public void testLand() throws Exception {
        final ShopperRepository shopperRepository = mock(ShopperRepository.class);
        final AppStatusRepository appStatusRepository = mock(AppStatusRepository.class);

        final ShopperController controller = new ShopperController(shopperRepository, appStatusRepository);
        final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("landing"));

    }
}