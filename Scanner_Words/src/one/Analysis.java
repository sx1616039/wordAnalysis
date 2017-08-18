package one;

import java.util.*;

/**1--20号为关键字，可直接用下标表示,i+1就是其机内码,21--40为运算符，间接下标：i+21就是其机内码,
 * 41--50为界符，直接下标：i+41就是其机内码;如果是用户自己定义的标识符，则其机内码为51；如果是常数，
 * 则其机内码是52；如果是错误，则机内码为0.
 */
public class Analysis {
	private String keyword[]={"int","long","char","if","else","for","while","return","break","continue","switch","case","default","float","double","void","struct","static","do","short"}; 
	private String operator[]={"+","-","*","/","%","=",">","<","!","==","!=",">=","<=","++","--","&","&&","||","[","]"};
	private String delimeter[]={",",";","(",")","{","}","\'","\"",":","#"};

	public Analysis(){
		
	}
	
	//判断ch是否是数字的函数
	public boolean isDigit(char ch){
		if(ch >='0' && ch <='9'){
			return true;
		}else{
			return false;
		}
	}
	
	//判断ch是否是字母的函数
	public boolean isLetter(char ch){
		if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
			return true;
		}else{
			return false;
		}
	}
	
	//判断str是否是一个由两个字符组成的合法运算符
	public boolean isSpeOperator(String str, char ch){
		char lc;
		int flag = 0;
		if(str.length() > 1 ||str.length() == 0){
			return false;
		}else{
			lc = str.charAt(str.length()-1);
			if(ch =='='&&(lc =='='||lc=='>'||lc=='<'||lc=='!')){
				
			}else if(ch == '+'&& lc== '+'){
				
			}else if(ch == '-'&& lc== '-'){
				
			}else if(ch== '&'&& lc=='&'){
				
			}else if(ch=='|'&& lc =='|'){
				
			}else{
				return false;
			}
			return true;
		}
	}
	
	//判断str是否是合法的运算符
	public int isOperator(String str){
		int i;
		for(i=0; i<operator.length; i++){
			if(str.equals(operator[i]))
				break;
		}
		if(i<operator.length){
			return i+21; //返回运算符对应的机内码
		}else{
			return 0; //错误
		}
	}
	
   //判断str是否是合法的运算符
	public int isDelimeter(String str){
		int i;
		for(i=0; i<delimeter.length; i++){
			if(str.equals(delimeter[i]))
				break;
		}
		if(i<delimeter.length){
			return i+41; //返回界符对应的机内码
		}else{
			return 0; //错误
		}
	}
	
    //判断str是否是合法的运算符
	public int isKeyword(String str){
		int i;
		for(i=0; i<keyword.length; i++){
			if(str.equals(keyword[i]))
				break;
		}
		if(i<keyword.length){
			return i+1; //返回界符对应的机内码
		}else{
			return 0; //错误
		}
	}
	
	//判断str是否是合法的标识符
	public boolean isIdent(String str){
		char ch;
		int i;
		for(i=0; i<str.length(); i++){
			ch = str.charAt(i);
			if((i==0&&!isLetter(ch))||(!isDigit(ch)&&!isLetter(ch))){
				break;
			}
		}
		
		if(i < str.length()){
			return false;
		}else{
			return true;
		}
	}
	
	//预处理函数，过滤空格和主要编辑性字符。
	public String preTreat(String ss){
		String ts ="";
		int i;
		char ch, nc;
		for(i=0; i<ss.length()-1; i++){
			ch = ss.charAt(i);
			nc = ss.charAt(i+1);
			if(ch == '\n'){
				ch = '$';
				ts = ts + ch;
			}
			else if(ch ==' '||ch=='\r'||ch=='\t'){
				if(nc ==' '||nc=='\r'||nc=='\t'){
					continue;
				}else{
					ch = ' ';
					ts = ts + ch;
				}
			}else{
				ts = ts + ch;
			}
		}
		
		ch = ss.charAt(ss.length()-1);
		if(ch !=' '&&ch!='\r'&&ch!='\n'&&ch!='\t'){
			ts = ts + ch;
		}
		
		return ts;
	}
	
	//分解单词的函数，将字符串分解成一个个单词，存放在一个数组当中
	ArrayList<Word> divide(String ts){
		ArrayList<Word> al = new ArrayList<Word>();
		String str= "";
		char ch;
		int i;
		int rowNum = 1;
		for(i=0; i<ts.length(); i++){
			ch = ts.charAt(i);
			if(i==0&&ch==' ')
				continue; //过滤开始处的空格
			
			if(ch==' '){
				if(str!=""){ //去除换行符过后可能出现str=""的情况。所以，这里必须要能够识别出来。
					al.add(new Word(rowNum, str));
					str = "";
				}else{
					continue;
				}
			}else if(isDigit(ch)||isLetter(ch)){
				if(str==""||isDigit(str.charAt(str.length()-1))||isLetter(str.charAt(str.length()-1))){
					str = str + ch;
				}else{
					al.add(new Word(rowNum, str));
					str = "";
					str=str + ch;
				}	
				
			}else{
				
				if(isSpeOperator(str, ch)){
					str = str + ch;
				}
				else{
					if(str==""&&ch!='$'){
						str = str + ch;
					}else if(str==""&&ch=='$'){
						rowNum++;
					}
					else{
						al.add(new Word(rowNum, str));
						str = "";
						if(ch!='$'){
							str=str + ch;
						}else{
							rowNum++;
						}
						
					}
					
				}	
			}
			
		}
		//但是最后一个单独的字符或单词将无法加入到al中，因为其后没有字符了。
		if(str!=""){
			al.add(new Word(rowNum, str));
		}
		return al;
		
	}
	
	//判断字符串是数字串，单个字符，还是一个字符串
	int check(String st){
		char ch;
		
		ch = st.charAt(0);
		if(ch>='0'&&ch<='9'){
			return 1;
		}
		if(st.length() == 1){
			return 2;
		}else{
			return 3;
		}
		
	}
	
	//判断数字串是否合法的函数,返回其机内码。
	int checkDigit(String str){
		int i;
		char ch;
		for(i=0; i<str.length(); i++){
			ch = str.charAt(i);
			if(ch>'9'||ch<'0')
				break;
		}
		
		if(i < str.length()){
			return 0; //错误的机内码
		}else{
			return 52; //常数的机内码
		}
	}
	
	//判断单个字符的类型，返回机内码
	int checkChar(String str){
		if(isOperator(str)!=0){
			return isOperator(str);
		}else if(isDelimeter(str)!=0){
			return isDelimeter(str);
		}else if(isIdent(str)){
			return 51; //标识符对应的机内码
		}else{
			return 0;
		}
	}
	
	//判断字符串的类型，返回机内码
	int checkString(String str){
		if(isOperator(str)!=0){
			return isOperator(str);
		}else if(isKeyword(str)!=0){
			return isKeyword(str);
		}else if(isIdent(str)){
			return 51; //标识符对应的机内码
		}else{
			return 0;
		}
	}
	
	
	

}
