package com.atu.diplomas.ui.home;

import java.util.Map;

public class ConferenceData {
    String nameConf;
    String dateConf;
    String locationConf;
    String formaConf;
    String languageConf;
    String organizatorConf;
    String textConf;

    public ConferenceData(String nameConf, String dateConf, String locationConf,
                          String formaConf, String languageConf, String organizatorConf, String textConf){
        this.nameConf = nameConf;
        this.dateConf = dateConf;
        this.locationConf = locationConf;
        this.languageConf = languageConf;
        this.formaConf = formaConf;
        this.organizatorConf = organizatorConf;
        this.textConf = textConf;
    }
    public ConferenceData(Map<String, Object> data){}

    public String getTextConf() {
        return textConf;
    }

    public void setTextConf(String textConf) {
        this.textConf = textConf;
    }

    public void setDateConf(String dateConf) {
        this.dateConf = dateConf;
    }

    public void setFormaConf(String formaConf) {
        this.formaConf = formaConf;
    }

    public void setLanguageConf(String languageConf) {
        this.languageConf = languageConf;
    }

    public void setLocationConf(String locationConf) {
        this.locationConf = locationConf;
    }

    public void setNameConf(String nameConf) {
        this.nameConf = nameConf;
    }

    public void setOrganizatorConf(String organizatorConf) {
        this.organizatorConf = organizatorConf;
    }

    public String getDateConf() {
        return dateConf;
    }

    public String getFormaConf() {
        return formaConf;
    }

    public String getLanguageConf() {
        return languageConf;
    }

    public String getLocationConf() {
        return locationConf;
    }

    public String getNameConf() {
        return nameConf;
    }

    public String getOrganizatorConf() {
        return organizatorConf;
    }
}
