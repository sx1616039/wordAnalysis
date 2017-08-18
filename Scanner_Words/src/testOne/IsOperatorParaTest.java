package testOne;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import one.Analysis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized. class )
public class IsOperatorParaTest {

	private static String operator[]={"+","-","*","/","%","=",">","<","!","==","!=",
							   ">=","<=","++","--","&","&&","||","[","]"};
	private static String errors[]={"-+","<>","test","`"};
	private static int expected[]={0,21,22,23,24,25,26,27,28,29,30,31,
							32,33,34,35,36,37,38,39,40};
	private static Analysis analysis = new Analysis();
	private   String  param;
	private   int  result;
	
	@SuppressWarnings("unchecked")
	@Parameters	 
    public   static  Collection data1()  {
		List list = new ArrayList<Object[]>();
		for (int i=0; i<20; i++){
			list.add(new Object[]{expected[i+1],operator[i]});
		}
		for (int i=0; i<4; i++){
			list.add(new Object[]{expected[0],errors[i]});
		}
		return list;
   } 
	// 构造函数，对变量进行初始化 
    public  IsOperatorParaTest(int  result,  String  param )  {
        this .param  =  param;
        this .result  =  result;
   } 

	@Test
	public void testIsKeyword() {
	    assertEquals(result, analysis.isOperator(param));
	}

}
