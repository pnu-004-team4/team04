package com.team04.musiccloud.controller;

import com.team04.musiccloud.db.AccountCustomRepository;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SettingController {

  private static final Logger logger = Logger.getGlobal();


  @RequestMapping(value = "/setcheck", method = RequestMethod.POST)
  public ModelAndView userSetCheck() {
    return new ModelAndView("/Setting/usersetcheck");
  }


  @RequestMapping(value = "/setcheck", method = RequestMethod.GET)
  public String confirmChangedSetting(
      @RequestParam String email,
      @RequestParam String password,
      @RequestParam String name,
      @RequestParam String isTranscode
  ) {
    AccountCustomRepository repository = new AccountCustomRepository();

    logger.info("confirm data ==> " + email + " " + password + " " + name + " " + isTranscode);

    repository.updateAccount(email, password, name, "true".equals(isTranscode));

    return "redirect:/";
  }
}
