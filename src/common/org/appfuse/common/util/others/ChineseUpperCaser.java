package org.appfuse.common.util.others;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将阿拉伯数字转换成汉语大写数字的类
 * 
 * @author sitinspring
 * @since 2008-03-25
 * @date 2008-03-27
 */
public class ChineseUpperCaser{
    /**
     * 用于存储整数部分
     */
    private String integerPart;
    
    /**
     * 用于存储小数部分
     */
    private String floatPart;
    
    /**
     * 用于存储0-9大写的哈希表
     */
    private static final Map<Character,String> zerotoNineHt;
    
    /**
     * 用于存储拾佰仟大写的哈希表
     */
    private static final Map<Integer,String> thHuTenHt;
    
    /**
     * 静态构造函数，用于初始化zerotoNineHt和thHuTenHt
     */
    static{
        // 初始化zerotoNineHt
        zerotoNineHt=new Hashtable<Character,String>();        
        zerotoNineHt.put('0', "零");
        zerotoNineHt.put('1', "壹");
        zerotoNineHt.put('2', "贰");
        zerotoNineHt.put('3', "叁");
        zerotoNineHt.put('4', "肆");
        zerotoNineHt.put('5', "伍");
        zerotoNineHt.put('6', "陆");
        zerotoNineHt.put('7', "柒");
        zerotoNineHt.put('8', "捌");
        zerotoNineHt.put('9', "玖");
        
        // 初始化thHuTenHt
        thHuTenHt=new Hashtable<Integer,String>();
        thHuTenHt.put(0, "");
        thHuTenHt.put(1, "拾");
        thHuTenHt.put(2, "佰");
        thHuTenHt.put(3, "仟");
    }
    
    /**
     * 取得拾佰仟等单位
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
     * 由数字取得中国大写汉字
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
     * 取得万亿兆等单位
     * @param level
     * @return
     */
    private static String getUnitBy(int level){
        String retval="";
        
        for(int i=0;i<level;i++){
            retval+="萬";
        }
                
        // 把万万万变成兆
        retval=retval.replaceAll("萬{3}", "兆");
        
        // 把万万变成億
        retval=retval.replaceAll("萬{2}", "億");
        
        // 颠倒回来，因为刚才是替换的结果是兆》億》萬
        retval=reverseStr(retval);
    
        return retval;
    }
    
    /**
     * 得到逆序字符串
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
     * 构造函数
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(float number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * 构造函数
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(double number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * 构造函数
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(int number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * 构造函数
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(long number) throws NumberFormatException{
        this(String.valueOf(number));
    }
    
    /**
     * 构造函数
     * @param number
     * @throws NumberFormatException
     */
    public ChineseUpperCaser(String number) throws NumberFormatException{
        String formalNumber=formatNumber(number);
        
        // 劈分以给整数部分和小数部分赋值
        String[] arr=formalNumber.split("[.]");
        if(arr.length==2){
            // 有小数点
            integerPart=arr[0];
            floatPart=arr[1];
        }
        else{
            // 无小数点
            integerPart=arr[0];
        }
    }
    
    public String toString(){
        String retval="";
                
        if(integerPart!=null){
            // 取得整数部分的大写汉字表示
            retval+=parseIntegerPart();
        }
        
        if(floatPart!=null){
            // 取得小数部分的大写汉字表示
            retval+=parseFloatPart();
        }
        else{
            retval+="整";
        }
                
        return retval;
    }
    
