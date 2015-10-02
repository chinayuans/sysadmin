/*BEGIN OCO COPYRIGHT
 *************************************************************************
 *
 * IBM Confidential
 * OCO Source Materials
 * 5724-L01, 5655-N53, 5724-I82, 5655-R15
 * (C) Copyright IBM Corporation 2006.
 * The source code for this program is not published or otherwise
 * divested of its trade secrets, irrespective of what has been
 * deposited with the U.S. Copyright Office.
 *
 **************************************************************************
 *END OCO COPYRIGHT
 */
package org.appfuse.common.util.serialize;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class SerializeObject
{
    public static void main(String[] args) throws IOException
    {
//        serializeAfterCompressed();
//        serializeNormally();
          serializeList();
          serializeListAfterCompressed();
    }
    
    private static void serializeAfterCompressed() throws IOException {
        HelloWorld hello = new HelloWorld();
        hello.setName("hello,World");
        
        byte[] outBytes=SerialUIDConvertUtil.serializeObject(hello);
        
        FileOutputStream out= new FileOutputStream("./lib/helloWorld1_compressed.data");
        out.write(outBytes);
        out.close();
    }
    
    private static void serializeNormally() throws IOException {
        HelloWorld hello = new HelloWorld();
        hello.setName("hello,World");
        
        ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("./lib/helloWorld1_normal.data"));
        out.writeObject(hello);
        out.close();
    }
    
    private static void serializeList() throws IOException {
//        HelloWorld hello1 = new HelloWorld();
//        hello1.setName("hello1,World");
//        
//        HelloWorld hello2 = new HelloWorld();
//        hello2.setName("hello2,World");
//        
        List list=new ArrayList();
        for (int i = 0; i < 100; i++) {
            HelloWorld hello=new HelloWorld();
            hello.setName("hello,World:"+i);
            list.add(hello);
        }
        
//        list.add(hello1);
//        list.add(hello2);
        
        ObjectOutputStream out= new ObjectOutputStream(new FileOutputStream("./lib/helloWorld1_list.data"));
        out.writeObject(list);
        out.close();
    }
    
    private static void serializeListAfterCompressed() throws IOException {
        List list=new ArrayList();
        for (int i = 0; i < 100; i++) {
            HelloWorld hello=new HelloWorld();
            hello.setName("hello,World:"+i);
            list.add(hello);
        }
        
        byte[] outBytes=SerialUIDConvertUtil.serializeObject(list);
        
        FileOutputStream out= new FileOutputStream("./lib/helloWorld1_list_compressed.data");
        out.write(outBytes);
        out.close();
    }
}
