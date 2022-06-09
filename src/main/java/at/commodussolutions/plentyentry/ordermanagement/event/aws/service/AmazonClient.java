package at.commodussolutions.plentyentry.ordermanagement.event.aws.service;

import at.commodussolutions.plentyentry.ordermanagement.event.aws.dto.AWSEventImagesUploadDTO;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AmazonClient {

    private AmazonS3 s3Client;

    @Value("${amazonProperties.endpointUrl}")
    private String endpointUrl;
    @Value("${amazonProperties.bucketName}")
    private String bucketName;
    @Value("${amazonProperties.accessKey}")
    private String accessKey;
    @Value("${amazonProperties.secretKey}")
    private String secretKey;
    private String fromEmail;
    @Value("${amazonProperties.smtpUsername}")
    private String smtpUsername;
    @Value("${amazonProperties.smtpPassword}")
    private String smtpPasword;

    @Value("${amazonProperties.mailHost}")
    private String smtpHost;

    @PostConstruct
    private void initializeAmazon() {
        AWSCredentials credentials = new BasicAWSCredentials(this.accessKey, this.secretKey);
        this.s3Client = new AmazonS3Client(credentials);
    }

    public List<String> uploadFiles(List<MultipartFile> multipartFiles, AWSEventImagesUploadDTO awsEventImagesUploadDTO) {
        int i = 0;
        List<String> fileUrls = new ArrayList<>();
        String path = "/" + awsEventImagesUploadDTO.getUsername() + "/" + awsEventImagesUploadDTO.getEventName() + "/";

        for (MultipartFile multipartFile : multipartFiles) {
            String fileUrl = "";
            try {
                File file = convertMultiPartToFile(multipartFile, i);
                String fileName = generateFileName(multipartFile, i);
                fileUrl = endpointUrl + path + fileName;

                String tempPath = awsEventImagesUploadDTO.getUsername() + "/" + awsEventImagesUploadDTO.getEventName() + "/";
                s3Client.putObject(new PutObjectRequest(bucketName, tempPath + fileName, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                file.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
            fileUrls.add(fileUrl);

        }
        return fileUrls;
    }

    public List<String> listFiles(String username, String eventName) {

        ListObjectsRequest listObjectsRequest =
                new ListObjectsRequest()
                        .withBucketName(bucketName);

        List<String> keys = new ArrayList<>();

        ObjectListing objects = s3Client.listObjects(listObjectsRequest);

        while (true) {
            List<S3ObjectSummary> objectSummaries = objects.getObjectSummaries();
            if (objectSummaries.size() < 1) {
                break;
            }

            for (S3ObjectSummary item : objectSummaries) {
                if (!item.getKey().endsWith("/") && item.getKey().contains(username) && item.getKey().contains(eventName))
                    keys.add(endpointUrl + "/" + item.getKey());
            }

            objects = s3Client.listNextBatchOfObjects(objects);
        }

        return keys;
    }

    public ByteArrayOutputStream  downloadFile(String keyName, AWSEventImagesUploadDTO awsEventImagesUploadDTO) {
        String path = awsEventImagesUploadDTO.getUsername()+"/"+awsEventImagesUploadDTO.getEventName()+"/";
        try {
            S3Object s3object = s3Client.getObject(new GetObjectRequest(bucketName, path+ keyName));

            InputStream is = s3object.getObjectContent();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            int len;
            byte[] buffer = new byte[4096];
            while ((len = is.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, len);
            }

            return outputStream;
        } catch (IOException ioException) {
            System.out.println("IOException: " + ioException.getMessage());
        } catch (AmazonServiceException serviceException) {
            System.out.println("AmazonServiceException Message:    " + serviceException.getMessage());
            throw serviceException;
        } catch (AmazonClientException clientException) {
            System.out.println("AmazonClientException Message: " + clientException.getMessage());
            throw clientException;
        }

        return null;
    }


    private String generateFileName(MultipartFile multiPart, int i) {
        return new Date().getTime() + "-" + String.valueOf(i).replace(" ", "_");
    }

    private File convertMultiPartToFile(MultipartFile file, int i) throws IOException {
        File convFile = new File(Objects.requireNonNull(String.valueOf(i)));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    public void deleteFileFromS3Bucket(String fileUrl, AWSEventImagesUploadDTO awsEventImagesUploadDTO) {
        String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
        String path = awsEventImagesUploadDTO.getUsername() + "/" + awsEventImagesUploadDTO.getEventName() + "/";
        s3Client.deleteObject(bucketName, path + fileName);
    }


}