    /**
     * 得到整数部分的汉字大写表示
     * @return
     */
    private String parseIntegerPart(){
        String retval="";
        
        // 将整数部分逆序，因为需要反向读取
        String reverseIntegerPart=reverseStr(integerPart);;
        
        // 将整数部分按四位分段
        Pattern p = Pattern.compile("\\d{4}",Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(reverseIntegerPart);
        StringBuffer sb = new StringBuffer();

        boolean result = m.find();
        while (result) {
            // 每找到四位放一个逗号
            m.appendReplacement(sb, m.group(0) + ",");
            result = m.find();
        }
        m.appendTail(sb);
        
        // 按逗号劈分，得到四位分组数据的数组
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
        
        // 最后的对零的修补
        if(retval.length()==0){
            retval="零";
        }
    
        return retval;
    }

    
    /**
     * 得到小数部分的汉字大写表示
     * @return
     */
    private String parseFloatPart(){
        String retval="点";
        
        for(int i=0;i<floatPart.length();i++){
            retval+=getCnNumberFrom(floatPart.charAt(i));
        }
        
        return retval;
    }
    
    /**
     * 对输入的字符串进行验证，如果不能转化为数字形式则抛出数字转化异常
     * ，注意这是一个运行时异常(非检查型异常)，程序不用显式捕获
     * @param number
     * @throws NumberFormatException
     */
    private String formatNumber(String number) throws NumberFormatException{        
        return (new BigDecimal(number)).toString();        
    }
    
    private static String getFommetedStr(String str){        
        // 将整数部分逆序，因为需要反向读取
        String reverseIntegerPart="";
        
        for(int i=str.length()-1;i>-1;i--){
            reverseIntegerPart+=str.charAt(i);
        }
        
        // 将整数部分按四位分段
        Pattern p = Pattern.compile("\\d{4}",Pattern.CASE_INSENSITIVE);

        Matcher m = p.matcher(reverseIntegerPart);
        StringBuffer sb = new StringBuffer();

        boolean result = m.find();
        while (result) {
            // 每找到四位放一个逗号
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
            System.out.println("阿拉伯数字等于："+str+" 大写汉字等于："+new ChineseUpperCaser(str));
        }
        
        new ChineseUpperCaser("12345.6789.123456").toString();
        */
        /*String number="10000000";
        for(int i=0;i<50;i++){
            number+="0";
            System.out.println("阿拉伯数字等于："+getFommetedStr(number)+" 大写汉字等于："+new CnUpperCaser(number));
        }    */
        
        /*System.out.println("------普通测试-------------------------------");
        String[] arr={"1","123","1234","12345","123456","1234567","12345678","123456789","123456789123456789","123456789123456789123456789","123456789123456789123456789","1234567891234567890123456789"};
        for(String str:arr){
            System.out.println("阿拉伯数字等于："+getFommetedStr(str)+" 大写汉字等于："+new CnUpperCaser(str));
        }
        */
        String number="1";
        for(int i=0;i<50;i++){            
            System.out.println("阿拉伯数字等于："+getFommetedStr(number)+" 大写汉字等于："+new ChineseUpperCaser(number));
            number+="0";
        }
        
        System.out.println("------零测试一-------------------------------");
        number="1";
        for(int i=0;i<50;i++){
            number+="0";
            System.out.println("阿拉伯数字等于："+getFommetedStr(number+"1")+" 大写汉字等于："+new ChineseUpperCaser(number+"1"));
        }
        
        System.out.println("------零测试二-------------------------------");
        number="1";
        for(int i=0;i<50;i++){
            number+="01";
            System.out.println("阿拉伯数字等于："+getFommetedStr(number)+" 大写汉字等于："+new ChineseUpperCaser(number));
        }
        
        System.out.println("------零测试三-------------------------------");
        String[] arr2={"1","101","1001","100101","1000100101","100001000100101","1000000100001000100101","10100100010000100000100000010"};
        for(String str:arr2){
            System.out.println("阿拉伯数字等于："+getFommetedStr(str)+" 大写汉字等于："+new ChineseUpperCaser(str));
        }
        
        System.out.println("------常规测试三-------------------------------");
        String[] arr3={"1.543524304302432","12.432423432","123.454235","1234","12345","123456","1234567","12345678","123456789","1234567891","12345678912","123456789123","1234567891234","12345678912345","123456789123456","1234567891234567","12345678912345678","123456789123456789","123456789123456789123456089123456789123456789123450780","0","00","000","0000","01","001","0001","00001","10","100","1000","10000","101","1001","10001","100001","1.23","21.234","243400031.233234","5400035.980","543.6545"};
        for(String str:arr3){
            System.out.println("阿拉伯数字等于："+str+" 大写汉字等于："+new ChineseUpperCaser(str));
        }
        
        System.out.println("------常规测试四-------------------------------");
        String[] arr4={"0","3.14159","101.3","10203040506070809","7897645","500000001000000","2435685","5345438976"};
        for(String str:arr4){
            System.out.println("阿拉伯数字等于："+str+" 大写汉字等于："+new ChineseUpperCaser(str));
        }
    }
}