package org.appfuse.common.util.others;

import java.util.ArrayList;
import java.util.List;

/**
 * ����λ�ֳ���ÿһ��
 * @author: sitinspring(junglesong@gmail.com)
 * @date: 2008-3-27
 */
public class CucSegment{
    /**
     * �ֶ��ַ�������
     */
    private List<CncChar> cnchars;
    
    /**
     * ��λ��������
     */
    private String unit;
    
    /**
     * ���캯��
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
     * ȡ�÷ֶ�����
     * @param isTail���Ƿ����һ��
     * @return
     */
    public String getString(boolean isTail){
        String retval="";
        
        for(CncChar cnChar:cnchars){
            retval+=cnChar.toString();
        }
        
        // ��ĩβ��һ�����滻��
        retval=retval.replaceAll("[��]+", "��");// ������滻��һ����
        
        
        retval=retval.replaceAll("(��+)($)", "$2");// ����ĩβ��ȥ��*/
        
        
        // ����������ѵ�λҲȥ����������ϵ�λ
        if(retval.length()>0){
            retval+=unit;
        }
        
        return retval;
    }
}
