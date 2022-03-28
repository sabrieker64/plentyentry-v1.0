package at.commodussolutions.plentyentry.ordermanagement.event.aws.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.aws.service.AmazonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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


    @DeleteMapping("/deleteFile")
    @ResponseBody
    public String deleteFile(@RequestParam String url) {
        return this.amazonClient.deleteFileFromS3Bucket(url);
    }
}
