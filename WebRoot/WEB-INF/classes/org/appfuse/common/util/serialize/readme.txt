The process:
Read the bytes from DB and try to deserialize it.
If InvalidClassException happens, get stream suid and local suid from exception detail.
Unzip the bytes (binary is zipped to save to DB).
Replace the stream suid with local suid (long->byte[]). Now get new bytes.
Deserialize the new bytes. If InvalidClassException happens again, repeat 2 and 4. Till it can be deserialized.
Return the object.

=====================
��Ҫ�����������:
=====================
һ���ϰ汾��object "hello.HelloWorld" ���л�Ȼ�󱣴浽�ļ����ˡ�
���Ƕ�������ļ��б�����������Ϊ6010�汾��

��ʱ���ϰ汾��object "hello.HelloWorld"�������޸ģ��γ����°汾��class(����Ϊ6012)
��ʱ���ļ���ȥȡ���ݣ��ᱨ�����´���
hello.HelloWorld; local class incompatible: stream classdesc serialVersionUID = -5863503448069391657, local class serialVersionUID = 5362978033127103447

����Ҫ����취���ļ��е��ϰ汾��objectת��Ϊ�°��object��

==========================================
����������⣬��д�����ʵ��˼��:
==========================================
���ļ��ж�ȡ�ϰ汾byte����Ȼ����byte���н�6010��serialVersionUID����Ϊ6012��serialVersionUID.
Ȼ��deserialization�����Ի���°汾��object.

==========================================
˵��:
==========================================

һ���������ࣺ
SerialUIDConvertUtil  ����byte���е�old serialVersionUID�滻��new serialVersionUID
SerializeObject    ��HelloWorld����ͨ��ѹ�����������л����ļ����߲�����ѹ�����������л����ļ�
DeserializeObject  ��ѹ�����л�����ļ���ѹ�������л���һ���°汾��HelloWorld ���� ����
                      ��δ����ѹ�����л�����ļ������л���һ���°汾��HelloWorld ����

                      
1.���Ƚ�hello.HelloWorld1 ������Ϊhello.HelloWorld
  Ȼ������hello.serialzeUtil.SerializeObject��������
  /lib/helloWorld1_compressed.data��/lib/helloWorld1_normal.data
  �����ļ���
2.Ȼ��hello.HelloWorld ��ԭΪhello.HelloWorld1��
3.�ٽ�hello.HelloWorld2 ��ԭΪhello.HelloWorld��
4.��ʱ����hello.serialzeUtil.DeserializeObject.  
  �Ϳ��Խ��ϰ汾��HelloWorld����˳��ת��Ϊ�°汾��HelloWorld.
  
  
==========================================
һ���µ����л�����:
==========================================  
XmlSerializerUtil
�����κζ���,���л���һ��ֻ����primitive����ֵ��xml�ļ���ȥ.
���������Բ���implements serializable.
ͬʱҲ���Խ���ǰ��xml�ٷ����л������������ȥ.