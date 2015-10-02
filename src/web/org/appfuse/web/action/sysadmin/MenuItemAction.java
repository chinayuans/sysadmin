/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : MenuItemAction.java
 * class   name : MenuItemAction
 * Created on 2005-7-22 18:53:50
 * creator ---Joson Yuan
 * author comments:
 * 
 * ��Action��Ҫ����PerfectMenu���˵������ɴ���
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
	 * ����id�ҵ�һ����¼����ϸ��Ϣ.
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
	 * �ҵ����еļ�¼��Ϣ.
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
	 * �����ò�ѯ����֮ǰ��һЩ����,�������ѯ��������ʼֵ.
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

		// ��ʼ��һЩ��ѯʱ��ȱʡ����
		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		menuItem.setName("menu1");
		menuItem.setTitle("�˵�1");
		menuItemForm.set("menuItem", menuItem);

		// ������һ��Ҫִ�еķ�����
		request.setAttribute("method", "queryStandard");
		ActionForward returnActionForward = mapping
				.findForward("preQueryStandard");
		if (logger.isDebugEnabled()) {
			logger.debug("preQueryStandard() - end");
		}
		return returnActionForward;
	}

	/**
	 * ����ǰ��������������в�ѯ,ͬʱ�ڱ������� ���������������Ĳ�ѯ(����)�������в�ѯ.
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

		// ����û�����Ĳ�ѯ������
		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");

		// ����domain object ���: from ������ where ....
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

		// // ˢ��ʹ��
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
	 * ������һ���¼�¼ǰ,����¼�е�����ֶθ�ֵһ����ʼֵ.
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
		menuItem.setTitle("�ڶ�����");
		menuItem.setTarget("main");
		
		menuItemForm.set("menuItem", menuItem);

		// ������һ��Ҫִ�еķ��� ���浽ҳ����.
		request.setAttribute("method", "saveOne");

		ActionForward returnActionForward = mapping
				.findForward("preSaveOrUpdate");
		if (logger.isDebugEnabled()) {
			logger.debug("preSaveOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * ִ�о��������һ����¼�Ĳ���.
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

		// Ч��õ�������.
		// ע��struts1.1��saveErrors(HttpServletRequest,ActionErrors);
		// struts1.2��saveErrors(HttpServletRequest,ActionMessages);

		ActionErrors errors = form.validate(mapping, request);
		if (!errors.isEmpty()) {
			// ���������Ϣ��ͬʱ�ص�����,�޸Ľ�����
			saveErrors(request, errors);

			ActionForward returnActionForward = mapping
					.findForward("preSaveOrUpdate");
			if (logger.isDebugEnabled()) {
				logger.debug("saveOne() - end");
			}
			return returnActionForward;
		}

		// Ч��ɹ��󱣴�����,������ʾ����ɹ���Ϣ.
		DynaActionForm menuItemForm = (DynaActionForm) form;

		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		// System.out.println("------"+menuItem.getFirstName());
		// System.out.println("------"+menuItem.getLastName());

		_service.saveMenuItem(menuItem);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"menuItem.saved"));
		saveMessages(request, messages);// ҳ����ʹ��<html:message .../>���

		// return queryAll(mapping, form, request, response);

		// �����Ƿ�ˢ��ҳ��
		// System.out.println("==========================");
		request.setAttribute("refresh", "afterSaveOne");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("saveOne() - end");
		}
		return returnActionForward;

	}

	/**
	 * �ڸ���һ����¼ǰ,����¼�е�����ֶθ�ֵһ����ʼֵ(��ʱ�丳ֵ ��ǰʱ��).
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

		// ������һ��Ҫִ�еķ��� ���浽ҳ����.
		request.setAttribute("method", "updateOne");

		ActionForward returnActionForward = mapping
				.findForward("preSaveOrUpdate");
		if (logger.isDebugEnabled()) {
			logger.debug("preUpdateOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * ִ�о���ĸ���һ����¼�Ĳ���.(ʵ�����Ǹ���id����һ�ʼ�¼�ĸ���)
	 * ��ΪmenuItem_InputOrEdit.jspҳ������menuItem.id ���ݵ��ύ
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

		// Ч��õ�������.
		ActionErrors errors = form.validate(mapping, request);
		if (!errors.isEmpty()) {
			// ���������Ϣ��ͬʱ�ص�����,�޸Ľ�����
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
		saveMessages(request, messages);// ҳ����ʹ��<html:message .../>���

		request.setAttribute("refresh", "afterUpdateOne");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("updateOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * ִ�о���ĸ���һЩ��¼�Ĳ��������������������Ҫ���µ��ֶΡ�
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

		// Ч��õ�������.
		ActionErrors errors = form.validate(mapping, request);
		if (!errors.isEmpty()) {
			// ���������Ϣ��ͬʱ�ص�����,�޸Ľ�����
			saveErrors(request, errors);

			// �ȴ����� �������������� ���ܡ�
			// to do �������� ����
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
		saveMessages(request, messages);// ҳ����ʹ��<html:message .../>���

		request.setAttribute("refresh", "afterUpdateSome");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("updateSome() - end");
		}
		return returnActionForward;
	}

	/**
	 * ���ݱ��е������Ƿ������ݿ����Ѿ�ӵ��, Ȼ���Զ��ж������ӻ��Ǹ�������. ��ʱû���ô���
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
		saveMessages(request, messages);// ҳ����ʹ��<html:message .../>���

		request.setAttribute("refresh", "afterSaveOrUpdateOne");
		ActionForward returnActionForward = mapping.findForward("success");
		if (logger.isDebugEnabled()) {
			logger.debug("saveOrUpdateOne() - end");
		}
		return returnActionForward;

	}

	/**
	 * ���ݸ�����idɾ��һ����¼.
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
	 * ���ݸ�����id����ɾ����¼.
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

		// ɾ��ʧ�ܵĻ�
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
	 * ��ɾ��һЩ��¼ �ƶ�ɾ��������ʱ��,����Щɾ����������ʼֵ.
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

		// ��ʼ��һЩ��ѯʱ��ȱʡ����
		DynaActionForm menuItemForm = (DynaActionForm) form;
		MenuItem menuItem = (MenuItem) menuItemForm.get("menuItem");
		menuItem.setName("menu1");
		menuItem.setTitle("�˵�1");
		menuItemForm.set("menuItem", menuItem);

		// ������һ��Ҫִ�еķ�����
		request.setAttribute("method", "removeSome");
		ActionForward returnActionForward = mapping
				.findForward("preRemoveSome");
		if (logger.isDebugEnabled()) {
			logger.debug("preRemoveSome() - end");
		}
		return returnActionForward;
	}

	/**
	 * ִ�о����ɾ��һЩ��¼�Ĳ���.
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

		// ����domain object ���: from ������ where ....
		String query_condition = new ActionUtil_BlurQuery().getQueryCondition(menuItem);

		_service.removeSomeMenuItems(query_condition);

		// ִ���ƺ�����Ϣ�ķ�����
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
	 * ʲôҲ������������������ת��
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
	 * �������˵�(�����˵��������������أ�������������ֲ˵�)
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

		// �����˵��⡣
		MenuRepository repository = buildRepository();

		request.setAttribute("repository", repository);

		
		ActionForward returnActionForward = mapping.findForward(Menu_Displayer);
		if (logger.isDebugEnabled()) {
			logger.debug("getMainMenu() - end");
		}
		return returnActionForward;

	}
	
	/**
	 * ������ʾ��ͬ���Ĳ˵��� �Ӻ�̨���ݿ��ж�ȡ�˵������ݣ�����һ��menu respository ,Ȼ��ǰ̨����ʾ��
	 * �������õ����Ͷ�����뵽request�С�
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

		// Menu_Displayer�ķ����XTree,Simple,DropDown,
		// CoolMenu,CoolMenu4,ListMenu,TabbedMenu.
		// ȱʡ����ΪXTree���
		String Menu_Displayer = request.getParameter("style");
		if (Menu_Displayer == null) {
			Menu_Displayer = "XTree";
		}

		// �����˵��⡣
		MenuRepository repository = buildRepository();

		request.setAttribute("repository", repository);

		// Menu_Displayer�ķ����XTree,Simple,DropDown,
		// CoolMenu,CoolMenu4,ListMenu,TabbedMenu.
		// ȱʡ����ΪXTree���
		ActionForward returnActionForward = mapping.findForward(Menu_Displayer);
		if (logger.isDebugEnabled()) {
			logger.debug("getMenuRepository() - end");
		}
		return returnActionForward;

	}

	/**
	 * �Ӻ�̨���ݿ��ж�ȡ�˵������ݣ�����һ���˵���javascript ,Ȼ��ǰ̨����ʾ�� �������õ�javascript���뵽request�С�
	 * ����һ����Ư���Ĳ˵���ʾ��ʽ��ͨ�����ݿ��������ɡ�
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

		// �����˵��⡣
		MenuRepository repository = buildRepository();

		// ���ݲ˵��ֿ��е��������ɣ����������˵�ʹ�õ�javascript��䡣
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
	 * ���ݲ˵��ֿ��е��������ɣ���>�����˵���ʱ����Ҫʹ�õ�javascript��䡣
	 * 
	 * @param request
	 * @param repository
	 * @return Created on 2005-8-8 11:46:35
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	private StringBuffer produceMenuJavascript(HttpServletRequest request,
			MenuRepository repository) {
		// �õ������˵���
		List top_menus = repository.getTopMenus();
		StringBuffer sb = new StringBuffer();
		int global_submenus = 0;

		for (int i = 0; i < top_menus.size(); i++) { // ���˵�����
			MenuComponent rootMenu = (MenuComponent) top_menus.get(i);

			sb.append("rootMenu" + (i + 1) + "=new mMenu(");// ��ͷ
			sb.append("'" + convert(rootMenu.getTitle()) + "',");// caption

			// ���pageû�����ӣ���ô�Ͳ�Ҫ���ӣ����򣬼�������
			if (convert(rootMenu.getPage()).equals("")) { // command
				sb.append("'',");
			} else {
				sb.append("'" + request.getContextPath()
						+ convert(rootMenu.getPage()) + "',");
			}

			// ���target���Ա�����ڣ�self,parent,blank,top

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
			// pos=0 ͼ������ߣ�pos=1ͼ�����ұߣ�pos=2 ͼ��Ϊ����,pos=''û��ͼ��
			sb.append("'" + pos + "',");// pos
			sb.append("'0',");// flag='0' �����Ǹ��˵�
			sb.append("'" + convert(rootMenu.getName()) + "'");// id
			sb.append("); \r\n");// ��β

			// Ѱ�����Ӳ˵���

			MenuComponent[] sub_menus = rootMenu.getMenuComponents();
			// �Ӳ˵������

			for (int j = 0; j < sub_menus.length; j++) {
				// �ж��Ƿ����Ӳ˵����еĻ������´���
				if (sub_menus[j].getComponents().size() != 0) {
					global_submenus = global_submenus + 1;
					int initial_value = global_submenus;
					sb.append("msub" + initial_value + "=new mMenuItem(");
					sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption
					// command
					// ���pageû�����ӣ���ô�Ͳ�Ҫ���ӣ����򣬼�������
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

					sb.append("false,");// isline��true��ʾ��һ�п��У�����ʾ�κκ�����Ϣ
					sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
					sb.append("'1',");// ��ʾ�ڸò˵�����û���Ӳ˵�,!null��ʾ���Ӳ˵�

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
					// pos=0 ͼ������ߣ�pos=1ͼ�����ұߣ�pos=2 ͼ��Ϊ����,pos=''û��ͼ��
					sb.append("'" + submenu_pos + "',");// pos
					sb.append("'1'");// flag='1' ������ ��ǰ�˵����Ӳ˵�

					sb.append(");\r\n");

					// �Ը��˵����Ӳ˵����Ӳ˵�����
					global_submenus = produce_submenu(sb, sub_menus[j],
							global_submenus, request);

					sb.append("rootMenu" + (i + 1) + ".addItem(msub"
							+ initial_value + ");\r\n");

				} else {

					sb.append("rootMenu" + (i + 1) + ".addItem(new mMenuItem(");
					sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption

					// command
					// ���pageû�����ӣ���ô�Ͳ�Ҫ���ӣ����򣬼�������
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

					sb.append("false,");// isline��true��ʾ��һ�п��У�����ʾ�κκ�����Ϣ
					sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
					// �˵�����ʾ�����˵���ʾ��Ϣ��ʾ��IE�е�״̬���С�

					sb.append("null,"); // level ��null ��ʾ�ڸò˵�����û���Ӳ˵�,!null��ʾ���Ӳ˵�

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
					// pos=0 ͼ������ߣ�pos=1ͼ�����ұߣ�pos=2 ͼ��Ϊ����,pos=''û��ͼ��
					sb.append("'" + submenu_pos + "',");// pos
					sb.append("'1',");// flag='1' �����ǵ�ǰ�˵����Ӳ˵�
					sb.append("'" + convert(sub_menus[j].getParent().getName())
							+ "'");// parentMenu
					// ��ø���˵���id���롣
					sb.append("));\r\n");

					//
					// sb.append("'" +
					// convert(sub_menus[j].getParent().getName())
					// + "'");// parentMenu
					// ��ø���˵���id���롣

				}
			}

		}
		return sb;
	}

	/**
	 * ����ת�����ݿ��е�target����Ϊ ���ɲ˵���Ҫ��target���ݡ�
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
	 * ��ָ��Ĵ���
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
	 * �ݹ麯���������Ӳ˵���javascript�������ɡ�
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
		// �Ӳ˵������
		MenuComponent[] sub_menus = component.getMenuComponents();
		int origin_submenus = global_submenus;
		for (int j = 0; j < sub_menus.length; j++) {
			// �ж��Ƿ����Ӳ˵����еĻ������´���
			if (sub_menus[j].getComponents().size() != 0) {
				global_submenus = global_submenus + 1;
				int initial_value = global_submenus;
				sb.append("msub" + initial_value + "=new mMenuItem(");
				sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption

				// command
				// ���pageû�����ӣ���ô�Ͳ�Ҫ���ӣ����򣬼�������
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

				sb.append("false,");// isline��true��ʾ��һ�п��У�����ʾ�κκ�����Ϣ
				sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
				sb.append("'1',");// ��ʾ�ڸò˵�����û���Ӳ˵�,!null��ʾ���Ӳ˵�

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
				// pos=0 ͼ������ߣ�pos=1ͼ�����ұߣ�pos=2 ͼ��Ϊ����,pos=''û��ͼ��
				sb.append("'" + submenu_pos + "',");// pos
				sb.append("'1'");// flag='1' ������ ��ǰ�˵����Ӳ˵�
				sb.append(");\r\n");

				// �Ը��˵����Ӳ˵����Ӳ˵�����
				global_submenus = produce_submenu(sb, sub_menus[j],
						global_submenus, request);

				sb.append("msub" + origin_submenus + ".addItem(msub"
						+ initial_value + ");\r\n");

			} else {

				sb.append("msub" + (origin_submenus)
						+ ".addItem(new mMenuItem(");
				sb.append("'" + convert(sub_menus[j].getTitle()) + "',");// caption

				// command
				// ���pageû�����ӣ���ô�Ͳ�Ҫ���ӣ����򣬼�������
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

				sb.append("false,");// isline��true��ʾ��һ�п��У�����ʾ�κκ�����Ϣ
				sb.append("'" + convert(sub_menus[j].getToolTip()) + "',");// statustxt;
				// �˵�����ʾ�����˵���ʾ��Ϣ��ʾ��IE�е�״̬���С�

				sb.append("null,"); // level ��null ��ʾ�ڸò˵�����û���Ӳ˵�,!null��ʾ���Ӳ˵�

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
				// pos=0 ͼ������ߣ�pos=1ͼ�����ұߣ�pos=2 ͼ��Ϊ����,pos=''û��ͼ��
				sb.append("'" + submenu_pos + "',");// pos
				sb.append("'1'");// flag='1' �����ǵ�ǰ�˵����Ӳ˵�
				sb.append("));\r\n");

				// sb.append("'" + convert(sub_menus[j].getParent().getName())
				// + "'");// parentMenu
				// ��ø���˵���id���롣

				// sb.append("msub" + (origin_submenus-1) + ".addItem(msub"
				// + origin_submenus + ");\r\n");
			}
		}
		return global_submenus;
	}

	/**
	 * �����˵��⣭�����˲˵�֮��ĸ��ӹ�ϵ��
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

		// �������Ͷ���
		MenuRepository repository = new MenuRepository();

		// Get the repository from the application scope - and copy the
		// DisplayerMappings from it.
		MenuRepository defaultRepository = (MenuRepository) getServlet()
				.getServletContext().getAttribute(
						MenuRepository.MENU_REPOSITORY_KEY);
		// ���ȱʡ����ʾ��ʽ��
		repository.setDisplayers(defaultRepository.getDisplayers());

		// �������������ʵ���Բ���Ҫ��

		// ���menuitem���е��������ݡ�
		List rows = _service.getMenuItems();
		
//		List rows=null;
//		
//		//menuItem�е�����ֵ�����
//		for (int i = 0; i < orgin_rows.size(); i++) {
//			MenuItem temp_menu = (MenuItem) rows.get(i);
//			BeanUtils.
//		}

		// ��ʼ�����˵�֮��ĸ��ӹ�ϵ��
		for (int i = 0; i < rows.size(); i++) {
			MenuComponent mc = new MenuComponent();
			MenuItem menu = (MenuItem) rows.get(i);
			BeanUtils.copyProperties(mc, menu);
			String name = menu.getName();
			String parent = menu.getParent_name();

			// �鿴��Ϣ�Ƿ���ȷ
			if (logger.isDebugEnabled()) {
				logger.debug(name + ", parent is: " + parent);
				if (parent != null)
					logger.debug("parent_name's length:" + parent.length());
			}

			// ��parent!=null����parent!=�ո�Ȼ����Լ�������������ĸ��˵�,Ȼ���ټ�������˵���repository.addMenu(mc);��
			if (parent != null && !parent.trim().equals("")) {
				parent = parent.trim();
				MenuComponent parentMenu = repository.getMenu(parent);
				if (parentMenu == null) {

					// �鿴��Ϣ�Ƿ���ȷ
					if (logger.isDebugEnabled()) {
						logger.debug("parentMenu '" + parent
								+ "' doesn't exist!");
					}

					// create a temporary parentMenu
					// ����һ����ʱ�ĸ��˵���
					parentMenu = new MenuComponent();
					parentMenu.setName(parent);

					// ����˵����С�
					repository.addMenu(parentMenu);
				}

				// �������ӹ�ϵ
				mc.setParent(parentMenu);
			}

			// 1.���û�и��˵�����parent=null����parent=�ո񣩣�ֱ�Ӽ����һ����Ĳ˵����У�Ҳ���Ǹ��˵���
			// 2.�и��˵��Ļ������úù�ϵ���ټ���ò˵���
			repository.addMenu(mc);
		}
		return repository;
	}

}
