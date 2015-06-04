package com.anmi.mailclient.core.service;

import com.anmi.mailclient.web.dto.google.drive.DriveDto;
import com.anmi.mailclient.web.dto.google.gmail.GmailDto;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface DriveService {
    Object get(String fileId);

    DriveDto all();

    void upload(MultipartFile file);
}
