package org.appfuse.common.util.classloader;

/**
 * ���Ǵ�Ҷ�֪����ÿ�������е��̶߳���һ����ԱcontextClassLoader������������ʱ��̬�����������ࡣ
 * ϵͳĬ�ϵ�contextClassLoader��systemClassLoader������һ�����java������ִ��ʱ����ʹ��JVM�Դ����ࡢ
 * $JAVA_HOME/jre/lib/ext/�е����$CLASSPATH/�е��࣬���ڷ�Ĭ�ϵ�jar��һ��ֻ���ֶ������û�����ӡ�
 * ����ʵ�ϣ����ǿ���ͨ��Thread.currentThread().setContextClassLoader()���ĵ�ǰ�̵߳�contextClassLoader��Ϊ��
 * ʵ���ڳ����ڼ����ⲿjar��
 * PS:
 * ClassLoader�Ĺ���ԭ���ǣ�
 * 1) �߳���Ҫ�õ�ĳ����ʱ��contextClassLoader�����������������
 * 2) contextClassLoader�������ĸ�ClassLoader����ɸ���������
 * 3) �����ClassLoader�޷������࣬��contextClassLoader��ͼ�Լ�������
 */

/**
 * <p>Description:JarLoader������jar�����ⲿ����</p>
 * <p>Copyright: Copyright (c) 2007</p>
 */
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.Manifest;

public class LaunchJar extends ClassLoader {
	// ��Դ����
	public static Hashtable resources = new Hashtable();

	public static LaunchJar loader = new LaunchJar();

	public static Class load(byte[] resource) throws Exception {
		// ������������ȫ��
		String mainClassName = "";
		// class��Դ��ʵ�建��
		ArrayList classNames = new ArrayList();
		ArrayList classBuffers = new ArrayList();
		// �洢������
		HashMap depends = new HashMap();
		// ��byte[]תΪJarInputStream
		JarInputStream jar = new JarInputStream(new ByteArrayInputStream(
				resource));
		Manifest manifest = jar.getManifest();
		// ��Main-Class������ʱ,���������������ȫ��
		if (manifest != null) {
			mainClassName = manifest.getMainAttributes().getValue("Main-Class");
		}
		// ���λ�ö�ӦJAR�ļ��з�װ�ĸ�����ѹ���ļ���JarEntry
		JarEntry entry;
		while ((entry = jar.getNextJarEntry()) != null) {
			// ���ҵ���entryΪclassʱ
			if (entry.getName().toLowerCase().endsWith(".class")) {
				// ����·��ת��Ϊ��ȫ��
				String name = entry.getName().substring(0,
						entry.getName().length() - ".class".length()).replace(
						'/', '.');
				// ���ظ���
				byte[] data = getResourceData(jar);
				// ��������������
				classNames.add(name);
				classBuffers.add(data);

			} else {
				// ��class��β����ͷ�ַ�Ϊ'/'ʱ
				if (entry.getName().charAt(0) == '/') {
					resources.put(entry.getName(), getResourceData(jar));
					// ����׷��'/'�󻺴�
				} else {
					resources.put("/" + entry.getName(), getResourceData(jar));
				}
			}
		}
		// ����õ�main-class����Ϊ��ʱ
		while (classNames.size() > 0) {
			// �����·��ȫ��
			int n = classNames.size();
			for (int i = classNames.size() - 1; i >= 0; i--) {
				try {
					// ��ѯָ����
					loader.defineClass((String) classNames.get(i),
							(byte[]) classBuffers.get(i), 0,
							((byte[]) classBuffers.get(i)).length);
					// �������
					String pkName = (String) classNames.get(i);
					if (pkName.lastIndexOf('.') >= 0) {
						pkName = pkName.substring(0, pkName.lastIndexOf('.'));
						if (loader.getPackage(pkName) == null) {
							loader.definePackage(pkName, null, null, null,
									null, null, null, null);
						}
					}
					// ��ѯ��ɾ������
					classNames.remove(i);
					classBuffers.remove(i);
				} catch (NoClassDefFoundError e) {
					depends.put((String) classNames.get(i), e.getMessage()
							.replaceAll("/", "."));
				} catch (UnsupportedClassVersionError e) {
					// jre�汾������ʾ
					throw new UnsupportedClassVersionError(classNames.get(i)
							+ ", " + System.getProperty("java.vm.name") + " "
							+ System.getProperty("java.vm.version") + ")");
				}
			}
			if (n == classNames.size()) {
				for (int i = 0; i < classNames.size(); i++) {
					System.err.println("NoClassDefFoundError:"
							+ classNames.get(i));
					String className = (String) classNames.get(i);
					while (depends.containsKey(className)) {
						className = (String) depends.get(className);
					}
				}
				break;
			}
		}
		
		System.out.println(classNames.size());
		for (int i = 0; i < classNames.size(); i++) {
			System.out.println(classNames.get(i));
		}
		
		try {
			// ����
			Thread.currentThread().setContextClassLoader(loader);
			// ���ָ����,���������෽ʽ���
			return Class.forName(mainClassName, true, loader);
		} catch (ClassNotFoundException e) {
			String className = mainClassName;
			while (depends.containsKey(className)) {
				className = (String) depends.get(className);
			}
			throw new ClassNotFoundException(className);
		}
	}

