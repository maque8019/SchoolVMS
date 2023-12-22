package DAL;

import dbUtils.IAccessDB;
import dbUtils.JavaMySQL;

public abstract class abstractDAO {
	public String errorMessage="";  //错误信息
	IAccessDB db=null;  //数据库访问接口对象
	//构造函数
	public abstractDAO()
	{
		db=  new JavaMySQL();//数据库访问类
		errorMessage="";
	}
	//获得类内部发生的错误信息
	public String getErrorMessage() {
			return errorMessage;
	}
	//获得类内部发生的错误信息
	public void setErrorMessage(String msg) {
			errorMessage=msg;
	}
}
