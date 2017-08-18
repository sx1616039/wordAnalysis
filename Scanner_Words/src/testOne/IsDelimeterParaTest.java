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
public class IsDelimeterParaTest {

	private static String delimeter[]={",",";","(",")","{","}","\'","\"",":","#"};
	private static String errors[]={"-+","<>","test","\\"};
	private static int expected[]={0,41,42,43,44,45,46,47,48,49,50};
	
	private static Analysis analysis = new Analysis();
	private   String  param;
	private   int  result;
	
	@SuppressWarnings("unchecked")
	@Parameters	 
    public   static  Collection data1()  {
		List list = new ArrayList<Object[]>();
		for (int i=0; i<10; i++){
			list.add(new Object[]{expected[i+1],delimeter[i]});
		}
		for (int i=0; i<4; i++){
			list.add(new Object[]{expected[0],errors[i]});
		}
		return list;
   } 
	// 构造函数，对变量进行初始化 
    public  IsDelimeterParaTest(int  result,  String  param )  {
        this .param  =  param;
        this .result  =  result;
   } 

	@Test
	public void testIsKeyword() {
	    assertEquals(result, analysis.isDelimeter(param));
	}

}
