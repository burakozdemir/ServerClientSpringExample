package com.hellokoding.springboot.Controller;

import com.hellokoding.springboot.Repo.Product;
import com.hellokoding.springboot.Service.GetterService;
import com.hellokoding.springboot.Service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping(value = "sender")
public class SenderController implements ServletContextAware {

    private ServletContext servletContext;

    @Autowired
    private SenderService senderService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        System.out.println("senderget");
        model.addAttribute("product", new Product());
        return "sendFile";
    }

    @RequestMapping(value = "/sendToServer", method = RequestMethod.POST)
    public String save(@ModelAttribute("product") Product product, @RequestParam(value = "file") MultipartFile file,
                       Model model) throws Exception {

        System.out.println("senderpost");
        if(senderService.sendData(product,file).equals("OK") && senderService.sendFile(file).equals("OK"))
            return "sendSucces";
        else
            return "sendError";
    }


    @RequestMapping(value = "/sendBoth", method = RequestMethod.POST)
    public String sendBoth(@ModelAttribute("product") Product product, @RequestParam(value = "file") MultipartFile file,
                       Model model) throws Exception {

        System.out.println("senderboth");
        if(senderService.sendBoth(product,file).equals("OK"))
            return "sendSucces";
        else
            return "sendError";
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

}
