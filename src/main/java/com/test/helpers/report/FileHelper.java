package com.test.helpers.report;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.util.UUID;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;
import org.testng.Reporter;
import org.testng.internal.Utils;

public class FileHelper {

    private static Logger logger = LoggingManager.getLogger(FileHelper.class);
    private static volatile FileHelper instance;


    public FileHelper() {
    }

    public static FileHelper getInstance(){
        if(instance==null)
            instance=new FileHelper();
        return instance;
    }


    public static String createRequestJsonFile(String jsonMsg, String fileName) {
        String requestFilePath = null;

        try {
            String resultFolder = ReportHelper.getResultFolderpath();
            resultFolder = resultFolder + "/Requests";
            File resultFileFolder = new File(resultFolder);
            resultFileFolder.delete();
            resultFileFolder.mkdir();
            UUID randNo = UUID.randomUUID();
            requestFilePath = resultFolder + "/Requests_" + fileName + "_" + randNo + ".json";
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(requestFilePath, true));
            resultFile.append(jsonMsg);
            resultFile.close();
            requestFilePath = "." + requestFilePath.split(ReportHelper.getResultFolderpath())[1];
            Reporter.log("<br><a href='" + requestFilePath + "'>Request_" + fileName + "</a><br>");
        } catch (Exception var7) {
            logger.log(Level.ERROR, "Error while creating JSON file - " + var7);
        }

        return requestFilePath;
    }

    public static String createResponseJsonFile(String jsonMsg, String fileName) {
        String responseFilePath = null;

        try {
            String resultFolder = ReportHelper.getResultFolderpath();
            resultFolder = resultFolder + "/Responses";
            File resultFileFolder = new File(resultFolder);
            resultFileFolder.delete();
            resultFileFolder.mkdir();
            UUID randNo = UUID.randomUUID();
            responseFilePath = resultFolder + "/Responses_" + fileName + "_" + randNo + ".json";
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(responseFilePath, true));
            resultFile.append(jsonMsg);
            resultFile.close();
            responseFilePath = "." + responseFilePath.split(ReportHelper.getResultFolderpath())[1];
            Reporter.log("<br><a href='" + responseFilePath + "'>Response_" + fileName + "</a><br>");
        } catch (Exception var7) {
            logger.log(Level.ERROR, "Error while creating reponse JSON file - " + var7);
        }
        return responseFilePath;
    }

    public String createCurlFile(String curl){
        String curlFilePath="";
        try{
            String resultFolder = ReportHelper.getResultFolderpath();
            resultFolder = resultFolder + "/Curls";
            File resultFileFolder = new File(resultFolder);
            resultFileFolder.delete();
            resultFileFolder.mkdir();
            UUID randNo = UUID.randomUUID();
            curlFilePath=resultFolder + "/Curls_" + randNo + ".html";
            String curlLine = "";
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(curlFilePath, true));
            resultFile.append("<html></head><body><title>CURL</title>");
            Scanner scanner = new Scanner(curl);

            while(scanner.hasNext()) {
                curlLine = scanner.nextLine();
                resultFile.append(curlLine + "<br>");
            }

            resultFile.close();
            curlFilePath = "." + curlFilePath.split(ReportHelper.getResultFolderpath())[1];
        }
        catch(Exception e){
            logger.error("Exception not reported - " + e);
        }
        return curlFilePath;
    }

    public String createExceptionFile(String methodName, Throwable exception) {
        String exceptionFilePath = "";

        try {
            UUID randNo = UUID.randomUUID();
            exceptionFilePath = ReportHelper.getResultFolderpath() + "/" + methodName + "_Exception_" + randNo + ".html";
            String stackTraceLine = "";
            String str = Utils.longStackTrace(exception, true);
            BufferedWriter resultFile = new BufferedWriter(new FileWriter(exceptionFilePath, true));
            resultFile.append("<html></head><body><title>Exception Stack Trace</title>");
            Scanner scanner = new Scanner(str);

            while(scanner.hasNext()) {
                stackTraceLine = scanner.nextLine();
                resultFile.append(stackTraceLine + "<br>");
            }

            resultFile.close();
        } catch (Exception var9) {
            logger.error("Exception not reported - " + var9);
        }

        return exceptionFilePath;
    }



}
