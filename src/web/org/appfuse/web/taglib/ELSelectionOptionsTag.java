/*
 * project name : sysadmin
 * package name : org.appfuse.web.taglib
 * file    name : ELSelectionOptionsTag.java
 * class   name : ELSelectionOptionsTag
 * Created on 2006-2-21 8:58:28
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.taglib;

import javax.servlet.jsp.JspException;


/**
 * Created on 2006-2-21 8:58:28
 * 
 * @author ---Joson Yuan author comments:
 * 
 */
public class ELSelectionOptionsTag extends SelectionOptionsTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String nameExpr;

	private String keycolExpr; // value Expression

	private String valuecolExpr; // display value Expression

	private String defaultvalueExpr;

	private String objectExpr; // objectName Expression

	
	
	public void setDefaultvalue(String defaultvalueExpr) {
		this.defaultvalueExpr = defaultvalueExpr;
	}

	public void setKeycol(String keycolExpr) {
		this.keycolExpr = keycolExpr;
	}

	public void setName(String nameExpr) {
		this.nameExpr = nameExpr;
	}

	public void setObject(String objectExpr) {
		this.objectExpr = objectExpr;
	}

	public void setValuecol(String valuecolExpr) {
		this.valuecolExpr = valuecolExpr;
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
		if (keycolExpr != null) {
			super.setKeycol(eval.evalString("keyco", keycolExpr)); //$NON-NLS-1$
		}
		if (valuecolExpr != null) {
			super.setValuecol(eval.evalString("valuecol", valuecolExpr)); //$NON-NLS-1$
		}
		if (defaultvalueExpr != null) {
			super.setDefaultvalue(eval.evalString("defaultvalue", defaultvalueExpr)); //$NON-NLS-1$
		}
		if (objectExpr != null) {
			super.setObject(eval.evalString("object", objectExpr)); //$NON-NLS-1$
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
        this.defaultvalueExpr = null;
        this.nameExpr= null;
        this.valuecolExpr = null;
        this.keycolExpr = null;
        this.objectExpr= null;
    }
}
