package shopper.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import shopper.model.StatusEnum;
import shopper.model.entities.AppStatus;
import shopper.model.entities.Shopper;
import shopper.model.repositories.AppStatusRepository;
import shopper.model.repositories.ShopperRepository;

import javax.validation.Valid;
import java.util.Date;

/**
 * Created by ssingh9 on 8/2/15.
 */
@Controller
public class ShopperController {

    @Autowired
    ShopperRepository shopperRepository;

    @Autowired
    AppStatusRepository appStatusRepository;


    Logger logger = LoggerFactory.getLogger(ShopperController.class);
    
    public ShopperController() {
    }

    public ShopperController(ShopperRepository shopperRepository, AppStatusRepository appStatusRepository) {
        this.shopperRepository = shopperRepository;
        this.appStatusRepository = appStatusRepository;
    }

    /**
     * * Landing page
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView land() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("landing");
        return mav;
    }

    /**
     * * Model/View to display form for a new shopper
     * @return
     */
    @RequestMapping(value = "/shopper", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView showForm() {
        ModelAndView mav = new ModelAndView("shopper", "shopper", new Shopper());
        return mav;
    }

    /**
     * * API to add new shopper and confirm the details
     * @return
     */
    @RequestMapping(value = "/addshopper", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ModelAndView addShopper(@Valid @ModelAttribute("shopper") Shopper shopper,
                                   BindingResult result, ModelMap model) {
        ModelAndView mav = new ModelAndView();
        if (result.hasErrors()) {
            logger.error("error:" + result.toString());
            mav.setViewName("error");
            mav.addObject("errormsg", "Invalid input error!!");
            return mav;
        }
        try {
            logger.debug("Got data for :{}", shopper.getEmail());
            Shopper savedInstance = shopperRepository.save(shopper);
            if (savedInstance == null) throw new RuntimeException("Unable to save shopper!");
            AppStatus appStatus = new AppStatus(savedInstance.getId(), new Date(), StatusEnum.applied);
            appStatusRepository.save(appStatus);
            mav.addObject("firstname", shopper.getFirstName());
            mav.addObject("lastname", shopper.getLastName());
            mav.addObject("email", shopper.getEmail());
            mav.addObject("phone",shopper.getPhone());
            mav.setViewName("confirm");
        } catch (Exception e) {
            e.printStackTrace();
            mav.setViewName("error");
            mav.addObject("errormsg", e.getMessage());
        }
        return mav;
    }


    /**
     * * Model/View to show errors
     * @return
     */
    @RequestMapping(value = "/error", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("error");
        mav.addObject("errormsg", "Invalid input error!!");
        return mav;
    }

}
