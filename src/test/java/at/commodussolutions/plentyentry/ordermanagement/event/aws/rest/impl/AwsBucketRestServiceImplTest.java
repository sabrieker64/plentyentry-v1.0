package at.commodussolutions.plentyentry.ordermanagement.event.aws.rest.impl;

import at.commodussolutions.plentyentry.ordermanagement.event.aws.dto.AWSEventImagesUploadDTO;
import at.commodussolutions.plentyentry.ordermanagement.event.dto.EventDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@Transactional
public class AwsBucketRestServiceImplTest {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    private final static String baseUrl = "/api/backend/storage";

    @Value("classpath:images/mina_tattoo.jpg")
    Resource resourceFile;

    public AWSEventImagesUploadDTO getAWSEventImagesUploadDTO() {
        AWSEventImagesUploadDTO AWSEventImagesUploadDTO = new AWSEventImagesUploadDTO();
        AWSEventImagesUploadDTO.setUsername("PrototypeUsername");
        AWSEventImagesUploadDTO.setEventName("PrototypeEvent");
        return AWSEventImagesUploadDTO;
    }

    @Test
    public void uploadFiles() throws Exception {

        Assertions.assertNotNull(resourceFile);

        List<MockMultipartFile> files = new ArrayList<>();

        MockMultipartFile firstFile = new MockMultipartFile(
                "files",resourceFile.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                resourceFile.getInputStream());

        Assertions.assertNotNull(firstFile);

        MockMultipartFile file = new MockMultipartFile("files", resourceFile.getFilename(), MediaType.MULTIPART_FORM_DATA_VALUE, resourceFile.getInputStream());

        MockMultipartFile secondFile = new MockMultipartFile(
                "files",resourceFile.getFilename(),
                MediaType.MULTIPART_FORM_DATA_VALUE,
                resourceFile.getInputStream());

        Assertions.assertNotNull(firstFile);


        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl+"/uploadFiles")
                        .accept(MediaType.MULTIPART_FORM_DATA)
                        .content(file.getBytes())
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getAWSEventImagesUploadDTO()))
                        .contentType(MediaType.MULTIPART_FORM_DATA))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<String> resultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        Assertions.assertEquals(1,resultList.size());
    }

    @Test
    public void listFiles() throws Exception {

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl+"/listFiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getAWSEventImagesUploadDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<ResponseEntity<byte[]>> resultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        //Assertions.assertEquals("https://eventimagesbucket.s3-us-east-2.amazonaws.com/PrototypeUsername/PrototypeEvent/1648892320691-mina_tattoo.jpg", resultList.get(0));
        Assertions.assertEquals(3,resultList.size());

    }

    public void downloadFiles() {

    }

    @Test
    public void deleteFiles() throws Exception {

        AWSEventImagesUploadDTO awsEventImagesUploadDTO = getAWSEventImagesUploadDTO();
        ArrayList<String> urls = new ArrayList<>();


        //GETFILES
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl+"/listFiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getAWSEventImagesUploadDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<String> resultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        for(String result : resultList) {
            urls.add(result);
        }

        awsEventImagesUploadDTO.setUrls(urls);


        mvcResult = mvc.perform(MockMvcRequestBuilders.delete(baseUrl+"/deleteFiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(awsEventImagesUploadDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        resultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        Assertions.assertEquals("Deleted",resultList.get(0));

    }
}
