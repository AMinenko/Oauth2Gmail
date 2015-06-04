package com.anmi.mailclient.web.controllers.google.drive;

import com.anmi.mailclient.core.service.DriveService;
import com.anmi.mailclient.web.controllers.google.BaseGoogleController;
import com.anmi.mailclient.web.dto.google.drive.DriveDto;
import com.anmi.mailclient.web.dto.google.gmail.GmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;
import java.util.List;

@Controller
public class DriveController extends BaseGoogleController {
    @Autowired
    private DriveService driveService;

    @RequestMapping(value = "/drive", method = RequestMethod.GET)
    public String getAll(Model model, @RequestParam(value = "fileId", required = false)String fileId ) {

        if (fileId != null) {
            model.addAttribute("file", driveService.get(fileId));
            return "g/drive";
        }
        List<GmailDto> gmailDtoList = Collections.emptyList();
        DriveDto fileList = driveService.all();
        model.addAttribute("files", fileList.getItems());
        return "g/driveList";
    }

    @RequestMapping(value = "/drive", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public String upload(@RequestParam("file") MultipartFile file) {
        file.getName();
        driveService.upload(file);
        return "/drive";
    }
}
