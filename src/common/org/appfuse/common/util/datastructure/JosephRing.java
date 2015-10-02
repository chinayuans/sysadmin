package org.appfuse.common.util.datastructure;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 约瑟夫环问题求解
 * 用数组实现约瑟夫出圈问题，n个人排成一圈，从第一个开始报数，报到m的人出圈，
 * 剩下的人继续开始从1报数，直到所有的人都出圈为止。对于给定的n，m，求出所有的人出圈顺序。
 *
 */
public class JosephRing {
	// 报出了这个数的都退出
	public static int outNumber;

	// 总的有多少个人
	public static int manSize;

	// 上面2个一开始就固定好了，所以我就声明成static

	// 圈中的人
	private List<Man> allMan;

	// 现在已经报到第几号了,初始化为1
	private int nowNumber = 1;

	// 现在已经报到第几个人了，初始化为0;
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
		// 初始化所有人，即把所有人编上号
		int manNumber = 0;
		while (JosephRing.manSize != manNumber) {
			allMan.add(new Man(++manNumber));
		}
	}

	public void begin() {
		while (allMan.size() > 0) {
			Man man = this.select();
			// 把这个人T出去
			allMan.remove(man);
			// 当T的是最后一个的时候,又从第一个开始数
			if (nowMan == allMan.size()) {
				nowMan = 0;
			}
			// 说明T的不是最后一个
			// T的人的后面的都会往前移一个位置
			// 这样就把原来的nowman代替了,就可以从nowman开始数了
			else {

			}
			// 选出来了以后又从1开始报
			nowNumber = 1;
			System.out.println("我是第" + man.getNumber() + "号，我现在被T出去了,我是第"
					+ (manSize - allMan.size()) + "个被T的" + "，还有"
					+ allMan.size() + "在圈里");
		}
		System.out.println("所有人都被T出去完了");
	}

	// 找出报outNumber的人
	private Man select() {
		Man man = null;
		// 没选出来就一直报数
		while (man == null) {
			// nowman报数
			Man m = allMan.get(nowMan);
			boolean right = m.reckon(nowNumber);
			// 就是他了
			if (right) {
				man = m;
			}
			// 说明不是他
			else {
				// 报的数字到下一个
				nowNumber++;
				// 人也到下一个去
				nowMan++;
				// 说明已经到了最后一个了
				if (nowMan == allMan.size()) {
					// 又从第一个开始报数
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

	// 报数：判断报出的数字是否和outNumber
	public boolean reckon(int num) {
		return num == JosephRing.outNumber;
	}
}
