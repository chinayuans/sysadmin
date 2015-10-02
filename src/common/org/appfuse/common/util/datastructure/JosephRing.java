package org.appfuse.common.util.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Լɪ���������
 * ������ʵ��Լɪ���Ȧ���⣬n�����ų�һȦ���ӵ�һ����ʼ����������m���˳�Ȧ��
 * ʣ�µ��˼�����ʼ��1������ֱ�����е��˶���ȦΪֹ�����ڸ�����n��m��������е��˳�Ȧ˳��
 *
 */
public class JosephRing {
	// ������������Ķ��˳�
	public static int outNumber;

	// �ܵ��ж��ٸ���
	public static int manSize;

	// ����2��һ��ʼ�͹̶����ˣ������Ҿ�������static

	// Ȧ�е���
	private List<Man> allMan;

	// �����Ѿ������ڼ�����,��ʼ��Ϊ1
	private int nowNumber = 1;

	// �����Ѿ������ڼ������ˣ���ʼ��Ϊ0;
	private int nowMan = 0;

	public JosephRing(int manSize, int outNumber) {
		JosephRing.manSize = manSize;
		JosephRing.outNumber = outNumber;
		init();
	}
	
	
	public static void main(String[] args){
        JosephRing ooc=new JosephRing(1000,10000);
        ooc.begin();
    }

	private void init() {
		allMan = new ArrayList();
		// ��ʼ�������ˣ����������˱��Ϻ�
		int manNumber = 0;
		while (JosephRing.manSize != manNumber) {
			allMan.add(new Man(++manNumber));
		}
	}

	public void begin() {
		while (allMan.size() > 0) {
			Man man = this.select();
			// �������T��ȥ
			allMan.remove(man);
			// ��T�������һ����ʱ��,�ִӵ�һ����ʼ��
			if (nowMan == allMan.size()) {
				nowMan = 0;
			}
			// ˵��T�Ĳ������һ��
			// T���˵ĺ���Ķ�����ǰ��һ��λ��
			// �����Ͱ�ԭ����nowman������,�Ϳ��Դ�nowman��ʼ����
			else {

			}
			// ѡ�������Ժ��ִ�1��ʼ��
			nowNumber = 1;
			System.out.println("���ǵ�" + man.getNumber() + "�ţ������ڱ�T��ȥ��,���ǵ�"
					+ (manSize - allMan.size()) + "����T��" + "������"
					+ allMan.size() + "��Ȧ��");
		}
		System.out.println("�����˶���T��ȥ����");
	}

	// �ҳ���outNumber����
	private Man select() {
		Man man = null;
		// ûѡ������һֱ����
		while (man == null) {
			// nowman����
			Man m = allMan.get(nowMan);
			boolean right = m.reckon(nowNumber);
			// ��������
			if (right) {
				man = m;
			}
			// ˵��������
			else {
				// �������ֵ���һ��
				nowNumber++;
				// ��Ҳ����һ��ȥ
				nowMan++;
				// ˵���Ѿ��������һ����
				if (nowMan == allMan.size()) {
					// �ִӵ�һ����ʼ����
					nowMan = 0;
				}
			}
		}
		return man;
	}

}

class Man {
	private int number;

	public Man(int number) {
		this.number = number;
	}

	public int getNumber() {
		return number;
	}

	// �������жϱ����������Ƿ��outNumber
	public boolean reckon(int num) {
		return num == JosephRing.outNumber;
	}
}
