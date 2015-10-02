/*
 * project name : equinox-self
 * package name : org.appfuse.model
 * file    name : MenuItem.java
 * class   name : MenuItem
 * Created on 2005-8-1 11:10:01
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.model.sysadmin;

import org.apache.commons.lang.StringUtils;
import org.appfuse.model.base.BaseObject;

/**
 * @hibernate.class table="t_menu_item"
 * 
 */
public class MenuItem extends BaseObject {

	// ע�����parent_name �� name������Ϊ"menu-styles",ֻ����Ϊ"menu_styles",
	// ��Ϊjavascript����ʶmenu-styles���ֱ�������Ϊ�ǷǷ��ġ�-����������javascript
	// ������������
	// 'location', 'page', 'forward', 'action'�����������ǰ��һ���Ļ�������ľ�ʧЧ������ôһ���ȼ���ϵ��
	// ���ң�location�е�����ֻ����Ϊnull,������Ϊ�ո񣬼�ʹΪ�ո�Ҳ��Ϊ����ֵ�ġ�
	private static final long serialVersionUID = 6275176159605251266L;

	private String id;

	private String parent_name;

	/** Holds value of property action, that is, Struts Logical Action Name. */
	private String action;

	/**
	 * Align menu 'left','right','top','bottom' ...and other alignment of
	 * particular menu system
	 */
	private String align;

	/** Holds value of property altImage. */
	private String altImage;

	/** Holds value of property description. */
	private String description;

	/** Holds value of property forward. */
	private String forward;

	/** Holds value of property height. */
	private String height;

	/** Holds value of property image. */
	private String image;

	/** Holds value of property location. */
	private String location;

	/** Holds value of property name. */
	private String name;

	/** Holds value of property onclick. */
	private String onclick;

	/** Holds value of property onmouseout. */
	private String onmouseout;

	/** Holds value of property onmouseover. */
	private String onmouseover;

	/** Holds value of property page. */
	private String page;

	/** Holds value of property roles. */
	private String roles;

	/** Holds value of property target. _top,_parent,_blank,_self */
	private String target;

	/** Holds value of property title. */
	private String title;

	/** Holds value of property toolTip. */
	private String toolTip;

	/** Holds value of property width. */
	private String width;

	/** Holds parsed (with variables) url that is used to render a link */
	private String url;
	
	
	public MenuItem() {
	
	}
	

	/**
	 * @hibernate.id generator-class="uuid.hex" not-null="true" length="32"
	 */
	public String getId() {
		return id;
	}

	/**
	 * @hibernate.property not-null="true" length="30"
	 */
	public String getName() {
		return name;
	}

	/**
	 * @hibernate.property not-null="false" length="30"
	 */
	public String getParent_name() {
		return parent_name;
	}

	/**
	 * @hibernate.property not-null="false" length="255"
	 */
	public String getAction() {
		return action;
	}

	/**
	 * @hibernate.property not-null="false" length="10"
	 */
	public String getAlign() {
		return align;
	}

	/**
	 * @hibernate.property not-null="false" length="30"
	 */
	public String getAltImage() {
		return altImage;
	}

	/**
	 * @hibernate.property not-null="false" length="50"
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @hibernate.property not-null="false" length="50"
	 */
	public String getForward() {
		return forward;
	}

	/**
	 * @hibernate.property not-null="false" length="5"
	 */
	public String getWidth() {
		return width;
	}

	/**
	 * @hibernate.property not-null="false" length="5"
	 */
	public String getHeight() {
		return height;
	}

	/**
	 * @hibernate.property not-null="false" length="50"
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @hibernate.property not-null="false" length="255"
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @hibernate.property not-null="false" length="255"
	 */
	public String getOnclick() {
		return onclick;
	}

	/**
	 * @hibernate.property not-null="false" length="255"
	 */
	public String getOnmouseout() {
		return onmouseout;
	}

	/**
	 * @hibernate.property not-null="false" length="255"
	 */
	public String getOnmouseover() {
		return onmouseover;
	}

	/**
	 * @hibernate.property not-null="false" length="255"
	 */
	public String getPage() {
		return page;
	}

	/**
	 * @hibernate.property not-null="false" length="100"
	 */
	public String getRoles() {
		return roles;
	}

	/**
	 * @hibernate.property not-null="false" length="10"
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * @hibernate.property not-null="false" length="30"
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @hibernate.property not-null="false" length="100"
	 */
	public String getToolTip() {
		return toolTip;
	}

	/**
	 * @hibernate.property not-null="false" length="255"
	 */
	public String getUrl() {
		return url;
	}

	public void setAction(String action) {

		this.action = convert(action);
	}

	public void setAlign(String align) {
		this.align = convert(align);
	}

	public void setAltImage(String altImage) {
		this.altImage = convert(altImage);
	}

	public void setDescription(String description) {
		this.description = convert(description);
	}

	public void setForward(String forward) {
		this.forward = convert(forward);
	}

	public void setHeight(String height) {
		this.height =convert( height);
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setImage(String image) {
		this.image = convert(image);
	}

	public void setLocation(String location) {
		this.location = convert(location);
	}

	public void setName(String name) {
		this.name = convert(name);
	}

	public void setOnclick(String onclick) {
		this.onclick = convert(onclick);
	}

	public void setOnmouseout(String onmouseout) {
		this.onmouseout = convert(onmouseout);
	}

	public void setOnmouseover(String onmouseover) {
		this.onmouseover = convert(onmouseover);
	}

	public void setPage(String page) {
		this.page = convert(page);
	}

	public void setParent_name(String parent_name) {
		this.parent_name = convert(parent_name);
	}

	public void setRoles(String roles) {
		this.roles = convert(roles);
	}

	public void setTarget(String target) {
		this.target = convert(target);
	}

	public void setTitle(String title) {
		this.title = convert(title);
	}

	public void setToolTip(String toolTip) {
		this.toolTip = convert(toolTip);
	}

	public void setUrl(String url) {
		this.url = convert(url);
	}

	public void setWidth(String width) {
		this.width = convert(width);
	}

	/**
	 * �ú�������ת������Ŀո�ֵ���null.����Ҫ���á�
	 * @param arg0
	 * @return
	 * 
	 */
	private String convert(String arg0) {
		if (StringUtils.isBlank(arg0)) {
			arg0 = null;
		}
		return arg0;

	}
}
