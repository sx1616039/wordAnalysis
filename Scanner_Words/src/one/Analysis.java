package one;

import java.util.*;

/**1--20��Ϊ�ؼ��֣���ֱ�����±��ʾ,i+1�����������,21--40Ϊ�����������±꣺i+21�����������,
 * 41--50Ϊ�����ֱ���±꣺i+41�����������;������û��Լ�����ı�ʶ�������������Ϊ51������ǳ�����
 * �����������52������Ǵ����������Ϊ0.
 */
public class Analysis {
	private String keyword[]={"int","long","char","if","else","for","while","return","break","continue","switch","case","default","float","double","void","struct","static","do","short"}; 
	private String operator[]={"+","-","*","/","%","=",">","<","!","==","!=",">=","<=","++","--","&","&&","||","[","]"};
	private String delimeter[]={",",";","(",")","{","}","\'","\"",":","#"};

	public Analysis(){
		
	}
	
	//�ж�ch�Ƿ������ֵĺ���
	public boolean isDigit(char ch){
		if(ch >='0' && ch <='9'){
			return true;
		}else{
			return false;
		}
	}
	
	//�ж�ch�Ƿ�����ĸ�ĺ���
	public boolean isLetter(char ch){
		if((ch>='a'&&ch<='z')||(ch>='A'&&ch<='Z')){
			return true;
		}else{
			return false;
		}
	}
	
	//�ж�str�Ƿ���һ���������ַ���ɵĺϷ������
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
	
	//�ж�str�Ƿ��ǺϷ��������
	public int isOperator(String str){
		int i;
		for(i=0; i<operator.length; i++){
			if(str.equals(operator[i]))
				break;
		}
		if(i<operator.length){
			return i+21; //�����������Ӧ�Ļ�����
		}else{
			return 0; //����
		}
	}
	
   //�ж�str�Ƿ��ǺϷ��������
	public int isDelimeter(String str){
		int i;
		for(i=0; i<delimeter.length; i++){
			if(str.equals(delimeter[i]))
				break;
		}
		if(i<delimeter.length){
			return i+41; //���ؽ����Ӧ�Ļ�����
		}else{
			return 0; //����
		}
	}
	
    //�ж�str�Ƿ��ǺϷ��������
	public int isKeyword(String str){
		int i;
		for(i=0; i<keyword.length; i++){
			if(str.equals(keyword[i]))
				break;
		}
		if(i<keyword.length){
			return i+1; //���ؽ����Ӧ�Ļ�����
		}else{
			return 0; //����
		}
	}
	
	//�ж�str�Ƿ��ǺϷ��ı�ʶ��
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
	
	//Ԥ�����������˿ո����Ҫ�༭���ַ���
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
	
	//�ֽⵥ�ʵĺ��������ַ����ֽ��һ�������ʣ������һ�����鵱��
	ArrayList<Word> divide(String ts){
		ArrayList<Word> al = new ArrayList<Word>();
		String str= "";
		char ch;
		int i;
		int rowNum = 1;
		for(i=0; i<ts.length(); i++){
			ch = ts.charAt(i);
			if(i==0&&ch==' ')
				continue; //���˿�ʼ���Ŀո�
			
			if(ch==' '){
				if(str!=""){ //ȥ�����з�������ܳ���str=""����������ԣ��������Ҫ�ܹ�ʶ�������
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
		//�������һ���������ַ��򵥴ʽ��޷����뵽al�У���Ϊ���û���ַ��ˡ�
		if(str!=""){
			al.add(new Word(rowNum, str));
		}
		return al;
		
	}
	
	//�ж��ַ��������ִ��������ַ�������һ���ַ���
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
	
	//�ж����ִ��Ƿ�Ϸ��ĺ���,����������롣
	int checkDigit(String str){
		int i;
		char ch;
		for(i=0; i<str.length(); i++){
			ch = str.charAt(i);
			if(ch>'9'||ch<'0')
				break;
		}
		
		if(i < str.length()){
			return 0; //����Ļ�����
		}else{
			return 52; //�����Ļ�����
		}
	}
	
	//�жϵ����ַ������ͣ����ػ�����
	int checkChar(String str){
		if(isOperator(str)!=0){
			return isOperator(str);
		}else if(isDelimeter(str)!=0){
			return isDelimeter(str);
		}else if(isIdent(str)){
			return 51; //��ʶ����Ӧ�Ļ�����
		}else{
			return 0;
		}
	}
	
	//�ж��ַ��������ͣ����ػ�����
	int checkString(String str){
		if(isOperator(str)!=0){
			return isOperator(str);
		}else if(isKeyword(str)!=0){
			return isKeyword(str);
		}else if(isIdent(str)){
			return 51; //��ʶ����Ӧ�Ļ�����
		}else{
			return 0;
		}
	}
	
	
	

}
