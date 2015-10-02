/*
 * project name : sysadmin
 * package name : org.appfuse.web.taglib
 * file    name : ELCheckBoxOptionsTag.java
 * class   name : ELCheckBoxOptionsTag
 * Created on 2006-2-21 11:12:30
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.taglib;

import javax.servlet.jsp.JspException;

/**
 * Created on 2006-2-21 11:12:30
 * @author ---Joson Yuan
 * author comments:
 * 
 */
public class ELRadioOptionsTag extends RadioOptionsTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nameExpr;

	private String msgExpr; // 
	
	private String defaultvalueExpr;

	public void setName(String nameExpr) {
		this.nameExpr = nameExpr;
	}

	public void setMsg(String msgExpr) {
		this.msgExpr = msgExpr;
	}


	public void setDefaultvalue(String defaultvalueExpr) {
		this.defaultvalueExpr = defaultvalueExpr;
	}

	/**
	 * Evaluates the expressions for all the given attributes and pass results
	 * up to the parent tag.
	 * 
	 * @throws JspException
	 *             for exceptions occurred during evaluation.
	 */
	private void evaluateExpressions() throws JspException {
		ExpressionEvaluator eval = new ExpressionEvaluator(this, pageContext);
		if (nameExpr != null) {
			super.setName(eval.evalString("name", nameExpr)); //$NON-NLS-1$
		}
		if (msgExpr != null) {
			super.setMsg(eval.evalString("msg", msgExpr)); //$NON-NLS-1$
		}
		if (defaultvalueExpr != null) {
			super.setDefaultvalue(eval.evalString("defaultvalue", defaultvalueExpr)); //$NON-NLS-1$
		}
	
	}
	
	 /**
     * @see javax.servlet.jsp.tagext.Tag#doStartTag()
     */
    public int doStartTag() throws JspException
    {
        evaluateExpressions();
        return super.doStartTag();
    }

    
    /**
     * @see javax.servlet.jsp.tagext.Tag#release()
     */
    public void release()
    {
        super.release();
        this.nameExpr= null;
        this.msgExpr = null;
        this.defaultvalueExpr=null;
    }
}
