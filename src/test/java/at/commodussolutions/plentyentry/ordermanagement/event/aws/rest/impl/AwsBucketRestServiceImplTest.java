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

    @Value("classpath:images/1806-tuerkei-818-dunkel-maxpane-818-290-jpg--8ae8dd9828dbe9dc-.jpg")
    Resource resourceFile2;

    public AWSEventImagesUploadDTO getAWSEventImagesUploadDTO() {
        AWSEventImagesUploadDTO awsEventImagesUploadDTO = new AWSEventImagesUploadDTO();
        awsEventImagesUploadDTO.setUsername("PrototypeUsername");
        awsEventImagesUploadDTO.setEventName("PrototypeEvent");
        return awsEventImagesUploadDTO;
    }

    @Test
    public void uploadFiles() throws Exception {


        //FIRST DELETE ALL FILES
        deleteFiles();


        //UPLOAD
        Assertions.assertNotNull(resourceFile);
        Assertions.assertNotNull(resourceFile2);


        var awsEventImagesUploadDTO = getAWSEventImagesUploadDTO();
        MockMultipartFile file2 = new MockMultipartFile("files", resourceFile2.getFilename(), MediaType.APPLICATION_JSON_VALUE, resourceFile2.getInputStream());

        MockMultipartFile file = new MockMultipartFile("files", resourceFile.getFilename(), MediaType.APPLICATION_JSON_VALUE, resourceFile.getInputStream());

        MockMultipartFile jsonFile = new MockMultipartFile("awsEventImagesUploadDTO", "", "application/json", ("{" +
                "\"username\":" + "\""+awsEventImagesUploadDTO.getUsername()+"\""+","+
                "\"eventName\":" + "\""+awsEventImagesUploadDTO.getEventName()+"\"}"

        ).getBytes());

        Assertions.assertNotNull(file);
        Assertions.assertNotNull(file2);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.multipart(baseUrl+"/uploadFiles")
                        .file(file)
                        .file(file2)
                        .file(jsonFile))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<String> resultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });



        //LISTFILES
        MvcResult listFilesMvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl+"/listFiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getAWSEventImagesUploadDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<String> listFilesResultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });


        //CHECK UPLOAD
        Assertions.assertEquals(listFilesResultList.size(),resultList.size());


    }

    @Test
    public void listFiles() throws Exception {

        //FIRST DELETE
        deleteFiles();

        //UPLOADFILES
        Assertions.assertNotNull(resourceFile);
        Assertions.assertNotNull(resourceFile2);


        var awsEventImagesUploadDTO = getAWSEventImagesUploadDTO();
        MockMultipartFile file2 = new MockMultipartFile("files", resourceFile2.getFilename(), MediaType.APPLICATION_JSON_VALUE, resourceFile2.getInputStream());

        MockMultipartFile file = new MockMultipartFile("files", resourceFile.getFilename(), MediaType.APPLICATION_JSON_VALUE, resourceFile.getInputStream());

        MockMultipartFile jsonFile = new MockMultipartFile("awsEventImagesUploadDTO", "", "application/json", ("{" +
                "\"username\":" + "\""+awsEventImagesUploadDTO.getUsername()+"\""+","+
                "\"eventName\":" + "\""+awsEventImagesUploadDTO.getEventName()+"\"}"

        ).getBytes());

        Assertions.assertNotNull(file);
        Assertions.assertNotNull(file2);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.multipart(baseUrl+"/uploadFiles")
                        .file(file)
                        .file(file2)
                        .file(jsonFile))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<String> resultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });


        //LISTFILES
        MvcResult listMvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl+"/listFiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(getAWSEventImagesUploadDTO()))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<String> listResultList =  objectMapper.readValue(mvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });



        Assertions.assertEquals(resultList.size(),listResultList.size());

    }


    @Test
    public void deleteFiles() throws Exception {

        AWSEventImagesUploadDTO awsEventImagesUploadDTO = getAWSEventImagesUploadDTO();
        ArrayList<String> urls = new ArrayList<>();


        //GETFILES
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl+"/listFiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(awsEventImagesUploadDTO))
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


        //LISTFILES TO CHECK IF EVERY FILE GOT DELETED
        MvcResult listMvcResult = mvc.perform(MockMvcRequestBuilders.post(baseUrl+"/listFiles")
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(awsEventImagesUploadDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<String> listResultList =  objectMapper.readValue(listMvcResult.getResponse().getContentAsString(StandardCharsets.UTF_8),
                new TypeReference<>() {
                });

        Assertions.assertEquals(0,listResultList.size());

    }
}
