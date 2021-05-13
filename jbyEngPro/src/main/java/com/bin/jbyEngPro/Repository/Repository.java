package com.bin.jbyEngPro.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import com.bin.jbyEngPro.EngList;

//数据库操作
public class Repository {
	private String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private String url="jdbc:sqlserver://localhost:1433; DatabaseName=jbyEng";
	private String username="sa";
	private String password="aA415263748596";
	private Connection conn=null;
	//初始化连接
	public Repository() {
		try {
			Class.forName(driverName);
			conn=DriverManager.getConnection(url,username,password);
			System.out.println("连接数据库成功......");
		} catch (SQLException e) {
			System.out.println("连接数据库失败......");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	//获取单词库
	public Map<String,Object> getEngList(int type){
		EngList list=new EngList();
		Map<String,Object> map=new HashMap<String, Object>();
		Statement st=null;
		ResultSet rs=null;
		
		String sql1="select * from CET4";
		String sql2="select * from CET6";
		
		if(type==1) {
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql1);
				while(rs.next()) {
					if(rs.getString(2)!=null)list.wordList.add(rs.getString(2));
					if(rs.getString(3)!=null)list.pronunciation.add(rs.getString(3));
					if(rs.getString(4)!=null)list.translation.add(rs.getString(4));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(type==2) {
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql2);
				while(rs.next()) {
					if(rs.getString(2)!=null)list.wordList.add(rs.getString(2));
					if(rs.getString(3)!=null)list.pronunciation.add(rs.getString(3));
					if(rs.getString(4)!=null)list.translation.add(rs.getString(4));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("单词",list.wordList);
		map.put("发音",list.pronunciation);
		map.put("翻译",list.translation);
		return map;
	}
	//搜索单词
	public Map<String,String> searchWord(String word){
		Map<String,String> map=new HashMap<String,String>();
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call search_word(?,?,?,?,?)}");
			cs.setObject(1, word);
			cs.registerOutParameter(2,java.sql.Types.NVARCHAR);
			cs.registerOutParameter(3,java.sql.Types.NVARCHAR);
			cs.registerOutParameter(4,java.sql.Types.NVARCHAR);
			cs.registerOutParameter(5,java.sql.Types.NVARCHAR);
			cs.execute();
			map.put("单词",word);
			map.put("美式发音",cs.getNString(2));
			map.put("英式发音",cs.getNString(3));
			map.put("翻译",cs.getNString(4));
			map.put("词性",cs.getNString(5));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	//登陆
	public boolean user_login(String id) {
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call login(?)}");
			cs.setObject(1, id);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//改变昵称
	public boolean change_name(String id, String name) {
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call changeName(?,?)}");
			cs.setObject(1, id);
			cs.setObject(2, name);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//设置学习
	public boolean user_learn(String id,int num,String table_name) {
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call learn_log(?,?,?)}");
			cs.setObject(1, id);
			cs.setObject(2, num);
			cs.setObject(3, table_name);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//重置学习
	public boolean user_reset(String id) {
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call reset_log(?)}");
			cs.setObject(1, id);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//记录学习
	public boolean user_getNum(String id, int num) {
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call get_log(?,?)}");
			cs.setObject(1, id);
			cs.setObject(2, num);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//获取用户信息
	public Map<String,Object> user_getinfo(String id){
		Map<String,Object> map=new HashMap<String,Object>();
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call get_learninfo(?,?,?,?,?,?)}");
			cs.setObject(1, id);
			cs.registerOutParameter(2,java.sql.Types.NVARCHAR);
			cs.registerOutParameter(3,java.sql.Types.NVARCHAR);
			cs.registerOutParameter(4,java.sql.Types.INTEGER);
			cs.registerOutParameter(5,java.sql.Types.INTEGER);
			cs.registerOutParameter(6,java.sql.Types.INTEGER);
			cs.execute();
			map.put("id", id);
			map.put("name", cs.getObject(2));
			map.put("正在学习", cs.getObject(3));
			map.put("每日学习量", cs.getObject(4));
			map.put("已学习数量", cs.getObject(5));
			map.put("已复习数量", cs.getObject(6));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
	//重新设置每日学习量
	public boolean user_resetNum(String id,int num) {
		CallableStatement cs=null;
		try {
			cs=conn.prepareCall("{call reset_num(?,?)}");
			cs.setObject(1, id);
			cs.setObject(2, num);
			cs.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	//获取今日份学习
	public Map<String,Object> getTodayWord(String id){
		Map<String,Object> map=new HashMap<String,Object>();
		String table=null;//单词库
		int num=0;//学习总量
		int sum=0;//复习总量
		CallableStatement cs1=null;
		try {
			cs1=conn.prepareCall("{call get_learninfo(?,?,?,?,?,?)}");
			cs1.setObject(1, id);
			cs1.registerOutParameter(2,java.sql.Types.NVARCHAR);
			cs1.registerOutParameter(3,java.sql.Types.NVARCHAR);
			cs1.registerOutParameter(4,java.sql.Types.INTEGER);
			cs1.registerOutParameter(5,java.sql.Types.INTEGER);
			cs1.registerOutParameter(6,java.sql.Types.INTEGER);
			cs1.execute();
			table=cs1.getNString(3);
			num=cs1.getInt(4);
			sum=cs1.getInt(5);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(num==0) {
			return null;
		}
		int first=sum+1;
		int last=num+sum;
		Statement st=null;
		ResultSet rs=null;
		EngList list=new EngList();
		if(table.equals("CET4")) {
			String sql=String.format("select * from CET4 where 序号>=%d and 序号<=%d",first,last);
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
					if(rs.getString(2)!=null)list.wordList.add(rs.getString(2));
					if(rs.getString(3)!=null)list.pronunciation.add(rs.getString(3));
					if(rs.getString(4)!=null)list.translation.add(rs.getString(4));
	            }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(table.equals("CET6")) {
			String sql=String.format("select * from CET6 where 序号>=%d and 序号<=%d",first,last);
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
					if(rs.getString(2)!=null)list.wordList.add(rs.getString(2));
					if(rs.getString(3)!=null)list.pronunciation.add(rs.getString(3));
					if(rs.getString(4)!=null)list.translation.add(rs.getString(4));
	            }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("单词",list.wordList);
		map.put("发音",list.pronunciation);
		map.put("翻译",list.translation);
		return map;
	}
	//获取今日份复习单词
	public Map<String,Object> getReviewWord(String id){
		Map<String,Object> map=new HashMap<String,Object>();
		String table=null;//单词库
		int num=0;//每日学习量
		int sum=0;//学习总量
		int review=0;//复习总量
		CallableStatement cs1=null;
		try {
			cs1=conn.prepareCall("{call get_learninfo(?,?,?,?,?,?)}");
			cs1.setObject(1, id);
			cs1.registerOutParameter(2,java.sql.Types.NVARCHAR);
			cs1.registerOutParameter(3,java.sql.Types.NVARCHAR);
			cs1.registerOutParameter(4,java.sql.Types.INTEGER);
			cs1.registerOutParameter(5,java.sql.Types.INTEGER);
			cs1.registerOutParameter(6,java.sql.Types.INTEGER);
			cs1.execute();
			table=cs1.getNString(3);
			num=cs1.getInt(4);
			sum=cs1.getInt(5);
			review=cs1.getInt(6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int first=0;
		int last=0;
		num=num*2;
		if(sum==0) {
			return null;
		}
		else if(review+num>=sum) {
			first=review+1;
			last=sum;
		}
		else {
			first=review+1;
			last=review+num;
		}
		
		Statement st=null;
		ResultSet rs=null;
		EngList list=new EngList();
		if(table.equals("CET4")) {
			String sql=String.format("select * from CET4 where 序号>=%d and 序号<=%d",first,last);
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
					if(rs.getString(2)!=null)list.wordList.add(rs.getString(2));
					if(rs.getString(3)!=null)list.pronunciation.add(rs.getString(3));
					if(rs.getString(4)!=null)list.translation.add(rs.getString(4));
	            }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else if(table.equals("CET6")) {
			String sql=String.format("select * from CET6 where 序号>=%d and 序号<=%d",first,last);
			try {
				st=conn.createStatement();
				rs=st.executeQuery(sql);
				while(rs.next()) {
					if(rs.getString(2)!=null)list.wordList.add(rs.getString(2));
					if(rs.getString(3)!=null)list.pronunciation.add(rs.getString(3));
					if(rs.getString(4)!=null)list.translation.add(rs.getString(4));
	            }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("单词",list.wordList);
		map.put("发音",list.pronunciation);
		map.put("翻译",list.translation);
		return map;
	}
	//记录复习情况
	public boolean getReviewNum(String id, int num) {
		int review=0;//复习两
		int sum=0;//学习总量
		CallableStatement cs1=null;
		try {
			cs1=conn.prepareCall("{call get_learninfo(?,?,?,?,?,?)}");
			cs1.setObject(1, id);
			cs1.registerOutParameter(2,java.sql.Types.NVARCHAR);
			cs1.registerOutParameter(3,java.sql.Types.NVARCHAR);
			cs1.registerOutParameter(4,java.sql.Types.INTEGER);
			cs1.registerOutParameter(5,java.sql.Types.INTEGER);
			cs1.registerOutParameter(6,java.sql.Types.INTEGER);
			cs1.execute();
			sum=cs1.getInt(5);
			review=cs1.getInt(6);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(review+num<sum) {
			CallableStatement cs=null;
			try {
				cs=conn.prepareCall("{call get_review(?,?)}");
				cs.setObject(1, id);
				cs.setObject(2, num);
				cs.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		else {
			CallableStatement cs=null;
			try {
				cs=conn.prepareCall("{call reset_review(?)}");
				cs.setObject(1, id);
				cs.execute();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
	}
}