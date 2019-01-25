package com.hellokoding.springboot.Repo;


import java.io.Serializable;
import java.util.List;

public class Product implements Serializable
{
    public String sendIp;
    public String getSendIp() {
        return sendIp;
    }

    public void setSendIp(String sendIp) {
        this.sendIp = sendIp;
    }

    public Product (){}
}