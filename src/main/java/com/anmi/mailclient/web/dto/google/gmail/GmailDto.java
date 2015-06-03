package com.anmi.mailclient.web.dto.google.gmail;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class GmailDto {
    String id;
    String threadId;
    String snippet;
    String raw;

    public String getRaw() {
        return raw;
    }

    public void setRaw(String raw) {
        this.raw = raw;
    }


    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public static class GmailList {
        private GmailDto[] messages;
        String  nextPageToken;
        String  resultSizeEstimate;


        public GmailDto[] getMessages() {
            return messages;
        }

        public void setMessages(GmailDto[] messages) {
            this.messages = messages;
        }


        public String getNextPageToken() {
            return nextPageToken;
        }

        public void setNextPageToken(String nextPageToken) {
            this.nextPageToken = nextPageToken;
        }

        public String getResultSizeEstimate() {
            return resultSizeEstimate;
        }

        public void setResultSizeEstimate(String resultSizeEstimate) {
            this.resultSizeEstimate = resultSizeEstimate;
        }
    }


}
