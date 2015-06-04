package com.anmi.mailclient.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseTokenDto {
    private CompanyType companyType;

    public enum CompanyType {
        TYPE_GOOGLE("Google"),
        TYPE_AZURE("Azure");

        private String value;

        private CompanyType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }
}
