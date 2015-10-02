/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : MenuItemAction.java
 * class   name : MenuItemAction
 * Created on 2005-7-22 18:53:50
 * creator ---Joson Yuan
 * author comments:
 * 
 * 该Action主要用于PerfectMenu风格菜单的生成处理
 */
package org.appfuse.web.action.sysadmin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.navigator.menu.MenuComponent;
import net.sf.navigator.menu.MenuRepository;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.appfuse.model.sysadmin.MenuItem;
import org.appfuse.service.sysadmin.MenuItemManagerService;
import org.appfuse.web.action.base.BaseAction;
import org.appfuse.web.action.util.ActionUtil_BlurQuery;

public class MenuItemAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(MenuItemAction.class);

	private MenuItemManagerService _service = null;

	public void setMenuItemManagerService(MenuItemManagerService service) {
		if (logger.isDebugEnabled()) {
			logger.debug("setMenuItemManagerService() - start");
		}

		this._service = service;

		if (logger.isDebugEnabled()) {
			logger.debug("setMenuItemManagerService() - end");
		}
	}

	/**
	 * 根据id找到一条记录的详细信息.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:27:38
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward detailOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("detailOne() - start");
		}

		request.getSession().setAttribute("test", "succeeded!");
		String menuItem_id = request.getParameter("id");

		MenuItem menuItem = _service.getMenuItem(menuItem_id);
		if (menuItem == null)
			if (logger.isDebugEnabled()) {
				logger.debug("menuItem -----NullPointerException");
			}
		if (logger.isDebugEnabled()) {
			logger.debug("menuItem id:" + menuItem.getId());
			logger.debug("menuItem name:" + menuItem.getName());
			logger.debug("menuItem title:" + menuItem.getTitle());
		}

		DynaActionForm userForm = (DynaActionForm) form;
		userForm.set("menuItem", menuItem);

		ActionForward returnActionForward = mapping.findForward("detailOne");
		if (logger.isDebugEnabled()) {
			logger.debug("detailOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * 找到所有的记录信息.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:28:46
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward queryAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("queryAll() - start");
		}

		List menuItems = _service.getMenuItems();

		if (menuItems != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < menuItems.size(); i++) {
					MenuItem menuItem = (MenuItem) menuItems.get(i);
					logger.debug("menuItem id:" + menuItem.getId());
				}
			}
		}

		request.setAttribute("data", menuItems);
		ActionForward returnActionForward = mapping.findForward("listAll");
		if (logger.isDebugEnabled()) {
			logger.debug("queryAll() - end");
		}
		return returnActionForward;
	}

	/**
	 * 在设置查询条件之前作一些工作,比如给查询条件赋初始值.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:30:43
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward preQueryStandard(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("preQueryStandard() - start");
		}

		// 初始化一些查询时的缺省条件
		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		menuItem.setName("menu1");
		menuItem.setTitle("菜单1");
		menuItemForm.set("menuItem", menuItem);

		// 保存下一步要执行的方法。
		request.setAttribute("method", "queryStandard");
		ActionForward returnActionForward = mapping
				.findForward("preQueryStandard");
		if (logger.isDebugEnabled()) {
			logger.debug("preQueryStandard() - end");
		}
		return returnActionForward;
	}

	/**
	 * 根据前面输入的条件进行查询,同时在本方法中 还可以增加隐含的查询(过滤)条件进行查询.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:32:23
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward queryStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("queryStandard() - start");
		}

		// 获得用户输入的查询条件。
		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");

		// 根据domain object 获得: from 对象名 where ....
		String query_condition =new ActionUtil_BlurQuery().getQueryCondition(menuItem);

		List menuItems = _service.queryStandard(query_condition);

		if (menuItems != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < menuItems.size(); i++) {
					menuItem = (MenuItem) menuItems.get(i);
					logger.debug("menuItem id:" + menuItem.getId());
				}
			}
		}

		// // 刷新使用
		// if (menuItems.size()==0){
		// menuItems=null;
		// }

		request.setAttribute("data", menuItems);
		request.setAttribute("refresh", "afterQueryStandard");

		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("queryStandard() - end");
		}
		return returnActionForward;
	}

	/**
	 * 在增加一条新记录前,给记录中的相关字段赋值一个初始值.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:34:43
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward preSaveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("preSaveOne() - start");
		}

		if (log.isDebugEnabled()) {
			log.debug("entering 'preSaveOne' method...");
		}
		DynaActionForm menuItemForm = (DynaActionForm) form;

		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");

		menuItem.setName("menu1");
		menuItem.setTitle("第二姓名");
		menuItem.setTarget("main");
		
		menuItemForm.set("menuItem", menuItem);

		// 把这下一步要执行的方法 保存到页面中.
		request.setAttribute("method", "saveOne");

		ActionForward returnActionForward = mapping
				.findForward("preSaveOrUpdate");
		if (logger.isDebugEnabled()) {
			logger.debug("preSaveOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * 执行具体的增加一条记录的操作.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:41:06
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward saveOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("saveOne() - start");
		}

		// StringBuffer sb=new StringBuffer();
		// sb.append("mapping.getName():"+mapping.getName());
		// sb.append("mapping.getParameter():"+mapping.getParameter());
		// sb.append("mapping.getInput():"+mapping.getInput());
		// sb.append("mapping.getInput():"+mapping.getPath());
		// sb.append("mapping.getForward():"+mapping.getForward());
		// sb.append("mapping.getParameter():"+mapping.getParameter());
		// sb.append("mapping.getInputForward():"+mapping.getInputForward());
		// sb.append("mapping.getAttribute():"+mapping.getAttribute());
		// System.out.println(sb);

		// System.out.println("method:"+request.getMethod());
		// System.out.println("action:"+request.getMethod());

		// 效验得到的数据.
		// 注意struts1.1中saveErrors(HttpServletRequest,ActionErrors);
		// struts1.2中saveErrors(HttpServletRequest,ActionMessages);

		ActionErrors errors = form.validate(mapping, request);
		if (!errors.isEmpty()) {
			// 保存错误信息的同时回到输入,修改界面中
			saveErrors(request, errors);

			ActionForward returnActionForward = mapping
					.findForward("preSaveOrUpdate");
			if (logger.isDebugEnabled()) {
				logger.debug("saveOne() - end");
			}
			return returnActionForward;
		}

		// 效验成功后保存数据,并且提示保存成功信息.
		DynaActionForm menuItemForm = (DynaActionForm) form;

		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		// System.out.println("------"+menuItem.getFirstName());
		// System.out.println("------"+menuItem.getLastName());

		_service.saveMenuItem(menuItem);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.saved"));
		saveMessages(request, messages);// 页面中使用<html:message .../>输出

		// return queryAll(mapping, form, request, response);

		// 用于是否刷新页面
		// System.out.println("==========================");
		request.setAttribute("refresh", "afterSaveOne");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("saveOne() - end");
		}
		return returnActionForward;

	}

	/**
	 * 在更新一条记录前,给记录中的相关字段赋值一个初始值(给时间赋值 当前时间).
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:43:57
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward preUpdateOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("preUpdateOne() - start");
		}

		DynaActionForm menuItemForm = (DynaActionForm) form;

		String menuItem_id = request.getParameter("id");
		MenuItem menuItem = _service.getMenuItem(menuItem_id);

		menuItemForm.set("menuItem", menuItem);

		// 把这下一步要执行的方法 保存到页面中.
		request.setAttribute("method", "updateOne");

		ActionForward returnActionForward = mapping
				.findForward("preSaveOrUpdate");
		if (logger.isDebugEnabled()) {
			logger.debug("preUpdateOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * 执行具体的更新一条记录的操作.(实际上是根据id进行一笔记录的更新)
	 * 因为menuItem_InputOrEdit.jsp页面上有menuItem.id 数据的提交
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:45:42
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward updateOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("updateOne() - start");
		}

		// 效验得到的数据.
		ActionErrors errors = form.validate(mapping, request);
		if (!errors.isEmpty()) {
			// 保存错误信息的同时回到输入,修改界面中
			saveErrors(request, errors);
			ActionForward returnActionForward = mapping
					.findForward("preSaveOrUpdate");
			if (logger.isDebugEnabled()) {
				logger.debug("updateOne() - end");
			}
			return returnActionForward;
		}

		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		_service.updateMenuItem(menuItem);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.saved"));
		saveMessages(request, messages);// 页面中使用<html:message .../>输出

		request.setAttribute("refresh", "afterUpdateOne");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("updateOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * 执行具体的更新一些记录的操作，给出具体的条件和要更新的字段。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:45:42
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward updateSome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("updateSome() - start");
		}

		// 效验得到的数据.
		ActionErrors errors = form.validate(mapping, request);
		if (!errors.isEmpty()) {
			// 保存错误信息的同时回到输入,修改界面中
			saveErrors(request, errors);

			// 等待将来 做完了批量更新 功能。
			// to do 批量更新 功能
			ActionForward returnActionForward = mapping
					.findForward("preUpdateSome");
			if (logger.isDebugEnabled()) {
				logger.debug("updateSome() - end");
			}
			return returnActionForward;
		}

		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		_service.updateMenuItem(menuItem);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.saved"));
		saveMessages(request, messages);// 页面中使用<html:message .../>输出

		request.setAttribute("refresh", "afterUpdateSome");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("updateSome() - end");
		}
		return returnActionForward;
	}

	/**
	 * 根据表单中的数据是否在数据库中已经拥有, 然后自动判断是增加还是更新数据. 暂时没有用处。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:41:06
	 * @author ---Joson Yuan author comments:
	 * @deprecated
	 */
	public ActionForward saveOrUpdateOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("saveOrUpdateOne() - start");
		}

		DynaActionForm menuItemForm = (DynaActionForm) form;
		_service.saveOrUpdateMenuItem((MenuItem) menuItemForm.get("menuItem"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.saved"));
		saveMessages(request, messages);// 页面中使用<html:message .../>输出

		request.setAttribute("refresh", "afterSaveOrUpdateOne");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("saveOrUpdateOne() - end");
		}
		return returnActionForward;

	}

	/**
	 * 根据给定的id删除一个记录.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:48:07
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward removeOne(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("entering 'removeOne' method...");
		}

		_service.removeMenuItem(request.getParameter("id"));
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.deleted"));
		saveMessages(request, messages);

		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("removeOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * 根据给定的id数组删除记录.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:48:07
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward removeSomeByIds(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("removeSomeByIds() - start");
		}

		String[] ids = (String[]) request.getParameterValues("ids");
		// String ids=(String)request.getParameter("ids");

		// 删除失败的话
		if (ids == null) {
			ActionForward returnActionForward = queryAll(mapping, form,
					request, response);
			if (logger.isDebugEnabled()) {
				logger.debug("removeSomeByIds() - end");
			}
			return returnActionForward;
		}

		if (logger.isDebugEnabled()) {
			for (int i = 0; i < ids.length; i++) {
				logger.debug("selected id:" + ids[i]);
			}

		}

		_service.removeMenuItems(ids);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.deleted"));
		saveMessages(request, messages);
		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("removeSomeByIds() - end");
		}
		return returnActionForward;
	}

	/**
	 * 在删除一些记录 制定删除条件的时候,给这些删除条件赋初始值.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:49:20
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward preRemoveSome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("preRemoveSome() - start");
		}

		// 初始化一些查询时的缺省条件
		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		menuItem.setName("menu1");
		menuItem.setTitle("菜单1");
		menuItemForm.set("menuItem", menuItem);

		// 保存下一步要执行的方法。
		request.setAttribute("method", "removeSome");
		ActionForward returnActionForward = mapping
				.findForward("preRemoveSome");
		if (logger.isDebugEnabled()) {
			logger.debug("preRemoveSome() - end");
		}
		return returnActionForward;
	}

	/**
	 * 执行具体的删除一些记录的操作.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-7-25 10:52:34
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward removeSome(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("removeSome() - start");
		}

		//
		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");

		// 根据domain object 获得: from 对象名 where ....
		String query_condition = new ActionUtil_BlurQuery().getQueryCondition(menuItem);

		_service.removeSomeMenuItems(query_condition);

		// 执行善后处理，消息的反馈。
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.deleted"));
		saveMessages(request, messages);

		request.setAttribute("refresh", "afterRemoveSome");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("removeSome() - end");
		}
		return returnActionForward;
	}

	public ActionForward removeAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("removeAll() - start");
		}

		_service.removeAllMenuItems();
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.deleted"));
		saveMessages(request, messages);
		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("removeAll() - end");
		}
		return returnActionForward;
	}

	/**
	 * 什么也不作，仅仅用来进行转向。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-8-2 16:52:54
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward forward(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("forward() - start");
		}

		ActionForward returnActionForward = mapping.findForward("listAll");

		if (logger.isDebugEnabled()) {
			logger.debug("forward() - end");
		}
		return returnActionForward;
	}

	/**
	 * 导向主菜单(导航菜单－－－可以隐藏，可以现身的那种菜单)
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 * Created on 2005-8-18 10:54:58
	 * @author ---Joson Yuan
	 * author comments:
	 *
	 */
	public ActionForward getMainMenu(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMainMenu() - start");
		}

		
		String Menu_Displayer="mainMenu";

		// 建立菜单库。
		MenuRepository repository = buildRepository();

		request.setAttribute("repository", repository);

		
		ActionForward returnActionForward = mapping.findForward(Menu_Displayer);
		if (logger.isDebugEnabled()) {
			logger.debug("getMainMenu() - end");
		}
		return returnActionForward;

	}
	
	/**
	 * 用于显示不同风格的菜单： 从后台数据库中读取菜单的数据，建立一个menu respository ,然后前台作显示。
	 * 将建立好的树型对象存入到request中。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-8-1 17:16:07
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward getMenuRepository(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getMenuRepository() - start");
		}

		// Menu_Displayer的风格有XTree,Simple,DropDown,
		// CoolMenu,CoolMenu4,ListMenu,TabbedMenu.
		// 缺省设置为XTree风格。
		String Menu_Displayer = request.getParameter("style");
		if (Menu_Displayer == null) {
			Menu_Displayer = "XTree";
		}

		// 建立菜单库。
		MenuRepository repository = buildRepository();

		request.setAttribute("repository", repository);

		// Menu_Displayer的风格有XTree,Simple,DropDown,
		// CoolMenu,CoolMenu4,ListMenu,TabbedMenu.
		// 缺省设置为XTree风格。
		ActionForward returnActionForward = mapping.findForward(Menu_Displayer);
		if (logger.isDebugEnabled()) {
			logger.debug("getMenuRepository() - end");
		}
		return returnActionForward;

	}

	/**
	 * 从后台数据库中读取菜单的数据，建立一个菜单的javascript ,然后前台作显示。 将建立好的javascript存入到request中。
	 * 这是一个很漂亮的菜单显示样式。通过数据库驱动生成。
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-8-1 17:16:07
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward getPerfectMenu(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("getPerfectMenu() - start");
		}

		// 建立菜单库。
		MenuRepository repository = buildRepository();

		// 根据菜单仓库中的数据生成－－－产生菜单使用的javascript语句。
		StringBuffer sb = produceMenuJavascript(request, repository);

		String MenuScript = sb.toString();
		if (logger.isDebugEnabled()) {
			logger.debug("getPerfectMenu() -  : " + MenuScript);
		}

		request.setAttribute("MenuScript", MenuScript);

		ActionForward returnActionForward = mapping.findForward("PerfectMenu");
		if (logger.isDebugEnabled()) {
			logger.debug("getPerfectMenu() - end");
		}
		return returnActionForward;

	}

	/**
	 * 根据菜单仓库中的数据生成－－>产生菜单的时候，需要使用的javascript语句。
	 * 
	 * @param request
	 * @param repository
	 * @return Created on 2005-8-8 11:46:35
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	private StringBuffer produceMenuJavascript(HttpServletRequest request,
			MenuRepository repository) {
		// 得到顶级菜单。
		List top_menus = repository.getTopMenus();
		StringBuffer sb = new StringBuffer();
		int global_submenus = 0;

		for (int i = 0; i < top_menus.size(); i++) { // 根菜单处理
			MenuComponent rootMenu = (MenuComponent) top_menus.get(i);

			sb.append("rootMenu" + (i + 1) + "=new mMenu(");// 开头
			sb.append("'" + convert(rootMenu.getTitle()) + "',");// caption

			// 如果page没有链接，那么就不要链接，否则，加上链接
			if (convert(rootMenu.getPage()).equals("")) { // command
				sb.append("'',");
			} else {
				sb.append("'" + request.getContextPath()
						+ convert(rootMenu.getPage()) + "',");
			}

			// 这个target属性必须存在：self,parent,blank,top

			sb.append("'" + convertTarget(rootMenu.getTarget()) + "',");// target
			sb.append("'" + convert(rootMenu.getImage()) + "',");// img
			sb.append("'" + convert(rootMenu.getWidth()) + "',");// sizex
			sb.append("'" + convert(rootMenu.getHeight()) + "',");// sizey

			String pos = "";
			if (convert(rootMenu.getAlign()).equals("left"))
				pos = "0";
			else if (convert(rootMenu.getAlign()).equals("right")) {
				pos = "1";
			} else {
				pos = convert(rootMenu.getAlign());
			}
			// pos=0 图像在左边，pos=1图像在右边，pos=2 图像为背景,pos=''没有图像
			sb.append("'" + pos + "',");// pos
			sb.append("'0',");// flag='0' 表明是根菜单
			sb.append("'" + convert(rootMenu.getName()) + "'");// id
			sb.append("); \r\n");// 结尾

			// 寻找其子菜单。

			MenuComponent[] sub_menus = rootMenu.getMenuComponents();
			// 子菜单的序号

			for (int j = 0; j < sub_menus.length; j++) {
				// 判断是否有子菜单，有的话，如下处理。
				if (sub_menus[j].getComponents().size() != 0) {
					global_submenus = global_submenus + 1;
					int initial_value = global_submenus;
					sb.append("msub" + initial_value + "=new mMenuItem(");
					sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption
					// command
					// 如果page没有链接，那么就不要链接，否则，加上链接
					if (convert(sub_menus[j].getPage()).equals("")) {
						sb.append("'',");
						// target
						sb.append("null,");
					} else {
						sb.append("'" + request.getContextPath()
								+ convert(sub_menus[j].getPage()) + "',");
						// target
						sb.append("'" + convertTarget(sub_menus[j].getTarget())
								+ "',");
					}

					sb.append("false,");// isline，true表示这一行空行，不显示任何汉字信息
					sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
					sb.append("'1',");// 表示在该菜单下面没有子菜单,!null表示有子菜单

					sb.append("'" + convert(sub_menus[j].getImage()) + "',");// img
					sb.append("'" + convert(sub_menus[j].getWidth()) + "',");// sizex
					sb.append("'" + convert(sub_menus[j].getHeight()) + "',");// sizey

					String submenu_pos = "";
					if (convert(sub_menus[j].getAlign()).equals("left"))
						submenu_pos = "0";
					else if (convert(sub_menus[j].getAlign()).equals("right")) {
						submenu_pos = "1";
					} else {
						submenu_pos = convert(sub_menus[j].getAlign());
					}
					// pos=0 图像在左边，pos=1图像在右边，pos=2 图像为背景,pos=''没有图像
					sb.append("'" + submenu_pos + "',");// pos
					sb.append("'1'");// flag='1' 表明是 当前菜单是子菜单

					sb.append(");\r\n");

					// 对根菜单的子菜单的子菜单处理
					global_submenus = produce_submenu(sb, sub_menus[j],
							global_submenus, request);

					sb.append("rootMenu" + (i + 1) + ".addItem(msub"
							+ initial_value + ");\r\n");

				} else {

					sb.append("rootMenu" + (i + 1) + ".addItem(new mMenuItem(");
					sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption

					// command
					// 如果page没有链接，那么就不要链接，否则，加上链接
					if (convert(sub_menus[j].getPage()).equals("")) {
						sb.append("'',");
						// target
						sb.append("null,");
					} else {
						sb.append("'" + request.getContextPath()
								+ convert(sub_menus[j].getPage()) + "',");
						// target
						sb.append("'" + convertTarget(sub_menus[j].getTarget())
								+ "',");

					}

					sb.append("false,");// isline，true表示这一行空行，不显示任何汉字信息
					sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
					// 菜单的提示，将菜单提示信息显示在IE中的状态栏中。

					sb.append("null,"); // level ，null 表示在该菜单下面没有子菜单,!null表示有子菜单

					sb.append("'" + convert(sub_menus[j].getImage()) + "',");// img
					sb.append("'" + convert(sub_menus[j].getWidth()) + "',");// sizex
					sb.append("'" + convert(sub_menus[j].getHeight()) + "',");// sizey

					String submenu_pos = "";
					if (convert(sub_menus[j].getAlign()).equals("left"))
						submenu_pos = "0";
					else if (convert(sub_menus[j].getAlign()).equals("right")) {
						submenu_pos = "1";
					} else {
						submenu_pos = convert(sub_menus[j].getAlign());
					}
					// pos=0 图像在左边，pos=1图像在右边，pos=2 图像为背景,pos=''没有图像
					sb.append("'" + submenu_pos + "',");// pos
					sb.append("'1',");// flag='1' 表明是当前菜单是子菜单
					sb.append("'" + convert(sub_menus[j].getParent().getName())
							+ "'");// parentMenu
					// 获得父类菜单的id号码。
					sb.append("));\r\n");

					//
					// sb.append("'" +
					// convert(sub_menus[j].getParent().getName())
					// + "'");// parentMenu
					// 获得父类菜单的id号码。

				}
			}

		}
		return sb;
	}

	/**
	 * 用于转换数据库中的target数据为 生成菜单需要的target数据。
	 * 
	 * @param target
	 * @return Created on 2005-8-8 11:50:49
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	private String convertTarget(String target) {
		String return_value = "";
		if (target == null || target.trim().equals("")) {
			return_value = "self";
		} else if (target.equalsIgnoreCase("_blank")) {
			return_value = "blank";
		} else if (target.equalsIgnoreCase("_parent")) {
			return_value = "parent";
		} else if (target.equalsIgnoreCase("_top")) {
			return_value = "top";
		} else if (target.equalsIgnoreCase("_self")) {
			return_value = "self";
		} else if (target.equalsIgnoreCase("main")) {
			return_value = "self";
		}
		return return_value;
	}

	/**
	 * 空指针的处理。
	 * 
	 * @param object
	 * @return Created on 2005-8-8 11:51:40
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	private String convert(String object) {
		if (object == null) {
			return "";
		} else {
			return object;
		}
	}

	/**
	 * 递归函数：用于子菜单的javascript语句的生成。
	 * 
	 * @param sb
	 * @param component
	 * @param global_submenus
	 * @param request
	 * @return Created on 2005-8-8 11:48:53
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	private int produce_submenu(StringBuffer sb, MenuComponent component,
			int global_submenus, HttpServletRequest request) {
		// 子菜单的序号
		MenuComponent[] sub_menus = component.getMenuComponents();
		int origin_submenus = global_submenus;
		for (int j = 0; j < sub_menus.length; j++) {
			// 判断是否有子菜单，有的话，如下处理。
			if (sub_menus[j].getComponents().size() != 0) {
				global_submenus = global_submenus + 1;
				int initial_value = global_submenus;
				sb.append("msub" + initial_value + "=new mMenuItem(");
				sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption

				// command
				// 如果page没有链接，那么就不要链接，否则，加上链接
				if (convert(sub_menus[j].getPage()).equals("")) {
					sb.append("'',");
					// target
					sb.append("null,");
				} else {
					sb.append("'" + request.getContextPath()
							+ convert(sub_menus[j].getPage()) + "',");
					// target
					sb.append("'" + convertTarget(sub_menus[j].getTarget())
							+ "',");

				}

				sb.append("false,");// isline，true表示这一行空行，不显示任何汉字信息
				sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
				sb.append("'1',");// 表示在该菜单下面没有子菜单,!null表示有子菜单

				sb.append("'" + convert(sub_menus[j].getImage()) + "',");// img
				sb.append("'" + convert(sub_menus[j].getWidth()) + "',");// sizex
				sb.append("'" + convert(sub_menus[j].getHeight()) + "',");// sizey

				String submenu_pos = "";
				if (convert(sub_menus[j].getAlign()).equals("left"))
					submenu_pos = "0";
				else if (convert(sub_menus[j].getAlign()).equals("right")) {
					submenu_pos = "1";
				} else {
					submenu_pos = convert(sub_menus[j].getAlign());
				}
				// pos=0 图像在左边，pos=1图像在右边，pos=2 图像为背景,pos=''没有图像
				sb.append("'" + submenu_pos + "',");// pos
				sb.append("'1'");// flag='1' 表明是 当前菜单是子菜单
				sb.append(");\r\n");

				// 对根菜单的子菜单的子菜单处理
				global_submenus = produce_submenu(sb, sub_menus[j],
						global_submenus, request);

				sb.append("msub" + origin_submenus + ".addItem(msub"
						+ initial_value + ");\r\n");

			} else {

				sb.append("msub" + (origin_submenus)
						+ ".addItem(new mMenuItem(");
				sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption

				// command
				// 如果page没有链接，那么就不要链接，否则，加上链接
				if (convert(sub_menus[j].getPage()).equals("")) {
					sb.append("'',");
					// target
					sb.append("null,");
				} else {
					sb.append("'" + request.getContextPath()
							+ convert(sub_menus[j].getPage()) + "',");
					// target
					sb.append("'" + convertTarget(sub_menus[j].getTarget())
							+ "',");
				}

				sb.append("false,");// isline，true表示这一行空行，不显示任何汉字信息
				sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
				// 菜单的提示，将菜单提示信息显示在IE中的状态栏中。

				sb.append("null,"); // level ，null 表示在该菜单下面没有子菜单,!null表示有子菜单

				sb.append("'" + convert(sub_menus[j].getImage()) + "',");// img
				sb.append("'" + convert(sub_menus[j].getWidth()) + "',");// sizex
				sb.append("'" + convert(sub_menus[j].getHeight()) + "',");// sizey

				String submenu_pos = "";
				if (convert(sub_menus[j].getAlign()).equals("left"))
					submenu_pos = "0";
				else if (convert(sub_menus[j].getAlign()).equals("right")) {
					submenu_pos = "1";
				} else {
					submenu_pos = convert(sub_menus[j].getAlign());
				}
				// pos=0 图像在左边，pos=1图像在右边，pos=2 图像为背景,pos=''没有图像
				sb.append("'" + submenu_pos + "',");// pos
				sb.append("'1'");// flag='1' 表明是当前菜单是子菜单
				sb.append("));\r\n");

				// sb.append("'" + convert(sub_menus[j].getParent().getName())
				// + "'");// parentMenu
				// 获得父类菜单的id号码。

				// sb.append("msub" + (origin_submenus-1) + ".addItem(msub"
				// + origin_submenus + ");\r\n");
			}
		}
		return global_submenus;
	}

	/**
	 * 建立菜单库－包含了菜单之间的父子关系。
	 * 
	 * @return
	 * @throws Exception
	 *             Created on 2005-8-7 10:00:15
	 * @author ---Joson Yuan author comments:
	 * 
	 */

	private MenuRepository buildRepository() throws Exception {
		// I had issues using the existing repository - creating a new one
		// seems to solve the problem. If you figure out how to use the default
		// Repository and keep your menus from duplicating themselves - please
		// let me know!

		// 建立树型对象
		MenuRepository repository = new MenuRepository();

		// Get the repository from the application scope - and copy the
		// DisplayerMappings from it.
		MenuRepository defaultRepository = (MenuRepository) getServlet()
				.getServletContext().getAttribute(
						MenuRepository.MENU_REPOSITORY_KEY);
		// 获得缺省的显示方式。
		repository.setDisplayers(defaultRepository.getDisplayers());

		// 上面两句代码其实可以不需要。

		// 获得menuitem表中的所有数据。
		List rows = _service.getMenuItems();
		
//		List rows=null;
//		
//		//menuItem中的属性值如果是
//		for (int i = 0; i < orgin_rows.size(); i++) {
//			MenuItem temp_menu = (MenuItem) rows.get(i);
//			BeanUtils.
//		}

		// 开始建立菜单之间的父子关系。
		for (int i = 0; i < rows.size(); i++) {
			MenuComponent mc = new MenuComponent();
			MenuItem menu = (MenuItem) rows.get(i);
			BeanUtils.copyProperties(mc, menu);
			String name = menu.getName();
			String parent = menu.getParent_name();

			// 查看信息是否正确
			if (logger.isDebugEnabled()) {
				logger.debug(name + ", parent is: " + parent);
				if (parent != null)
					logger.debug("parent_name's length:" + parent.length());
			}

			// 当parent!=null并且parent!=空格，然后可以继续下面的找他的父菜单,然后再加入这个菜单（repository.addMenu(mc);）
			if (parent != null && !parent.trim().equals("")) {
				parent = parent.trim();
				MenuComponent parentMenu = repository.getMenu(parent);
				if (parentMenu == null) {

					// 查看信息是否正确
					if (logger.isDebugEnabled()) {
						logger.debug("parentMenu '" + parent
								+ "' doesn't exist!");
					}

					// create a temporary parentMenu
					// 建立一个临时的父菜单。
					parentMenu = new MenuComponent();
					parentMenu.setName(parent);

					// 存入菜单库中。
					repository.addMenu(parentMenu);
				}

				// 建立父子关系
				mc.setParent(parentMenu);
			}

			// 1.如果没有父菜单（即parent=null或者parent=空格），直接加入第一级别的菜单库中，也就是根菜单。
			// 2.有父菜单的话，设置好关系后，再加入该菜单。
			repository.addMenu(mc);
		}
		return repository;
	}

}
