package org.appfuse.web.applet;
// c8:DrawString.java
//author:ZhangHongbin 
//This program is protected by copyright laws.
//Applet program with params.
//<applet code="DrawString.class" codebase="c:\" width="200" height="100">
//<param name="para1" value="This is para1">
//<param name="para2" value="This is para2">
//If you can see this message,your brower doesn't support Java
//</applet> import java.awt.*;

import java.awt.Graphics;

import javax.swing.JApplet;
public class DrawString extends JApplet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g)
    
	{ 
		String param1=getParameter("para1");
		String param2=getParameter("para2");
		g.drawString(param1,30,30);
		g.drawString(param2,50,50);
	}
}
