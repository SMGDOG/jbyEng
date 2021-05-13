package com.bin.jbyEngPro;

import org.junit.Test;
import com.bin.jbyEngPro.Controller.Controller;

public class ServiceTest {
	@Test
	public void test1() {
		//测试搜索单词
		Controller test=new Controller();
		if(test.searchWord("apple")!=null) {
			System.out.println("success");
		}
	}
	@Test
	public void test2() {
		//测试用户登录
		Controller test=new Controller();
		if(test.login("475869142536")) {
			System.out.println("success");
		}
	}
	@Test
	public void test3() {
		//测试改变昵称
		Controller test=new Controller();
		if(test.changeName("475869142536", "testUser")) {
			System.out.println("success");
		}
	}
	@Test
	public void test4() {
		//测试设置学习情况
		Controller test=new Controller();
		if(test.learn("475869142536", 0, "CET4")) {
			System.out.println("success");
		}
	}
	@Test
	public void test5() {
		//测试重置学习情况
		Controller test=new Controller();
		if(test.reset("475869142536")) {
			System.out.println("success");
		}
	}
	@Test
	public void test6() {
		//测试记录学习情况
		Controller test=new Controller();
		if(test.getNum("475869142536", 30)) {
			System.out.println("success");
		}
	}
	@Test
	public void test7() {
		//测试重新设置每日学习量
		Controller test=new Controller();
		if(test.resetNum("475869142536", 25)) {
			System.out.println("success");
		}
	}
	@Test
	public void test8() {
		//测试记录复习量
		Controller test=new Controller();
		if(test.getReviewNum("475869142536", 20)) {
			System.out.println("success");
		}
	}
	@Test
	public void test9() {
		//测试获取每日学习单词
		Controller test=new Controller();
		if(test.getTodayWord("475869142536")!=null) {
			System.out.println("success");
		}
	}
	@Test
	public void test10() {
		//测试获取每日复习单词
		Controller test=new Controller();
		if(test.getReviewWord("475869142536")!=null) {
			System.out.println("success");
		}
	}
}
