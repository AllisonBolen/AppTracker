package com.example.allisonbolen.myapplication;

import java.util.Date;

public class Application_Information_Object {

    private String companyName, companyDesc, jobTitle, jobDesc, contactInfo;
    private Date lastClick, appDate;


    public Application_Information_Object(String companyName, String companyDesc,
                                          String jobTitle, Date lastClick, String jobDesc,
                                          Date appDate, String contactInfo){
        this.companyName = companyName;
        this.companyDesc = companyDesc;
        this.jobTitle = jobTitle;
        this.jobDesc = jobDesc;
        this.contactInfo = contactInfo;
        this.lastClick = lastClick;
        this.appDate = appDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyDesc() {
        return companyDesc;
    }

    public void setCompanyDesc(String companyDesc) {
        this.companyDesc = companyDesc;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDesc() {
        return jobDesc;
    }

    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public Date getLastClick() {
        return lastClick;
    }

    public void setLastClick(Date lastClick) {
        this.lastClick = lastClick;
    }

    public Date getAppDate() {
        return appDate;
    }

    public void setAppDate(Date appDate) {
        this.appDate = appDate;
    }
}
