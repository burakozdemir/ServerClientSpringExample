package com.hellokoding.springboot.Service;

import com.hellokoding.springboot.Repo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SendService {

    @Autowired
    RestTemplate restTemplate;

    public String sendData(String url, Data data){
        System.out.println("serverservicesendData");
        HttpHeaders headers = new HttpHeaders(); headers.setContentType(MediaType.APPLICATION_XML);
        HttpEntity<Data> requestEntity = new HttpEntity<Data>(data, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> result = restTemplate.exchange(url+"/getData", org.springframework.http.HttpMethod.POST,requestEntity,String.class);
        if(result.getBody().equals("OK"))
            return "OK";
        else return "NO";
    }

    public String sendFile(String url , MultipartFile file){
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

                ResponseEntity<String> response = restTemplate.exchange(url+"/getFile",
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

    }


}
