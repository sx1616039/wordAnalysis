package one;

public class Word {

	private int rowNum; //��¼�������ڵ��к�
	private String word;//��¼���ʱ���
	
	public Word(int rowNum, String word){
		this.rowNum = rowNum;
		this.word = word;
	}

	public int getRow() {
		return rowNum;
	}

	public void setRow(int errRow) {
		this.rowNum = errRow;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String errStr) {
		this.word = errStr;
	}
	
	

}
