package com.gamma.dexter.console.draft;

import com.gamma.dexter.console.draft.Notifiaction.NotificationDBHandler;
import com.gamma.dexter.console.draft.Notifiaction.NotificationPojo;
import net.sf.json.JSONObject;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Rushil Mahaan on 27-04-2016.
 */
@Controller
public class NotificationController {

    @RequestMapping(value = "settings", method = RequestMethod.GET)
    public String settings() {
        return "settings";
    }


    String pendriveStatusGlobal;

    String ipChangeStatusGlobal;

    double downloadLimitGlobal;
    String downloadLimitStatusGlobal;

    double uploadLimitGlobal;
    String uploadLimitStatusGlobal;

    String processGlobal;
    String processStatusGlobal;

    double upTimeLimitGlobal;
    String upTimeLimitStatusGlobal;

    int ramUsageLimitGlobal;
    String ramUsageLimitStatusGlobal;

    double freeDriveSpaceLimitGlobal;
    String freeDriveSpaceLimitStatusGlobal;


    @RequestMapping("/saveNotificationSettings")
    public
    @ResponseBody
    void saveNotificationSettings(@RequestParam(value = "pendriveStatus") String pendriveStatus,
                                  @RequestParam(value = "ipStatus") String ipStatus,
                                  @RequestParam(value = "ramStatus") String ramStatus, @RequestParam(value = "ramInput") int ramInput,
                                  @RequestParam(value = "processStatus") String processStatus, @RequestParam(value = "processInput") String processInput,
                                  @RequestParam(value = "downloadStatus") String downloadStatus, @RequestParam(value = "dataDownload") String dataDownload, @RequestParam(value = "bytesDownload") String bytesDownload,
                                  @RequestParam(value = "uploadStatus") String uploadStatus, @RequestParam(value = "dataUpload") String dataUpload, @RequestParam(value = "bytesUpload") String bytesUpload,
                                  @RequestParam(value = "freeDriveSpaceStatus") String freeDriveSpaceStatus, @RequestParam(value = "dataFreeDriveSpace") String dataFreeDriveSpace, @RequestParam(value = "bytesFreeDriveSpace") String bytesFreeDriveSpace,
                                  @RequestParam(value = "upTimeStatus") String upTimeStatus, @RequestParam(value = "dataUpTime") String dataUpTime, @RequestParam(value = "bytesUpTime") String bytesUpTime) {

        pendriveStatusGlobal = pendriveStatus;
        ipChangeStatusGlobal = ipStatus;
        ramUsageLimitStatusGlobal = ramStatus;
        ramUsageLimitGlobal = ramInput;
        processStatusGlobal = processStatus;
        processGlobal = processInput;
        downloadLimitStatusGlobal = downloadStatus;
        uploadLimitStatusGlobal = uploadStatus;
        freeDriveSpaceLimitStatusGlobal = freeDriveSpaceStatus;
        upTimeLimitStatusGlobal = upTimeStatus;

        Double dataSize0 = Double.parseDouble(dataDownload);
        if (bytesDownload.equalsIgnoreCase("KB")) {
            downloadLimitGlobal = dataSize0 * 1024.0;
        } else if (bytesDownload.equalsIgnoreCase("MB")) {
            downloadLimitGlobal = dataSize0 * 1024.0 * 1024.0;
        } else if (bytesDownload.equalsIgnoreCase("GB")) {
            downloadLimitGlobal = dataSize0 * 1024.0 * 1024.0 * 1024.0;
        }

        Double dataSize1 = Double.parseDouble(dataUpload);
        if (bytesUpload.equalsIgnoreCase("KB")) {
            uploadLimitGlobal = dataSize1 * 1024.0;
        } else if (bytesUpload.equalsIgnoreCase("MB")) {
            uploadLimitGlobal = dataSize1 * 1024.0 * 1024.0;
        } else if (bytesUpload.equalsIgnoreCase("GB")) {
            uploadLimitGlobal = dataSize1 * 1024.0 * 1024.0 * 1024.0;
        }

        Double dataSize2 = Double.parseDouble(dataFreeDriveSpace);
        if (bytesFreeDriveSpace.equalsIgnoreCase("KB")) {
            freeDriveSpaceLimitGlobal = dataSize2 * 1024.0;
        } else if (bytesFreeDriveSpace.equalsIgnoreCase("MB")) {
            freeDriveSpaceLimitGlobal = dataSize2 * 1024.0 * 1024.0;
        } else if (bytesFreeDriveSpace.equalsIgnoreCase("GB")) {
            freeDriveSpaceLimitGlobal = dataSize2 * 1024.0 * 1024.0 * 1024.0;
        }

        Double dataSize3 = Double.parseDouble(dataUpTime);
        if (bytesUpTime.equalsIgnoreCase("mins")) {
            upTimeLimitGlobal = dataSize3 * 60;
        } else if (bytesUpTime.equalsIgnoreCase("hours")) {
            upTimeLimitGlobal = dataSize3 * 60 * 60;
        } else if (bytesUpTime.equalsIgnoreCase("days")) {
            upTimeLimitGlobal = dataSize3 * 60 * 60 * 24;
        } else if (bytesUpTime.equalsIgnoreCase("weeks")) {
            upTimeLimitGlobal = dataSize3 * 60 * 60 * 24 * 7;
        } else if (bytesUpTime.equalsIgnoreCase("months")) {
            upTimeLimitGlobal = dataSize3 * 60 * 60 * 24 * 7 * 30;
        }


        Subject subject = SecurityUtils.getSubject();

        NotificationPojo rule = new NotificationPojo();

        rule.setPendriveDetectionStatus(convertStatusToint(pendriveStatus));

        rule.setIpChangeDetectionStatus(convertStatusToint(ipStatus));

        rule.setRamDetectionStatus(convertStatusToint(ramStatus));
        rule.setRamLimit(ramInput);

        rule.setDownloadDetectionStatus(convertStatusToint(downloadStatus));
        rule.setDownloadDataLimit(downloadLimitGlobal);

        rule.setUploadDetectionStatus(convertStatusToint(uploadStatus));
        rule.setUploadDataLimit(uploadLimitGlobal);

        rule.setUptimeDetectionStatus(convertStatusToint(upTimeStatus));
        rule.setUptimeLimit(upTimeLimitGlobal);

        rule.setFreeSpaceDetectionStatus(convertStatusToint(freeDriveSpaceStatus));
        rule.setFreeSpaceLimit(freeDriveSpaceLimitGlobal);

        rule.setProcessDetectionStatus(convertStatusToint(processStatus));
        rule.setProcessName(processInput);

        rule.setUserName(subject.getPrincipal().toString());

        new NotificationDBHandler().inputNotificationParametersToDb(rule);
        System.out.println("data send to inputNotificationParametersToDb");

    }

    @RequestMapping("/extractNotificationSettings")
    public
    @ResponseBody
    String extractNotificationSettings(){

        Subject subject = SecurityUtils.getSubject();
        String userName = subject.getPrincipal().toString();
        JSONObject notificationSettings = new NotificationDBHandler().extractNotificationSettingFromUserName(userName);
        System.out.println("data received to inputNotificationParametersToDb");
        return notificationSettings.toString();
    }


    public int convertStatusToint(String status){
        if(status.equalsIgnoreCase("true")){
            return 1;
        }
        else {
            return 0;
            }
        }


}
