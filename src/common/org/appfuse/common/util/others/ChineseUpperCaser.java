package org.appfuse.common.util.others;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������������ת���ɺ����д���ֵ���
 * 
 * @author sitinspring
 * @since 2008-03-25
 * @date 2008-03-27
 */
public class ChineseUpperCaser{
    /**
     * ���ڴ洢��������
     */
    private String integerPart;
    
    /**
     * ���ڴ洢С������
     */
    private String floatPart;
    
    /**
     * ���ڴ洢0-9��д�Ĺ�ϣ��
     */
    private static final Map<Character,String> zerotoNineHt;
    
    /**
     * ���ڴ洢ʰ��Ǫ��д�Ĺ�ϣ��
     */
    private static final Map<Integer,String> thHuTenHt;
    
    /**
     * ��̬���캯�������ڳ�ʼ��zerotoNineHt��thHuTenHt
     */
    static{
        // ��ʼ��zerotoNineHt
        zerotoNineHt=new Hashtable<Character,String>();        
        zerotoNineHt.put('0', "��");
        zerotoNineHt.put('1', "Ҽ");
        zerotoNineHt.put('2', "��");
        zerotoNineHt.put('3', "��");
        zerotoNineHt.put('4', "��");
        zerotoNineHt.put('5', "��");
        zerotoNineHt.put('6', "½");
        zerotoNineHt.put('7', "��");
        zerotoNineHt.put('8', "��");
        zerotoNineHt.put('9', "��");
        
        // ��ʼ��thHuTenHt
        thHuTenHt=new Hashtable<Integer,String>();
        thHuTenHt.put(0, "");
        thHuTenHt.put(1, "ʰ");
        thHuTenHt.put(2, "��");
        thHuTenHt.put(3, "Ǫ");
    }
    
    /**
     * ȡ��ʰ��Ǫ�ȵ�λ
     * @param level
     * @return
     */
    public static String getPieceUnitBy(int index){
        if(thHuTenHt.containsKey(index)){
            return thHuTenHt.get(index);
        }
        else{
            return "";
        }
    }
    
    /**
     * ������ȡ���й���д����
     * @param number
     * @return
     */
    public static String getCnNumberFrom(char number){
        if(zerotoNineHt.containsKey(number)){
            return zerotoNineHt.get(number);
        }
        else{
            return "";
        }
    }
    
    /**
     * ȡ�������׵ȵ�λ
     * @param level
     * @return
     */
    private static String getUnitBy(int level){
        String retval="";
        
        for(int i=0;i<level;i++){
            retval+="�f";
        }
                
        // ������������
        retval=retval.replaceAll("�f{3}", "��");
        
        // �������Ƀ|
        retval=retval.replaceAll("�f{2}", "�|");
        
        // �ߵ���������Ϊ�ղ����滻�Ľ�����ס��|���f
        retval=reverseStr(retval);
    
        return retval;
    }
    
    /**
     * �õ������ַ���
     * @param str
     * @return
     */
    private static String reverseStr(String str){
        String retval="";
        
        for(int i=str.length()-1;i>-1;i--){
            retval+=str.charAt(i);
        }
    
        return retval;
    }
    
