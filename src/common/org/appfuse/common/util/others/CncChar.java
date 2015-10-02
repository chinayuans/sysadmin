package org.appfuse.common.util.others;

/**
 * 每一位数字
 * @author: sitinspring(junglesong@gmail.com)
 * @date: 2008-3-27
 */
public class CncChar{
    /**
     * 阿拉伯字母
     */
    private char arabicNumber;
    
    /**
     * 对应的汉字大写数字
     */
    private String chineseNumber;
    
    /**
     * 单位（拾佰仟）
     */
    private String unit;
    
    /**
     * 构造函数
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
