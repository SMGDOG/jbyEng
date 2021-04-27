package com.bin.jbyEngPro.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class Controller {
    @RequestMapping("getEngWord")
    public Map<String,Object> getEngWord(){
        System.out.println("微信小程序正在调用...");
        Map<String,Object> map = new HashMap<String, Object>();
        List<String> list = new ArrayList<String>();
        list.add("test1");
        list.add("test2");
        map.put("list",list);
        System.out.println("微信小程序调用完成...");
        return map;
    }
}