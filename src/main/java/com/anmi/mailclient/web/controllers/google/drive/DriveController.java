package com.anmi.mailclient.web.controllers.google.drive;

import com.anmi.mailclient.core.service.DriveService;
import com.anmi.mailclient.web.controllers.google.BaseGoogleController;
import com.anmi.mailclient.web.dto.google.drive.DriveDto;
import com.anmi.mailclient.web.dto.google.gmail.GmailDto;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Controller
public class DriveController extends BaseGoogleController {
    @Autowired
    private DriveService driveService;

    @RequestMapping(value = "/drive", method = RequestMethod.GET)
    public String getAll(Model model) {
        List<GmailDto> gmailDtoList = Collections.emptyList();
        DriveDto fileList = driveService.all();
        model.addAttribute("files", fileList.getItems());
        return "g/driveList";
    }

    @RequestMapping(value = "/drive/get", method = RequestMethod.GET,produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void get(HttpServletResponse response, @RequestParam(value = "fileId", required = false)String fileId ) throws IOException {
        IOUtils.copy(driveService.get(fileId),response.getOutputStream());
    }


    @RequestMapping(value = "/drive", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE )
    public String upload(@RequestParam("file") MultipartFile file) {
        file.getName();
        driveService.upload(file);
        return "/drive";
    }



}
