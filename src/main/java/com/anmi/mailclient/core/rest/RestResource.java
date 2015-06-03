package com.anmi.mailclient.core.rest;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public enum RestResource {


    GMAIL_GET_MESSAGES("https://www.googleapis.com/gmail/v1/users/%s/messages"),
    GMAIL_GET_MESSAGE("https://www.googleapis.com/gmail/v1/users/%s/messages/%s?format=raw");

    private String path;

    RestResource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getPath(Object... args) {
        List<String> formatedArgs = new ArrayList<>();
        if (args != null) {
            String arg = null;
            for (Object obj : args) {
                if (obj instanceof String) {
                    arg = (String) obj;
                }
                formatedArgs.add(arg);
            }
        }
        return String.format(path, formatedArgs.toArray());
    }


}
