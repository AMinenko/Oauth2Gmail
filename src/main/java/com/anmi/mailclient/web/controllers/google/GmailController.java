package com.anmi.mailclient.web.controllers.google;

import com.anmi.mailclient.core.service.GmailService;
import com.anmi.mailclient.web.dto.google.gmail.GmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

;import java.util.Collections;
import java.util.List;

@Controller
public class GmailController extends BaseGoogleController {

    @Autowired
    private GmailService gmailService;

    @RequestMapping(value = "/gmail", method = RequestMethod.GET)
    public String getAll(Model model, @RequestParam (value = "mailId", required = false)String mailId ) {

        if (mailId != null) {
            model.addAttribute("mail", gmailService.get(mailId));
            return "g/gmail";
        }
        List<GmailDto> gmailDtoList = Collections.emptyList();
        gmailDtoList = gmailService.all();
        model.addAttribute("mails", gmailDtoList);
        return "g/gmails";
    }

}
