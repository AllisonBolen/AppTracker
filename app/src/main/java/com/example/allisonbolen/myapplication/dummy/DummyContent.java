package com.example.allisonbolen.myapplication.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;

import java.util.Map;
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent implements java.io.Serializable{

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Application_Information_Object> ITEMS = new ArrayList<Application_Information_Object>();


    public static void addItem(Application_Information_Object item) {
        ITEMS.add(item);
    }


    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */

    public static class Application_Information_Object implements java.io.Serializable{

        private String companyName;
        private String companyDesc;
        private String jobTitle;
        private String jobDesc;
        private String contactInfo;
        private String lastClick;
        private String appDate;
        private String imageUrl;
        public String _key;
        //private DateTime lastClick, appDate;

        private int position;
        public Application_Information_Object(){
            this.companyName = "";
            this.companyDesc = "";
            this.jobTitle = "";
            this.jobDesc = "";
            this.contactInfo = "";
            this.lastClick = new DateTime().toString(DateTimeFormat.fullDate());
            this.appDate = new DateTime().toString(DateTimeFormat.fullDate());
            this.position = 0;
            this.imageUrl = "";
        };

        public Application_Information_Object(String companyName, String companyDesc,
                                              String jobTitle, String lastClick, String jobDesc,
                                              String appDate, String contactInfo, int position){
            this.companyName = companyName;
            this.companyDesc = companyDesc;
            this.jobTitle = jobTitle;
            this.jobDesc = jobDesc;
            this.contactInfo = contactInfo;
            this.lastClick = lastClick;
            this.appDate = appDate;
            this.position = position;
            this.imageUrl = "make request here to get string";
        }

        public String getImageUrl() { return imageUrl; }

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

        public String getLastClick() {
            return lastClick;
        }

        public void setLastClick(String lastClick) {
            this.lastClick = lastClick;
        }

        public String getAppDate() {
            return appDate;
        }

        public void setAppDate(String appDate) {
            this.appDate = appDate;
        }
    }

}
