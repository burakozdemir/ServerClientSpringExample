package com.hellokoding.springboot.Service;

import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Vector;

@Service
public class GetterService {

    public String saveImage(Vector<String> files ,MultipartFile multipartFile, ServletContext servletContext) throws IOException {
        System.out.println("recieverServicesaveImage");
        File res = new File(System.getProperty("user.dir")+"\\src\\main\\resources\\images\\"+multipartFile.getOriginalFilename());
        FileCopyUtils.copy(multipartFile.getBytes(),res);
        files.add(res.getAbsolutePath());
        return multipartFile.getOriginalFilename();
    }
}
