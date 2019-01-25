package com.hellokoding.springboot.Controller;

import com.hellokoding.springboot.Repo.Data;
import com.hellokoding.springboot.Service.GetterService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.print.attribute.standard.Media;
import javax.servlet.ServletContext;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

@Controller
@RequestMapping(value = "reciever")
public class GetterController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private GetterService getterService;

    @Autowired
    ResourceLoader resourceLoader;

    public Vector<String> files = new Vector<>(5);
    public Data currentData;
    public String photo = "" ;

    MultipartFile img ;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        model.addAttribute("photo",photo);
        //Data data = new Data();
        //data.setBaseIp("testIp");
        model.addAttribute("data",currentData);
        return "recieveFile";
    }

    @RequestMapping(method = RequestMethod.GET ,value = "/reciever/getImage/{name}")
    public byte[] getImage(@PathVariable String name) throws IOException {
        String path = null;
        for (String photo: files) {
            if(photo.equals(name))
                path=photo;
        }
        return extractBytes(path);

        //return img.getBytes();
    }

    private byte[] extractBytes (String ImageName) throws IOException {
        // open image
        File imgPath = new File(ImageName);
        BufferedImage bufferedImage = ImageIO.read(imgPath);
        // get DataBufferBytes from Raster
        WritableRaster raster = bufferedImage .getRaster();
        DataBufferByte data   = (DataBufferByte) raster.getDataBuffer();

        return ( data.getData() );
    }


    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    @ResponseBody
    public String getAndShow(@RequestBody Data data ,// @RequestParam("file") MultipartFile file ,
                             Model model) {
        System.out.println("recievergetdata");
        if(data!=null) {
            System.out.println(data.getMessage());
            currentData = data;
            return "OK";
        }
        else return "NO";
    }

    @RequestMapping(value = "/getFile", method = RequestMethod.POST)
    @ResponseBody
    public String saveFileAndShow(@RequestBody MultipartFile file ,// @RequestParam("file") MultipartFile file ,
                             Model model) throws IOException {
        System.out.println("recievergetFile");
        if(file!=null) {
            img=file;
            photo = getterService.saveImage(files,file,servletContext);
            return "OK";
        }
        else return "NO";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {

    }


    @GetMapping(value = "/image/{fileName}",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody
    ResponseEntity<InputStreamResource> getImage2(@PathVariable String fileName) throws IOException {
        try {
            Resource s = resourceLoader.getResource("file:C:\\Users\\xyz\\Desktop\\software\\biztalkinspring\\Ads¦-z Klas+Âr\\client\\src\\main\\resources\\images\\" + fileName);
            System.out.println("photo : "+photo);
            System.out.println("filename : "+fileName);

            return ResponseEntity.ok().contentLength(s.contentLength()).body(new InputStreamResource(s.getInputStream()));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
        }
}
