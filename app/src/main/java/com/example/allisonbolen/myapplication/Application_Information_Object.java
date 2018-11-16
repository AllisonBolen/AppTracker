package com.example.allisonbolen.myapplication;

import java.util.Date;
import org.joda.time.DateTime;

public class Application_Information_Object {

    private String companyName, companyDesc, jobTitle, jobDesc, contactInfo;
    private DateTime lastClick, appDate;


    public Application_Information_Object(String companyName, String companyDesc,
                                          String jobTitle, DateTime lastClick, String jobDesc,
                                          DateTime appDate, String contactInfo){
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

    public DateTime getLastClick() {
        return lastClick;
    }

    public void setLastClick(DateTime lastClick) {
        this.lastClick = lastClick;
    }

    public DateTime getAppDate() {
        return appDate;
    }

    public void setAppDate(DateTime appDate) {
        this.appDate = appDate;
    }
}
