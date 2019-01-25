package com.hellokoding.springboot.Controller;

import com.hellokoding.springboot.Repo.Data;
import com.hellokoding.springboot.Service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value = "server")
public class myController {

    @Autowired
    private SendService sendService;

    public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
    public String recieverIpPrefix = "http://";
    public String recieverIp = "";
    public String recieverPort = ":8084";
    public String recieverPostfix = "/reciever";
    public String recieverUrl;

    @RequestMapping(value="/sendData", method = RequestMethod.POST,produces = "application/xml")
    @ResponseBody
    public String getData(@RequestBody Data data, Model model) {
        System.out.println("servergetData");
        data.setMessage("Serverdan DATA geldi");
        recieverIp = data.getTargetIp();
        recieverUrl = recieverIpPrefix+recieverIp+recieverPort+recieverPostfix;;

        if (sendService.sendData(recieverUrl,data).equals("OK"))
            return "OK";
        else
            return "NO";
    }

    @RequestMapping(value="/sendFile", method = RequestMethod.POST)
    @ResponseBody
    public String getData(@RequestBody MultipartFile file, Model model) {
        System.out.println("servergetFile");
        if (sendService.sendFile(recieverUrl,file).equals("OK"))
            return "OK";
        else
            return "NO";
    }

}
