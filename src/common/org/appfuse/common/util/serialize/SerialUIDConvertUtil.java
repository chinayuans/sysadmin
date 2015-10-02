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


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;


public class SerialUIDConvertUtil
{

    /**
     * 
     * @param compressedBytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object convertCompressedSerializedObject(byte[] compressedBytes)
        throws IOException, ClassNotFoundException
    {
        if ((compressedBytes == null) || (compressedBytes.length == 0)) {
            return null;
        }

        ByteArrayInputStream inbytes = new ByteArrayInputStream(compressedBytes);
        InflaterInputStream inflating = new InflaterInputStream(inbytes);

        ObjectInputStream in = new ObjectInputStream(inflating);

        Object return_object = null;
        try {
            return_object = in.readObject();
        } catch (InvalidClassException e) {

            //�Ƚ�ѹ���ݡ�
            ByteArrayInputStream inbytes2 = new ByteArrayInputStream(compressedBytes);
            InflaterInputStream inflating2 = new InflaterInputStream(inbytes2);

            //���������������
            byte[] normalBytes = getBytesFromStream(inflating2);

            return_object = convertSerialID(e, normalBytes);
        }

        in.close();
        return return_object;
    }


    public static Object convertNormallySerializedObject(byte[] a_bytes)
        throws IOException, ClassNotFoundException
    {
        if ((a_bytes == null) || (a_bytes.length == 0)) {
            return null;
        }

        ByteArrayInputStream inbytes = new ByteArrayInputStream(a_bytes);
        ObjectInputStream in = new ObjectInputStream(inbytes);

        Object return_object = null;
        try {
            return_object = in.readObject();
        } catch (InvalidClassException e) {

            ByteArrayInputStream inbytes2 = new ByteArrayInputStream(a_bytes);

            //���������������            
            byte[] normalBytes = getBytesFromStream(inbytes2);

            return_object = convertSerialID(e, normalBytes);
        }

        in.close();
        return return_object;
    }


    /**
     * compress and serialize the object
     * @param serializedObject
     * @throws IOException
     * @return byte[] serialized byte array
     */
    public static byte[] serializeObject(Object serializedObject)
        throws IOException
    {
        Deflater deflater = new Deflater();
        deflater.setStrategy(Deflater.DEFAULT_STRATEGY);
        deflater.setLevel(Deflater.BEST_COMPRESSION);

        ByteArrayOutputStream outbytes = new ByteArrayOutputStream();
        DeflaterOutputStream deflating = new DeflaterOutputStream(outbytes, deflater);
        ObjectOutputStream out = new ObjectOutputStream(deflating);

        out.writeObject(serializedObject);

        out.close();
        return outbytes.toByteArray();
    }


    /**
     * deserialize the compress bytes
     * @param bytes
     * @throws IOException
     * @throws ClassNotFoundException
     * @return Object deserialized object
     */
    public static Object deserializeObject(byte[] bytes)
        throws IOException, ClassNotFoundException
    {
        if ((bytes == null) || (bytes.length == 0)) {
            return null;
        }

        ByteArrayInputStream inbytes = new ByteArrayInputStream(bytes);
        InflaterInputStream inflating = new InflaterInputStream(inbytes);
        ObjectInputStream in = new ObjectInputStream(inflating);

        Object deserializedObject = in.readObject();

        in.close();
        return deserializedObject;
    }


    /**
     *  ����InputStream ��ȫ�������е�byte���㷨�������byte��ʱ��byte[] ��̬�����İ취��
     * @param in
     * @return
     * @throws IOException
     */
    public static byte[] getBytesFromStream(InputStream in)
        throws IOException
    {

        /**
         * initSizeRepository.size()��fixedSizeReadStream.size()����ֵ������������
         */
        byte[] initSizeRepository = new byte[10]; //ʼ�����ڱ�������.        
        byte[] fixedSizeReadStream = new byte[3]; //ʼ�����ڶ��̶����ȵ�byte��

        int index = 0;

        while (true) {
            int counter = in.read(fixedSizeReadStream);

            //�����������
            if (counter == -1) {
                break;
            }

            index += counter;

            //��ǰ�����������ܳ��ȡ���ʼ�������ݵĳ���ʱ
            if (index > initSizeRepository.length) {
                int increasedSizeRepository = (initSizeRepository.length * 3) / 2 + 1; //�������ӡ�
                byte[] increasedBytes = new byte[increasedSizeRepository];

                //ԭ������copy����
                System.arraycopy(initSizeRepository, 0, increasedBytes, 0,
                    initSizeRepository.length);

                //append�¼ӵ�����
                System.arraycopy(fixedSizeReadStream, 0, increasedBytes, index - counter,
                    counter);
                initSizeRepository = increasedBytes;
            }
            //��ǰ�����������ܳ��ȡ�=��ʼ�������ݵĳ���ʱ
            else {
                //ԭ������copy����
                System.arraycopy(fixedSizeReadStream, 0, initSizeRepository, index
                        - counter, counter);
            }

        }
        in.close();

        //        System.out.println("index:"+index);

        //�����������ԭʼ���ݣ��������������������ݡ�
        byte[] realSizeRepository = new byte[index];
        System.arraycopy(initSizeRepository, 0, realSizeRepository, 0, index);

        System.out.println("===========");
        for (int i = 0; i < realSizeRepository.length; i++) {
            System.out.print(realSizeRepository[i]);
        }


        return realSizeRepository;
    }


