package com.sj.dalimi.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@NoArgsConstructor
public class S3Service {
    private AmazonS3 s3Client;
    public static final String CLOUD_FRONT_DOMAIN_NAME = "danzabn941bf7.cloudfront.net";

    @Value("${cloud.aws.credentials.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.credentials.secretKey}")
    private String secretKey;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.region.static}")
    private String region;

    @PostConstruct
    public void setS3Client(){
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(this.region)
                .build();
    }

    public String upload(String currentFilePath , MultipartFile file)throws IOException{

        SimpleDateFormat date = new SimpleDateFormat("yyyymmddHHmmss");
        String filename = file.getOriginalFilename() + "-" + date.format(new Date());

        if("".equals(currentFilePath) == false && currentFilePath != null){
            boolean isExistObject = s3Client.doesObjectExist(bucket,currentFilePath);

            if(isExistObject == true){
                s3Client.deleteObject(bucket,currentFilePath);
            }
        }
        s3Client.putObject(new PutObjectRequest(bucket, filename, file.getInputStream(),null)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        return filename;

    }

}