    /**
     * ���캯��
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(float number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * ���캯��
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(double number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * ���캯��
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(int number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * ���캯��
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(long number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * ���캯��
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(String number) throws NumberFormatException{
        String formalNumber=formatNumber(number);
        
        // �����Ը��������ֺ�С�����ָ�ֵ
        String[] arr=formalNumber.split("[.]");
        if(arr.length==2){
            // ��С����
            integerPart=arr[0];
            floatPart=arr[1];
        }
        else{
            // ��С����
            integerPart=arr[0];
        }
    }
    
    public String toString(){
        String retval="";
                
        if(integerPart!=null){
            // ȡ���������ֵĴ�д���ֱ�ʾ
            retval+=parseIntegerPart();
        }
        
        if(floatPart!=null){
            // ȡ��С�����ֵĴ�д���ֱ�ʾ
            retval+=parseFloatPart();
        }
        else{
            retval+="��";
        }
                
        return retval;
    }
    
    /**
     * �õ��������ֵĺ��ִ�д��ʾ
     * @return
     */
    private String parseIntegerPart(){
        String retval="";
        
        // ����������������Ϊ��Ҫ�����ȡ
        String reverseIntegerPart=reverseStr(integerPart);;
        
        // ���������ְ���λ�ֶ�
        Pattern p = Pattern.compile("\\d{4}",Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(reverseIntegerPart);
        StringBuffer sb = new StringBuffer();

        boolean result = m.find();
        while (result) {
            // ÿ�ҵ���λ��һ������
            m.appendReplacement(sb, m.group(0) + ",");
            result = m.find();
        }
        m.appendTail(sb);
        
        // ���������֣��õ���λ�������ݵ�����
        String[] arr=sb.toString().split(",");    
        List<CucSegment> list=new ArrayList<CucSegment>();

        for(int i=arr.length-1;i>=0;i--){
            list.add(new CucSegment(arr[i],getUnitBy(i)));
        }
        
        int n=list.size();
        for(int i=0;i<n;i++){
            CucSegment cnPiece=list.get(i);
            retval+=cnPiece.getString(i==n-1);
        }
        
        // ���Ķ�����޲�
        if(retval.length()==0){
            retval="��";
        }
    
        return retval;
    }

    
    /**
     * �õ�С�����ֵĺ��ִ�д��ʾ
     * @return
     */
    private String parseFloatPart(){
        String retval="��";
        
        for(int i=0;i<floatPart.length();i++){
            retval+=getCnNumberFrom(floatPart.charAt(i));
        }
        
        return retval;
    }
    
    /**
     * ��������ַ���������֤���������ת��Ϊ������ʽ���׳�����ת���쳣
     * ��ע������һ������ʱ�쳣(�Ǽ�����쳣)����������ʽ����
     * @param number
     * @throws NumberFormatException
     */
    private String formatNumber(String number) throws NumberFormatException{        
        return (new BigDecimal(number)).toString();        
    }
    
    private static String getFommetedStr(String str){        
        // ����������������Ϊ��Ҫ�����ȡ
        String reverseIntegerPart="";
        
        for(int i=str.length()-1;i>-1;i--){
            reverseIntegerPart+=str.charAt(i);
        }
        
        // ���������ְ���λ�ֶ�
        Pattern p = Pattern.compile("\\d{4}",Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(reverseIntegerPart);
        StringBuffer sb = new StringBuffer();

        boolean result = m.find();
        while (result) {
            // ÿ�ҵ���λ��һ������
            m.appendReplacement(sb, m.group(0) + ",");
            result = m.find();
        }
        m.appendTail(sb);
        
        reverseIntegerPart=sb.toString();
        
        String retval="";
        
        for(int i=reverseIntegerPart.length()-1;i>-1;i--){
            retval+=reverseIntegerPart.charAt(i);
        }
        
        return retval;
    }
    
    public static void main(String[] args){
        
        
        //new ChineseUpperCaser("123456789123456789123456089123456789123456789123450780").toString();
        /*String[] arr={"1.543524304302432","12.432423432","123.454235","1234","12345","123456","1234567","12345678","123456789","1234567891","12345678912","123456789123","1234567891234","12345678912345","123456789123456","1234567891234567","12345678912345678","123456789123456789","123456789123456789123456089123456789123456789123450780","0","00","000","0000","01","001","0001","00001","10","100","1000","10000","101","1001","10001","100001","1.23","21.234","243400031.233234","5400035.980","543.6545"};
        //String[] arr={"0","00","000","0000","01","001","0001","00001","10","100","1000","10000","101","1001","10001","100001"};
        //String[] arr={"1.23","21.234","243400031.233234","5400035.980","543.6545"};
        for(String str:arr){
            System.out.println("���������ֵ��ڣ�"+str+" ��д���ֵ��ڣ�"+new ChineseUpperCaser(str));
        }
        
        new ChineseUpperCaser("12345.6789.123456").toString();
        */
        /*String number="10000000";
        for(int i=0;i<50;i++){
            number+="0";
            System.out.println("���������ֵ��ڣ�"+getFommetedStr(number)+" ��д���ֵ��ڣ�"+new CnUpperCaser(number));
        }    */
        
        /*System.out.println("------��ͨ����-------------------------------");
        String[] arr={"1","123","1234","12345","123456","1234567","12345678","123456789","123456789123456789","123456789123456789123456789","123456789123456789123456789","1234567891234567890123456789"};
        for(String str:arr){
            System.out.println("���������ֵ��ڣ�"+getFommetedStr(str)+" ��д���ֵ��ڣ�"+new CnUpperCaser(str));
        }
        */
        String number="1";
        for(int i=0;i<50;i++){            
            System.out.println("���������ֵ��ڣ�"+getFommetedStr(number)+" ��д���ֵ��ڣ�"+new ChineseUpperCaser(number));
            number+="0";
        }
        
        System.out.println("------�����һ-------------------------------");
        number="1";
        for(int i=0;i<50;i++){
            number+="0";
            System.out.println("���������ֵ��ڣ�"+getFommetedStr(number+"1")+" ��д���ֵ��ڣ�"+new ChineseUpperCaser(number+"1"));
        }
        
        System.out.println("------����Զ�-------------------------------");
        number="1";
        for(int i=0;i<50;i++){
            number+="01";
            System.out.println("���������ֵ��ڣ�"+getFommetedStr(number)+" ��д���ֵ��ڣ�"+new ChineseUpperCaser(number));
        }
        
        System.out.println("------�������-------------------------------");
        String[] arr2={"1","101","1001","100101","1000100101","100001000100101","1000000100001000100101","10100100010000100000100000010"};
        for(String str:arr2){
            System.out.println("���������ֵ��ڣ�"+getFommetedStr(str)+" ��д���ֵ��ڣ�"+new ChineseUpperCaser(str));
        }
        
        System.out.println("------���������-------------------------------");
        String[] arr3={"1.543524304302432","12.432423432","123.454235","1234","12345","123456","1234567","12345678","123456789","1234567891","12345678912","123456789123","1234567891234","12345678912345","123456789123456","1234567891234567","12345678912345678","123456789123456789","123456789123456789123456089123456789123456789123450780","0","00","000","0000","01","001","0001","00001","10","100","1000","10000","101","1001","10001","100001","1.23","21.234","243400031.233234","5400035.980","543.6545"};
        for(String str:arr3){
            System.out.println("���������ֵ��ڣ�"+str+" ��д���ֵ��ڣ�"+new ChineseUpperCaser(str));
        }
        
        System.out.println("------���������-------------------------------");
        String[] arr4={"0","3.14159","101.3","10203040506070809","7897645","500000001000000","2435685","5345438976"};
        for(String str:arr4){
            System.out.println("���������ֵ��ڣ�"+str+" ��д���ֵ��ڣ�"+new ChineseUpperCaser(str));
        }
    }
}