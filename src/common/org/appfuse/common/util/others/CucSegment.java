package org.appfuse.common.util.others;

import java.util.ArrayList;
import java.util.List;

/**
 * 按四位分出的每一段
 * @author: sitinspring(junglesong@gmail.com)
 * @date: 2008-3-27
 */
public class CucSegment{
    /**
     * 分段字符串序列
     */
    private List<CncChar> cnchars;
    
    /**
     * 单位（兆亿万）
     */
    private String unit;
    
    /**
     * 构造函数
     * @param segment
     * @param unit
     */
    public CucSegment(String segment,String unit){
        this.unit=unit;
        
        cnchars=new ArrayList<CncChar>();
        for(int i=segment.length()-1;i>=0;i--){
            cnchars.add(new CncChar(segment.charAt(i),ChineseUpperCaser.getPieceUnitBy(i)));
        }
    }    
    
    /**
     * 取得分段文字
     * @param isTail：是否最后一段
     * @return
     */
    public String getString(boolean isTail){
        String retval="";
        
        for(CncChar cnChar:cnchars){
            retval+=cnChar.toString();
        }
        
        // 将末尾的一堆零替换掉
        retval=retval.replaceAll("[零]+", "零");// 多个零替换成一个零
        
        
        retval=retval.replaceAll("(零+)($)", "$2");// 零在末尾则去掉*/
        
        
        // 若无数字则把单位也去掉，否则加上单位
        if(retval.length()>0){
            retval+=unit;
        }
        
        return retval;
    }
}
