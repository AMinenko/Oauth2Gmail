package com.anmi.mailclient.core.service;

import com.anmi.mailclient.web.dto.google.gmail.GmailDto;

import java.util.List;

public interface GmailService {
    List<GmailDto> all();
    GmailDto get (String id);
}
