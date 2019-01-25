package com.hellokoding.springboot.Service;

import com.hellokoding.springboot.Repo.Data;
import com.hellokoding.springboot.Repo.Product;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SenderService {

    @Autowired
    RestTemplate restTemplate;

    public String serverPrefix = "http://";
    public String serverIp = "localhost";
    public String serverPort = ":8085";
    public String serverPostfix = "/server";
    public String serverUrl = serverPrefix+serverIp+serverPort+serverPostfix;


    public String sendData(Product product,MultipartFile file){
        System.out.println("senderservicesendData");
        Data sendData = new Data();
        sendData.setBaseIp(getIp());
        sendData.setTargetIp(product.sendIp);
        //sendData.setFile(file);

        HttpHeaders header = new HttpHeaders(); header.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Data> requestEntity = new HttpEntity<Data>(sendData, header);

        restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(serverUrl+"/sendData", HttpMethod.POST,requestEntity,String.class);
        if (result.getBody().equals("OK"))
            return "OK";
        else return "NO";
    }

    public String sendBoth(Product product,MultipartFile file) throws IOException {
        System.out.println("senderservicesenBOTH");
        boolean success = false;
        Data sendData = new Data();
        sendData.setBaseIp(getIp());
        sendData.setTargetIp(product.sendIp);
        //MultipartFile a = new MockMultipartFile("a",file.getBytes());
        //sendData.setFile(a);

        if(!file.isEmpty()){

            List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
            acceptableMediaTypes.add(MediaType.MULTIPART_FORM_DATA);
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(acceptableMediaTypes);
            MultiValueMap<String, Object> valueMap = new LinkedMultiValueMap<String, Object>();

            ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };
            valueMap.add("file",fileAsResource);
            valueMap.add("data",sendData);

            HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(valueMap, headers);

            restTemplate = new RestTemplate();
            System.out.println("post öncesi");
            ResponseEntity<String> response = restTemplate.exchange(
                    serverUrl+"/sendBoth", HttpMethod.POST, entity,
                    String.class);
            System.out.println("post sonrası");
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                success = true;
            }

            /*
            ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
                @Override
                public String getFilename() {
                    return file.getOriginalFilename();
                }
            };

            LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
            map.add("file", fileAsResource);
            map.add("data",sendData);

            restTemplate=new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.MULTIPART_FORM_DATA);

            HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

            System.out.println("post öncesi");
            ResponseEntity<String> response = restTemplate.exchange(serverUrl+"/sendBoth",
                    HttpMethod.POST,
                    entity,
                    String.class);

            System.out.println("post sonrası");
            if (response.getStatusCode().equals(HttpStatus.OK)) {
                success = true;
            }*/
        }
        if(success==true) return "OK";
        else return "NO" ;

    }


    public String sendFile(MultipartFile file){
        boolean success = false;
        if (!file.isEmpty()) {
            try {
                ByteArrayResource fileAsResource = new ByteArrayResource(file.getBytes()) {
                    @Override
                    public String getFilename() {
                        return file.getOriginalFilename();
                    }
                };

                LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
                map.add("file", fileAsResource);

                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.MULTIPART_FORM_DATA);

                HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

                ResponseEntity<String> response = restTemplate.exchange(serverUrl+"/sendFile",
                        HttpMethod.POST,
                        entity,
                        String.class);
                if (response.getStatusCode().equals(HttpStatus.OK)) {
                    success = true;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if(success==true) return "OK";
        else return "NO" ;

        /*System.out.println("senderservicesendFile");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file);

        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);

        ResponseEntity<String> response = restTemplate
                .postForEntity(serverUrl+"/sendFile", requestEntity, String.class);
*/
    }

    private String getIp(){
        InetAddress ip;
        try {
            ip = InetAddress.getLocalHost();
            return ip.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }

}