	/**
	 * ���ָ��·���ļ���byte[]��ʽ
	 * 
	 * @param name
	 * @return
	 */
	final static public byte[] getDataSource(String name) {
		FileInputStream fileInput;
		try {
			fileInput = new FileInputStream(new File(name));
		} catch (FileNotFoundException e) {
			fileInput = null;
		}
		BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
		return getDataSource(bufferedInput);
	}

	/**
	 * ���ָ��InputStream��byte[]��ʽ
	 * 
	 * @param name
	 * @return
	 */
	final static public byte[] getDataSource(InputStream is) {
		if (is == null) {
			return null;
		}
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] arrayByte = null;
		try {
			byte[] bytes = new byte[8192];
			bytes = new byte[is.available()];
			int read;
			while ((read = is.read(bytes)) >= 0) {
				byteArrayOutputStream.write(bytes, 0, read);
			}
			arrayByte = byteArrayOutputStream.toByteArray();
		} catch (IOException e) {
			return null;
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
					byteArrayOutputStream = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}

			} catch (IOException e) {
			}
		}
		return arrayByte;
	}

	/**
	 * ���ָ��JarInputStream��byte[]��ʽ
	 * 
	 * @param jar
	 * @return
	 * @throws IOException
	 */
	final static private byte[] getResourceData(JarInputStream jar)
			throws IOException {
		ByteArrayOutputStream data = new ByteArrayOutputStream();
		byte[] buffer = new byte[8192];
		int size;
		while (jar.available() > 0) {
			size = jar.read(buffer);
			if (size > 0) {
				data.write(buffer, 0, size);
			}
		}
		return data.toByteArray();
	}

	/**
	 * ���ص�getResource,����Ƿ��ظ�����
	 */
	public URL getResource(String name) {
		if (resources.containsKey("/" + name)) {
			try {
				return new URL("file:///" + name);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return super.getResource(name);
	}

	/**
	 * ���ص�getResourceAsStream,����Ƿ��ظ�����
	 */
	public InputStream getResourceAsStream(String name) {
		if (name.charAt(0) == '/') {
			name = name.substring(1);
		}
		if (resources.containsKey("/" + name)) {
			return new ByteArrayInputStream((byte[]) resources.get("/" + name));
		}
		return super.getResourceAsStream(name);
	}

	/**
	 * ִ��ָ��class��
	 * 
	 * @param clz
	 * @param methodName
	 * @param args
	 */
	public static void callVoidMethod(Class clz, String methodName,
			String[] args) {
		Class[] arg = new Class[1];
		arg[0] = args.getClass();
		try {
			Method method = clz.getMethod(methodName, arg);
			Object[] inArg = new Object[1];
			inArg[0] = args;
			method.invoke(clz, inArg);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
