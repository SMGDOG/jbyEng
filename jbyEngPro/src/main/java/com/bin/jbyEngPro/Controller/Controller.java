package com.bin.jbyEngPro.Controller;

import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bin.jbyEngPro.Repository.Repository;

@RestController
@SpringBootApplication
public class Controller {
	private Repository link=new Repository();
	//获取单词库，目前支持46级
    @RequestMapping("getEngList")
    public Map<String,Object> getEngList(String table_name){
    	if(table_name.equals("CET4"))
    		return link.getEngList(1);
    	else if(table_name.equals("CET6"))
    		return link.getEngList(2);
    	else
    		System.out.println("Wrong!");
    		return null;
    }
    //查找单词
    @RequestMapping("searchWord")
    public Map<String,String> searchWord(String word){
    	return link.searchWord(word);
    }
    //用户登录
    @RequestMapping("login")
    public boolean login(String id,String name){
    	return link.user_login(id, name);
    }
    //记录用户学习单词数
    @RequestMapping("learn")
    public boolean learn(String id,int num,String table_name){
    	return link.user_learn(id, num, table_name);
    }
    //重置学习单词数
    @RequestMapping("reset")
    public boolean reset(String id,String table_name){
    	return link.user_reset(id, table_name);
    }
    //获取用户学习情况
    @RequestMapping("getNum")
    public int getNum(String id,String table_name){
    	return link.user_get(id, table_name);
    }
}