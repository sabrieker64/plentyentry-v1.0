package at.commodussolutions.plentyentry.ordermanagement.event.aws.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.aws.beans.UserEventData;
import at.commodussolutions.plentyentry.ordermanagement.event.aws.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.util.List;

@RestController
@RequestMapping({"/api/backend/storage"})
public class AwsBucketRestServiceImpl {

    private final AmazonClient amazonClient;

    @Autowired
    AwsBucketRestServiceImpl(AmazonClient amazonClient) {
        this.amazonClient = amazonClient;
    }

    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(MultipartFile file) {
        return this.amazonClient.uploadFile(file);
    }

    @PostMapping("/listFiles")
    @ResponseBody
    public List<String> listFile(@RequestBody UserEventData userEventData) {
        return this.amazonClient.listFiles(userEventData.getUsername(), userEventData.getEventName());
    }

    @GetMapping(value = "/download/{filename}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String filename) {
        ByteArrayOutputStream downloadInputStream = this.amazonClient.downloadFile(filename);

        return ResponseEntity.ok()
                .contentType(contentType(filename))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(downloadInputStream.toByteArray());
    }

    @DeleteMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(@RequestParam String url) {
        return this.amazonClient.deleteFileFromS3Bucket(url);
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
