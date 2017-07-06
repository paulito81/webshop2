package no.phasfjo.util;


import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by phasf on 27.01.2017.
 */
public class ItemServerConnection {

    // ======================================
    // =             Attributes             =
    // ======================================

    @URL
    private String resourceURL;
    @NotNull
    @URL(protocol = "http", host = "www.paulswarehouse.com")
    private String itemURL;
    @URL(protocol = "ftp", port = 21)
    private String ftpServerURL;
    @URL(groups = Error.class)
    private Date lastConnectionDate;

    // ======================================
    // =            Constructors            =
    // ======================================

    public ItemServerConnection() {
    }

    public ItemServerConnection(String resourceURL, String itemURL, String ftpServerURL) {
        this.resourceURL = resourceURL;
        this.itemURL = itemURL;
        this.ftpServerURL = ftpServerURL;
    }

    // ======================================
    // =            GET AND SET            =
    // ======================================


    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public String getItemURL() {
        return itemURL;
    }

    public void setItemURL(String itemURL) {
        this.itemURL = itemURL;
    }

    public String getFtpServerURL() {
        return ftpServerURL;
    }

    public void setFtpServerURL(String ftpServerURL) {
        this.ftpServerURL = ftpServerURL;
    }

    public Date getLastConnectionDate() {
        return lastConnectionDate;
    }

    public void setLastConnectionDate(Date lastConnectionDate) {
        this.lastConnectionDate = lastConnectionDate;
    }

}
