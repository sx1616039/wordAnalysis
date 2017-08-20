package testOne;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import one.Analysis;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized. class )
public class IskeywordParaTest {
	private static String keyword[]={"int","long","char","if",
							  "else","for","while","return",
							  "break","continue","switch","case",
							  "default","float","double","void",
							  "struct","static","do","short"};
	private static String errors[]={"hello","return","test","whie"};
	private static int expected[]={0,1,2,3,4,5,6,7,8,9,10,11,
							12,13,14,15,16,17,18,19,20};
	private static Analysis analysis = new Analysis();
	private   String  param;
	private   int  result;	
	@SuppressWarnings("unchecked")
	@Parameters	 
    public   static  Collection data1()  {
		List list = new ArrayList<Object[]>();
		for (int i=0; i<20; i++){
			list.add(new Object[]{expected[i+1],keyword[i]});
		}
		for (int i=0; i<4; i++){
			list.add(new Object[]{expected[0],errors[i]});
		}
		return list;
   } 
	// 构造函数，对变量进行初始化 
    public  IskeywordParaTest(int  result,  String  param )  {
        this .param  =  param;
        this .result  =  result;
   } 
	@Test
	public void testIsKeyword() {
	    assertEquals(result, analysis.isKeyword(param));
	}
}
