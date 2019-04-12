package com.itemsSharing.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AWSConfig {


    String accessKey = "AKIAJGHJD6MBVIXIG6VA";

    String secret = "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";

    String region = "US_EAST_1";

//    String accessKey = "AKIAIEYXDYB53RO6ZQXA";
//
//    String secret = "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";
//
//    String region = "AP_SOUTHEAST_1";



    @Bean
    public AmazonS3 s3client() {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey,
                secret);

        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.valueOf(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        return s3Client;
    }


}