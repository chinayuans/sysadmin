package org.appfuse.common.util.jvm;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class StartAnotherJVM
{
   public static void main1(String[] args)
        throws IOException
    {
//        String cmdName = "c:/run.bat";
//        String cmdName = "ping  9.181.85.162";
//        String cmdName = "c:/6010.bat";
//        String cmdName = "c:/6010_bal.bat";
//        String cmdName = "c:\\WBI602\\java\\bin\\java -classpath  c:/1.jar; HelloWorld";
       
//       String cmdName = "C:/WBI602/java/bin/java -classpath c:/6010.jar;C:/lib/ant.jar;C:/lib/emf.jar;C:/lib/sib.common.jar;C:/lib/bootstrap.jar;C:/lib/j2ee.jar;C:/lib/ffdc.jar;C:/lib/ras.jar;C:/lib/runtime.jar;C:/lib/sca.jar;C:/lib/util.jar;C:/lib/utils.jar;C:/lib/wsdl4j.jar;C:/lib/wsexception.jar;C:/lib/deployutils.jar;C:/lib/wasproduct.jar;C:/lib/recoveryMBean.jar;C:/lib/wbiBOS.jar;C:/lib/wbiCore.jar;C:/lib/xmlsecurity.jar;C:/lib/scdl-wsdl.jar;C:/lib/scdl-java.jar;C:/lib/scdl.jar;C:/lib/sca-webservice.jar;C:/lib/sca-style.jar;C:/lib/sca-managed.jar;C:/lib/sca-jms.jar;C:/lib/sca-java.jar;C:/lib/sca-j2c.jar;C:/lib/sca-ejb.jar;C:/lib/sca-deploy.jar;C:/lib/sca-container.jar;C:/lib/sca-common.jar;C:/lib/sca-async.jar;C:/lib/sas.jar;C:/lib/corona-wsdl.jar MainEntry";
        
        String cmdName = "java  MainEntry";
        String[] parameters=new String[]{"classpath=c:/6010.jar;C:/lib/ant.jar;C:/lib/emf.jar;C:/lib/sib.common.jar;C:/lib/bootstrap.jar;C:/lib/j2ee.jar;C:/lib/ffdc.jar;C:/lib/ras.jar;C:/lib/runtime.jar;C:/lib/sca.jar;C:/lib/util.jar;C:/lib/utils.jar;C:/lib/wsdl4j.jar;C:/lib/wsexception.jar;C:/lib/deployutils.jar;C:/lib/wasproduct.jar;C:/lib/recoveryMBean.jar;C:/lib/wbiBOS.jar;C:/lib/wbiCore.jar;C:/lib/xmlsecurity.jar;C:/lib/scdl-wsdl.jar;C:/lib/scdl-java.jar;C:/lib/scdl.jar;C:/lib/sca-webservice.jar;C:/lib/sca-style.jar;C:/lib/sca-managed.jar;C:/lib/sca-jms.jar;C:/lib/sca-java.jar;C:/lib/sca-j2c.jar;C:/lib/sca-ejb.jar;C:/lib/sca-deploy.jar;C:/lib/sca-container.jar;C:/lib/sca-common.jar;C:/lib/sca-async.jar;C:/lib/sas.jar;C:/lib/corona-wsdl.jar"};
        File path=new File("C:/WBI602/java/bin");
        try {
            Process prc = Runtime.getRuntime().exec(cmdName,parameters,path);
            
//            Process prc = Runtime.getRuntime().exec(cmdName);
            
            String line;
            BufferedReader prcout = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(prc.getInputStream())));
            while ((line = prcout.readLine()) != null) {
                System.out.println(line);
            }
            prcout.close();
            prc.waitFor();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
   
   
   public static void main(String[] args)
        throws IOException
    {

        String cmdName = "c:/WBI602/java/bin/java -classpath  c:/1.jar; HelloWorld";
        try {
            Process prc = Runtime.getRuntime().exec(cmdName);

            String line;
            BufferedReader prcout = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(prc.getInputStream())));
            while ((line = prcout.readLine()) != null) {
                System.out.println(line);
            }
            prcout.close();
            prc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    public static void main3(String[] args)
        throws IOException
    {
        String cmdName = "java  HelloWorld";
        String[] parameters = new String[] { "classpath='.;c:\\1.jar;'" };
        File path = new File("c:\\WBI602\\java\\bin");
        try {
            Process prc = Runtime.getRuntime().exec(cmdName, parameters, path);

            String line;
            BufferedReader prcout = new BufferedReader(new InputStreamReader(
                    new BufferedInputStream(prc.getInputStream())));
            while ((line = prcout.readLine()) != null) {
                System.out.println(line);
            }
            prcout.close();
            prc.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
