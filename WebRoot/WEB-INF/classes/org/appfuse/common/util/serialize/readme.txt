The process:
Read the bytes from DB and try to deserialize it.
If InvalidClassException happens, get stream suid and local suid from exception detail.
Unzip the bytes (binary is zipped to save to DB).
Replace the stream suid with local suid (long->byte[]). Now get new bytes.
Deserialize the new bytes. If InvalidClassException happens again, repeat 2 and 4. Till it can be deserialized.
Return the object.

=====================
需要解决如下问题:
=====================
一个老版本的object "hello.HelloWorld" 序列化然后保存到文件中了。
我们定义这个文件中保存的这个对象为6010版本。

此时对老版本的object "hello.HelloWorld"进行了修改，形成了新版本的class(定义为6012)
此时从文件中去取数据，会报告如下错误：
hello.HelloWorld; local class incompatible: stream classdesc serialVersionUID = -5863503448069391657, local class serialVersionUID = 5362978033127103447

现在要求想办法将文件中的老版本的object转化为新版的object。

==========================================
解决上面问题，所写程序的实现思想:
==========================================
从文件中读取老版本byte流，然后在byte流中将6010的serialVersionUID更改为6012的serialVersionUID.
然后deserialization，可以获得新版本的object.

==========================================
说明:
==========================================

一共有三个类：
SerialUIDConvertUtil  负责将byte流中的old serialVersionUID替换成new serialVersionUID
SerializeObject    将HelloWorld对象通过压缩数据在序列化成文件或者不经过压缩数据在序列化成文件
DeserializeObject  将压缩序列化后的文件解压缩反序列化成一个新版本的HelloWorld 对象 或者
                      将未经过压缩序列化后的文件反序列化成一个新版本的HelloWorld 对象。

                      
1.首先将hello.HelloWorld1 更改名为hello.HelloWorld
  然后运行hello.serialzeUtil.SerializeObject程序，生成
  /lib/helloWorld1_compressed.data和/lib/helloWorld1_normal.data
  两个文件。
2.然后将hello.HelloWorld 还原为hello.HelloWorld1。
3.再将hello.HelloWorld2 还原为hello.HelloWorld。
4.此时运行hello.serialzeUtil.DeserializeObject.  
  就可以将老版本的HelloWorld对象顺利转换为新版本的HelloWorld.
  
  
==========================================
一个新的序列化方法:
==========================================  
XmlSerializerUtil
它将任何对象,序列化到一个只包含primitive类型值的xml文件中去.
这个对象可以不用implements serializable.
同时也可以将先前的xml再反序列化到这个对象中去.