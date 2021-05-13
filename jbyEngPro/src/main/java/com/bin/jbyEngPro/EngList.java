package com.bin.jbyEngPro;

import java.util.ArrayList;
import java.util.List;

//一个用来存储单词列表的数据结构
public class EngList {
	public List<String> wordList = new ArrayList<String>();//辅助单词列表
	public List<String> translation = new ArrayList<String>();//辅助翻译列表
	public List<String> pronunciation = new ArrayList<String>();//辅助发音列表
}