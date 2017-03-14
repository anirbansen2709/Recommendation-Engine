package com.gamma.dexter.console.draft.Notifiaction;

/**
 * Created by lenovo on 4/21/2016.
 */
public class NotificationPojo {

    private int pendriveDetectionStatus;

    private int ramDetectionStatus;
    private int ramLimit;

    private int freeSpaceDetectionStatus;
    private double freeSpaceLimit;

    private int uploadDetectionStatus;
    private double uploadDataLimit;

    private int downloadDetectionStatus;
    private double downloadDataLimit;

    private int uptimeDetectionStatus;
    private double uptimeLimit;

    private int ipChangeDetectionStatus;

    private int processDetectionStatus;
    private String processName;

    private String userId;
    private String email;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    private String userName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public int isDownloadDetectionEnabled() {
        return downloadDetectionStatus;
    }

    public void setDownloadDetectionStatus(int downloadDetectionStatus) {
        this.downloadDetectionStatus = downloadDetectionStatus;
    }

    public double getDownloadDataLimit() {
        return downloadDataLimit;
    }

    public void setDownloadDataLimit(double downloadDataLimit) {
        this.downloadDataLimit = downloadDataLimit;
    }

    public int isFreeSpaceDetectionEnabled() {
        return freeSpaceDetectionStatus;
    }

    public void setFreeSpaceDetectionStatus(int freeSpaceDetectionStatus) {
        this.freeSpaceDetectionStatus = freeSpaceDetectionStatus;
    }

    public double getFreeSpaceLimit() {
        return freeSpaceLimit;
    }

    public void setFreeSpaceLimit(double freeSpaceLimit) {
        this.freeSpaceLimit = freeSpaceLimit;
    }

    public int isIpChangeDetectionEnabled() {
        return ipChangeDetectionStatus;
    }

    public void setIpChangeDetectionStatus(int ipChangeDetectionStatus) {
        this.ipChangeDetectionStatus = ipChangeDetectionStatus;
    }

    public int isPendriveDetectionEnabled() {
        return pendriveDetectionStatus;
    }

    public void setPendriveDetectionStatus(int pendriveDetectionStatus) {
        this.pendriveDetectionStatus = pendriveDetectionStatus;
    }

    public int isProcessDetectionEnabled() {
        return processDetectionStatus;
    }

    public void setProcessDetectionStatus(int processDetectionStatus) {
        this.processDetectionStatus = processDetectionStatus;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public int isRamDetectionEnabled() {
        return ramDetectionStatus;
    }

    public void setRamDetectionStatus(int ramDetectionStatus) {
        this.ramDetectionStatus = ramDetectionStatus;
    }

    public int getRamLimit() {
        return ramLimit;
    }

    public void setRamLimit(int ramLimit) {
        this.ramLimit = ramLimit;
    }


    public int isUploadDetectionEnabled() {
        return uploadDetectionStatus;
    }

    public void setUploadDetectionStatus(int uploadDetectionStatus) {
        this.uploadDetectionStatus = uploadDetectionStatus;
    }

    public double getUploadDataLimit() {
        return uploadDataLimit;
    }

    public void setUploadDataLimit(double uploadDataLimit) {
        this.uploadDataLimit = uploadDataLimit;
    }

    public int isUptimeDetectionEnabled() {
        return uptimeDetectionStatus;
    }

    public void setUptimeDetectionStatus(int uptimeDetectionStatus) {
        this.uptimeDetectionStatus = uptimeDetectionStatus;
    }

    public double getUptimeLimit() {
        return uptimeLimit;
    }

    public void setUptimeLimit(double uptimeLimit) {
        this.uptimeLimit = uptimeLimit;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
