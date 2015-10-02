/**
 * Licensed under the Artistic License; you may not use this file
 * except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://displaytag.sourceforge.net/license.html
 *
 * THIS PACKAGE IS PROVIDED "AS IS" AND WITHOUT ANY EXPRESS OR
 * IMPLIED WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED
 * WARRANTIES OF MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 */
package org.displaytag.sample;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet used to display jsp source for example pages.
 * @author Fabrizio Giustina
 * @version $Revision: 1.11 $ ($Author: fgiust $)
 */
public class DisplaySourceServlet extends HttpServlet
{

    /**
     * D1597A17A6.
     */
    private static final long serialVersionUID = 899149338534L;

 
    /**
     * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest, HttpServletResponse)
     */
    protected final void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
        IOException
    {

        String jspFile = request.getRequestURI();//得到 /webapplication/application/sysadmin.jsp.source
        String contextPath=request.getContextPath(); //   也就是/webapplication 
        
        //System.out.println("request.getRequestURI():"+ request.getRequestURI());
        //System.out.println("request.getContextPath(:"+request.getContextPath());

        
        // lastIndexOf(".") can't be null, since the servlet is mapped to ".source"
        //将 路径中的.source去掉. 得到 /webapplication/application/sysadmin.jsp
        jspFile = jspFile.substring(0, jspFile.lastIndexOf(".")); //$NON-NLS-1$
        
        //获得jsp 文件的相对地址,也就是不包含contextPath ,也就是得到  /application/sysadmin.jsp
        String relative_webcontext_file=jspFile.substring(contextPath.length());
        
        //System.out.println("File location:"+relative_webcontext_file);
        


        InputStream inputStream = getServletContext().getResourceAsStream(relative_webcontext_file);

        if (inputStream == null)
        {
            throw new ServletException("Unable to find JSP file: " + jspFile); //$NON-NLS-1$
        }

        response.setContentType("text/html"); //$NON-NLS-1$

        PrintWriter out = response.getWriter();

        out.println("<%@ page contentType='text/html; charset=GB2312'%>");
        out.println("<%@ page language='java' pageEncoding='GB2312'%>");
        out.println("<html> "); //$NON-NLS-1$
        out.println("<head>"); //$NON-NLS-1$
        out.println("<title>"); //$NON-NLS-1$
        out.println("source for " + jspFile); //$NON-NLS-1$
        out.println("</title>"); //$NON-NLS-1$
        out.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=GB2312\" />"); //$NON-NLS-1$
        out.println("</head>"); //$NON-NLS-1$
        out.println("<body>"); //$NON-NLS-1$
        String cn_back="返 回";
        String back=new String(cn_back.getBytes("GB2312"),"ISO-8859-1");
        out.println("<center><font size='14'><a href=\"javascript:history.go(-1)\">"+back+"</a></font></center>");
        out.println("<pre>"); //$NON-NLS-1$
        for (int currentChar = inputStream.read(); currentChar != -1; currentChar = inputStream.read())
        {
            if (currentChar == '<')
            {
                out.print("&lt;"); //$NON-NLS-1$
            }
            else
            {
                out.print((char) currentChar);
            }
        }
        out.println("</pre>"); //$NON-NLS-1$
        
        
        out.println("</body>"); //$NON-NLS-1$
        out.println("</html>"); //$NON-NLS-1$
    }

}