    /**
     * 
     * @param invalidClassException
     * @param a_bytes
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static Object convertSerialID(InvalidClassException invalidClassException,
        byte[] a_bytes)
        throws IOException, ClassNotFoundException
    {
        byte[] bytes = changeSerialID(a_bytes, invalidClassException);

        ByteArrayInputStream inputStream_bytes = new ByteArrayInputStream(bytes);
        ObjectInputStream inputStream_object = new ObjectInputStream(inputStream_bytes);

        Object return_object = null;
        try {
            return_object = inputStream_object.readObject();
        } catch (InvalidClassException e1) {
            return_object = convertSerialID(e1, bytes);
        }

        inputStream_object.close();
        return return_object;
    }


    /**
     * 
     * @param a_bytes
     * @param e
     * @return
     * @throws IOException
     */
    private static byte[] changeSerialID(byte[] a_bytes,
        InvalidClassException e)
        throws IOException
    {
        String exception = e.getMessage();
        System.out.println(exception);

        int first_begin = exception.indexOf("= ");
        int first_end = exception.indexOf(",");

        String stream_class_serialVersionUID = exception.substring(first_begin + 2,
            first_end);

        int second_begin = exception.lastIndexOf("= ");
        String local_class_serialVersionUID = exception.substring(second_begin + 2);

        System.out.println("stream class UID:" + stream_class_serialVersionUID);
        System.out.println("local class UID:" + local_class_serialVersionUID);

        long stream_UID = Long.parseLong(stream_class_serialVersionUID);
        long local_UID = Long.parseLong(local_class_serialVersionUID);
        byte[] changedBytes = replaceBytes(a_bytes, stream_UID, local_UID);

        System.out.println("stream_UID:" + stream_UID);
        System.out.println("local_UID:" + local_UID);

        //       System.out.println("origin:");
        //       for (int i = 0; i < JServiceMsgBytes.length; i++) {
        //           System.out.print(JServiceMsgBytes[i]);
        //       }
        //       
        //       System.out.println();
        //       System.out.println("new:");
        //       for (int i = 0; i < changedJServiceMsgBytes.length; i++) {
        //           System.out.print(changedJServiceMsgBytes[i]);
        //       }

        return changedBytes;
    }


    /**
     * 
     * @param a_value
     * @return
     */
    private static byte[] longToByte(long a_value)
    {
        byte[] bytes = new byte[8];
        for (int i = 1; i <= bytes.length; i++) {
            bytes[8 - i] = new Long(a_value).byteValue();
            a_value = a_value >> 8;
        }
        return bytes;
    }


    /**
     * 
     * @param a_bytes
     * @param oldSuid
     * @param newSuid
     * @return
     */
    private static byte[] replaceBytes(byte[] a_bytes,
        long oldSuid,
        long newSuid)
    {

        byte[] bytes = new byte[a_bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = a_bytes[i];
        }

        byte[] oldSuidBytes = longToByte(oldSuid);

        System.out.println("oldSuid :");
        for (int i = 0; i < oldSuidBytes.length; i++) {
            System.out.print(oldSuidBytes[i]);
        }

        byte[] newSuidBytes = longToByte(newSuid);

        System.out.println();
        System.out.println("newSuid :");
        for (int i = 0; i < newSuidBytes.length; i++) {
            System.out.print(newSuidBytes[i]);
        }

        System.out.println();


        for (int i = 0; i < bytes.length - 7; i++) {
            if (bytes[i] == oldSuidBytes[0]) {
                //compare 8-bit
                boolean match = true;
                for (int j = 1; j <= 7; j++) {
                    if (bytes[i + j] != oldSuidBytes[j]) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    //convert
                    for (int j = 0; j <= 7; j++) {
                        bytes[i + j] = newSuidBytes[j];
                    }
                    i = i + 8;
                }
            }
        }

        return bytes;
    }
}
