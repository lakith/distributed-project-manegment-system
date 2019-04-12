package com.itemsSharing.service.impl;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


@Service
public class AmazonClient {

    @Autowired
    private AmazonS3 s3client;


    String bucketName = "hospitel-paid-bucket";

    SimpleDateFormat sdf =new SimpleDateFormat("yyyyMMdd-hhmmsss");
    String date = sdf.format(new Date());



    @Async
    public String uploadFile(MultipartFile multipartFile, boolean enablePublicReadAccess) {
        String fileName = multipartFile.getOriginalFilename();

        try {
            //creating the file in the server (temporarily)
            File file = new File(fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();
            String uploadName = date+fileName;
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, uploadName, file);

            if (enablePublicReadAccess) {
                putObjectRequest.withCannedAcl(CannedAccessControlList.PublicRead);
            }

            s3client.putObject(putObjectRequest);
            //removing the file created in the server
            file.delete();
            return uploadName;
        } catch (IOException | AmazonServiceException ex) {
            return "error [" + ex.getMessage() + "] occurred while uploading [" + fileName + "] ";
        }
    }


    public String getUrlFromFileName(String name) {
        String path = s3client.getUrl(bucketName,name).toString();
        return path;
    }


}