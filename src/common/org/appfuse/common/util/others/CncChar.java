package org.appfuse.common.util.others;

/**
 * ÿһλ����
 * @author: sitinspring(junglesong@gmail.com)
 * @date: 2008-3-27
 */
public class CncChar{
    /**
     * ��������ĸ
     */
    private char arabicNumber;
    
    /**
     * ��Ӧ�ĺ��ִ�д����
     */
    private String chineseNumber;
    
    /**
     * ��λ��ʰ��Ǫ��
     */
    private String unit;
    
    /**
     * ���캯��
     * @param arabicNumber
     * @param unit
     */
    public CncChar(char arabicNumber,String unit){
        this.arabicNumber=arabicNumber;
        this.chineseNumber=ChineseUpperCaser.getCnNumberFrom(arabicNumber);
        this.unit=unit;
    }
    
    public String toString(){
        if(arabicNumber=='0'){
            return chineseNumber;
        }
        else{
            return chineseNumber+unit;
        }
    }
}
