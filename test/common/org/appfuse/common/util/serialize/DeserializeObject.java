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


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DeserializeObject
{
    public static void main(String[] args)
        throws Exception
    {
        //        convertCompressedSerializedObject();
        //        
        //        System.out.println("=====================");
        //        
        //        convertNormalSerializedObject();

        convertNormalSerializedList();
        convertCompressedSerializedList();
    }


    private static void convertCompressedSerializedObject()
        throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream in = new FileInputStream("./lib/helloWorld1_compressed.data");

        byte[] readBytes = new byte[1];
        int index = 0;
        boolean NOTEOF = true;
        while (NOTEOF) {
            byte AByteRead = (byte) in.read();
            if (AByteRead == -1) {
                NOTEOF = true;
                break;
            }

            if (index + 1 > readBytes.length) {
                int size_new = (readBytes.length * 3) / 2 + 1; //增长因子。
                byte[] readBytes_new = new byte[size_new];

                System.arraycopy(readBytes, 0, readBytes_new, 0, readBytes.length);
                readBytes_new[index] = AByteRead;
                readBytes = readBytes_new;
            }
            else {
                readBytes[index] = AByteRead;
            }

            index++;
        }
        in.close();

        byte[] FileBytes = new byte[index];
        System.arraycopy(readBytes, 0, FileBytes, 0, FileBytes.length);

        HelloWorld hello = (HelloWorld) SerialUIDConvertUtil
            .convertCompressedSerializedObject(FileBytes);

        System.out.println("name:" + hello.getName());
        System.out.println("property:" + hello.getProperty());
    }


    private static void convertNormalSerializedObject()
        throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream in = new FileInputStream("./lib/helloWorld1_normal.data");

        byte[] FileBytes = SerialUIDConvertUtil.getBytesFromStream(in);

        HelloWorld hello = (HelloWorld) SerialUIDConvertUtil
            .convertNormallySerializedObject(FileBytes);

        System.out.println("name:" + hello.getName());
        System.out.println("property:" + hello.getProperty());
    }


    private static void convertNormalSerializedList()
        throws FileNotFoundException, IOException, ClassNotFoundException
    {
        FileInputStream in = new FileInputStream("./lib/helloWorld1_list.data");

        byte[] FileBytes = SerialUIDConvertUtil.getBytesFromStream(in);
        
        System.out.println();
        System.out.println("normal filebytes size:"+FileBytes.length);

        List list = (ArrayList) SerialUIDConvertUtil
            .convertNormallySerializedObject(FileBytes);
        for (int i = 0; i < list.size(); i++) {

            HelloWorld hello = (HelloWorld) list.get(i);
            System.out.println("name:" + hello.getName());
            System.out.println("property:" + hello.getProperty());
        }

    }
    
    private static void convertCompressedSerializedList()
    throws FileNotFoundException, IOException, ClassNotFoundException
  {
    FileInputStream in = new FileInputStream("./lib/helloWorld1_list_compressed.data");

    byte[] FileBytes = SerialUIDConvertUtil.getBytesFromStream(in);

    System.out.println();
    System.out.println("compressed filebytes size:"+FileBytes.length);
    List list = (ArrayList) SerialUIDConvertUtil.convertCompressedSerializedObject(FileBytes);
    for (int i = 0; i < list.size(); i++) {
        HelloWorld hello = (HelloWorld) list.get(i);
        System.out.println("name:" + hello.getName());
        System.out.println("property:" + hello.getProperty());
    }

}

}
