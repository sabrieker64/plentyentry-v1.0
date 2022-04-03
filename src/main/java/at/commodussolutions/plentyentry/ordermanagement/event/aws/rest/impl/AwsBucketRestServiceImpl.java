package at.commodussolutions.plentyentry.ordermanagement.event.aws.rest.impl;


import at.commodussolutions.plentyentry.ordermanagement.event.aws.dto.AWSEventImagesUploadDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.aws.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping({"/api/backend/storage"})
public class AwsBucketRestServiceImpl {

    private final AmazonClient amazonClient;

    @Autowired
    AwsBucketRestServiceImpl(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFiles")
    @ResponseBody
    public List<String> uploadFiles(@RequestPart("files") MultipartFile[] files,@RequestPart AWSEventImagesUploadDTO awsEventImagesUploadDTO) {
        return this.amazonClient.uploadFiles(files, awsEventImagesUploadDTO);
    }

    @PostMapping("/listFiles")
    @ResponseBody
    public List<String> listFiles(@RequestBody AWSEventImagesUploadDTO eventImagesUploadDTO) {
        return this.amazonClient.listFiles(eventImagesUploadDTO.getUsername(), eventImagesUploadDTO.getEventName());
    }

    @GetMapping(value = "/download")
    public List<ResponseEntity<byte[]>> downloadFile(@RequestParam String[] filenames, @RequestPart AWSEventImagesUploadDTO awsEventImagesUploadDTO) {

        List<ResponseEntity<byte[]>> responseEntity = new ArrayList<>();
        for(String filename : filenames) {
            ByteArrayOutputStream downloadInputStream = this.amazonClient.downloadFile(filename, awsEventImagesUploadDTO);
            ResponseEntity<byte[]> tempResponsEntity = ResponseEntity.ok()
                    .contentType(contentType(filename))
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                    .body(downloadInputStream.toByteArray());
            responseEntity.add(tempResponsEntity);
        }

        return responseEntity;
    }

    /* DO WE NEED THIS?
    @DeleteMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(@RequestParam String url, @RequestBody AWSEventImagesUploadDTO awsEventImagesUploadDTO) {
        return this.amazonClient.deleteFileFromS3Bucket(url,awsEventImagesUploadDTO);
    }

     */

    @DeleteMapping("/deleteFiles")
    @ResponseBody
    public ArrayList<String> deleteFiles(@RequestBody AWSEventImagesUploadDTO awsEventImagesUploadDTO) {
        for (String url : awsEventImagesUploadDTO.getUrls()) {
            this.amazonClient.deleteFileFromS3Bucket(url,awsEventImagesUploadDTO);
        }
        ArrayList<String> temp = new ArrayList<String>();
        temp.add("Deleted");
        return temp;
    }

    private MediaType contentType(String filename) {
        String[] fileArrSplit = filename.split("\\.");
        String fileExtension = fileArrSplit[fileArrSplit.length - 1];
        switch (fileExtension) {
            case "txt":
                return MediaType.TEXT_PLAIN;
            case "png":
                return MediaType.IMAGE_PNG;
            case "jpg":
                return MediaType.IMAGE_JPEG;
            default:
                return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
