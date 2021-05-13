package com.bin.jbyEngPro.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bin.jbyEngPro.Repository.Repository;

@RestController
@SpringBootApplication
@RequestMapping("jbyEng")
public class Controller {
	private Repository link=new Repository();
	//获取单词库，目前支持46级
    @RequestMapping("/getEngList")
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
    @RequestMapping("/searchWord")
    public Map<String,String> searchWord(String word){
    	return link.searchWord(word);
    }
    //用户登录
    @RequestMapping("/login")
    public boolean login(String id){
    	return link.user_login(id);
    }
    //修改名称
    @RequestMapping("/changeName")
    public boolean changeName(String id, String name){
    	return link.change_name(id,name);
    }
    //记录用户正在学习的单词表和设置的每日学习数量
    @RequestMapping("/learn")
    public boolean learn(String id,int num,String table_name){
    	return link.user_learn(id, num, table_name);
    }
    //重置学习单词数
    @RequestMapping("/reset")
    public boolean reset(String id){
    	return link.user_reset(id);
    }
    //记录用户学习单词总量
    @RequestMapping("/getNum")
    public boolean getNum(String id,int num){
    	return link.user_getNum(id, num);
    }
    //获取用户的学习情况
    @RequestMapping("/getLearnInfo")
    public Map<String,Object> getLearnInfo(String id){
    	return link.user_getinfo(id);
    }
    //重新设置用户的每日学习量
    @RequestMapping("/resetnum")
    public boolean resetNum(String id,int num) {
    	return link.user_resetNum(id, num);
    }
    //获取今天要学习的单词
    @RequestMapping("/getTodayWord")
    public Map<String,Object> getTodayWord(String id){
    	return link.getTodayWord(id);
    }
    //获取今天要复习的单词
    @RequestMapping("/getReviewWord")
    public Map<String,Object> getReviewWord(String id){
    	return link.getReviewWord(id);
    }
    //记录用户复习单词总量
    @RequestMapping("/getReviewNum")
    public boolean getReviewNum(String id,int num){
    	return link.getReviewNum(id, num);
    }
}