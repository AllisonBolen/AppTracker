package com.example.allisonbolen.myapplication.dummy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.joda.time.DateTime;
import java.util.Map;
import com.example.allisonbolen.myapplication.Application_Information_Object;
/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Application_Information_Object> ITEMS = new ArrayList<Application_Information_Object>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<DateTime, Application_Information_Object> ITEM_MAP = new HashMap<DateTime, Application_Information_Object>();


    private static void addItem(Application_Information_Object item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getAppDate(), item);
    }

    private static Application_Information_Object createDummyItem(int position) {
        return new Application_Information_Object("Nike", "SHOE PLACE", "ENGINEER", DateTime.now(), "Do the android", DateTime.now(), "Mr Rogers", position);
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

    public static class Application_Information_Object {

        private String companyName, companyDesc, jobTitle, jobDesc, contactInfo;
        private DateTime lastClick, appDate;
        private int position;

        public Application_Information_Object(String companyName, String companyDesc,
                                              String jobTitle, DateTime lastClick, String jobDesc,
                                              DateTime appDate, String contactInfo, int position){
            this.companyName = companyName;
            this.companyDesc = companyDesc;
            this.jobTitle = jobTitle;
            this.jobDesc = jobDesc;
            this.contactInfo = contactInfo;
            this.lastClick = lastClick;
            this.appDate = appDate;
            this.position = position;
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

}
