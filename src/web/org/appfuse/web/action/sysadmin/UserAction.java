/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : UserAction.java
 * class   name : UserAction
 * Created on 2005-7-22 18:53:50
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.action.sysadmin;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.DynaActionForm;
import org.appfuse.model.sysadmin.User;
import org.appfuse.model.sysadmin.UserStatus;
import org.appfuse.service.base.BaseService;
import org.appfuse.service.sysadmin.UserManagerService;
import org.appfuse.web.action.base.BaseAction;
import org.appfuse.web.action.util.ActionUtil_BlurQuery;

public class UserAction extends BaseAction {
	private static final Logger logger = Logger.getLogger(UserAction.class);

	private UserManagerService user_service = null;
	
	private BaseService _service = null;

	public void setUserManagerService(UserManagerService service) {
		if (logger.isDebugEnabled()) {
			logger.debug("setUserManagerService() - start");
		}

		this.user_service = service;

		if (logger.isDebugEnabled()) {
			logger.debug("setUserManagerService() - end");
		}
	}

	public void setService(BaseService service) {
		if (logger.isDebugEnabled()) {
			logger.debug("setService() - start");
		}

		this._service = service;

		if (logger.isDebugEnabled()) {
			logger.debug("setService() - end");
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
		String user_id = request.getParameter("id");

		User user = user_service.getUser(user_id);
		if (user == null)
			if (logger.isDebugEnabled()) {
				logger.debug("user -----NullPointerException");
			}
		if (logger.isDebugEnabled()) {
			logger.debug("userid:" + user.getId());
			logger.debug("user firstName:" + user.getFirstName());
			logger.debug("user lastName:" + user.getLastName());
			logger.debug("user birthday:" + user.getBirthday());
		}

		DynaActionForm userForm = (DynaActionForm) form;
		userForm.set("user", user);

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

		List users = user_service.getUsers();

		if (users != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < users.size(); i++) {
					User user = (User) users.get(i);
					logger.debug("user id:" + user.getId());
					logger.debug("user birthday:" + user.getBirthday());
					//logger.debug("user UserStatus:" + user.getUserStatus().getDescription());
				}
			}
		}

		request.setAttribute("data", users);
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
		DynaActionForm userForm = (DynaActionForm) form;
		User user = (User) userForm.get("user");
		user.setFirstName("��һ����");
		user.setLastName("�ڶ�����");
		userForm.set("user", user);
		user.setBirthday(new Date(System.currentTimeMillis()));

		List userstatus_s=_service.getObjects(new UserStatus());
		for (Iterator iter = userstatus_s.iterator(); iter.hasNext();) {
			UserStatus element = (UserStatus) iter.next();
			if (logger.isDebugEnabled()) {
				logger.debug("userstatus name:" + element.getDescription());
				logger.debug("userstatus id:" + element.getId());
			}
		}
		
		request.setAttribute("userstatus_s",userstatus_s);
		
		
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
		DynaActionForm userForm = (DynaActionForm) form;
		User user = (User) userForm.get("user");

		// ����domain object ���: from ������ where ....
		String query_condition = new ActionUtil_BlurQuery().getQueryCondition(user);

		List users = user_service.queryStandard(query_condition);

		if (users != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < users.size(); i++) {
					user = (User) users.get(i);
					logger.debug("user id:" + user.getId());
					logger.debug("user birthday:" + user.getBirthday());
				}
			}
		}

		// ˢ��ʹ��
		request.setAttribute("data", users);
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

		DynaActionForm userForm = (DynaActionForm) form;

		User user = (User) userForm.get("user");

		user.setFirstName("��һ����");
		user.setLastName("�ڶ�����");
		userForm.set("user", user);
		user.setBirthday(new Date(System.currentTimeMillis()));
		
		
		// ������һ��Ҫִ�еķ��� ���浽ҳ����.ȡ���û�״̬
		userForm.set("user",user);
		
		List userstatus_s=_service.getObjects(new UserStatus());
		for (Iterator iter = userstatus_s.iterator(); iter.hasNext();) {
			UserStatus element = (UserStatus) iter.next();
			if (logger.isDebugEnabled()) {
				logger.debug("userstatus name:" + element.getDescription());
				logger.debug("userstatus id:" + element.getId());
			}
		}
		
		request.setAttribute("userstatus_s",userstatus_s);
		
		
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
		DynaActionForm userForm = (DynaActionForm) form;

		User user = (User) userForm.get("user");
		// System.out.println("------"+user.getFirstName());
		// System.out.println("------"+user.getLastName());

		//���������Ϣ
		UserStatus input_userstatus=user.getUserStatus();
		UserStatus output_userstatus=(UserStatus)_service.getObject(input_userstatus);
		user.setUserStatus(output_userstatus);
		
		user_service.saveUser(user);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.saved"));
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

		DynaActionForm userForm = (DynaActionForm) form;

		String user_id = request.getParameter("id");
		User user = user_service.getUser(user_id);

		user.setBirthday(new Date(System.currentTimeMillis()));
		
		userForm.set("user", user);

		
		List userstatus_s=_service.getObjects(new UserStatus());
		for (Iterator iter = userstatus_s.iterator(); iter.hasNext();) {
			UserStatus element = (UserStatus) iter.next();
			if (logger.isDebugEnabled()) {
				logger.debug("userstatus name:" + element.getDescription());
				logger.debug("userstatus id:" + element.getId());
			}
		}
		
		request.setAttribute("userstatus_s",userstatus_s);
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
	 * ִ�о���ĸ���һ����¼�Ĳ���.(ʵ�����Ǹ���id����һ�ʼ�¼�ĸ���) ��Ϊuser_InputOrEdit.jspҳ������user.id
	 * ���ݵ��ύ
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

		DynaActionForm userForm = (DynaActionForm) form;
		User user = (User) userForm.get("user");
		
		//���¹�����Ϣ
		UserStatus input_userstatus=user.getUserStatus();
		UserStatus output_userstatus=(UserStatus)_service.getObject(input_userstatus);
		user.setUserStatus(output_userstatus);		
		
		user_service.updateUser(user);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.saved"));
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

		DynaActionForm userForm = (DynaActionForm) form;
		User user = (User) userForm.get("user");
		user_service.updateUser(user);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.saved"));
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

		DynaActionForm userForm = (DynaActionForm) form;
		user_service.saveOrUpdateUser((User) userForm.get("user"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.saved"));
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

		user_service.removeUser(request.getParameter("id"));
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.deleted"));
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

		user_service.removeUsers(ids);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.deleted"));
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
		DynaActionForm userForm = (DynaActionForm) form;
		User user = (User) userForm.get("user");
		user.setFirstName("��һ����");
		user.setLastName("�ڶ�����");
		userForm.set("user", user);
		user.setBirthday(new Date(System.currentTimeMillis()));

		List userstatus_s=_service.getObjects(new UserStatus());
		for (Iterator iter = userstatus_s.iterator(); iter.hasNext();) {
			UserStatus element = (UserStatus) iter.next();
			if (logger.isDebugEnabled()) {
				logger.debug("userstatus name:" + element.getDescription());
				logger.debug("userstatus id:" + element.getId());
			}
		}
		
		request.setAttribute("userstatus_s",userstatus_s);
		
		
		
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
		DynaActionForm userForm = (DynaActionForm) form;
		User user = (User) userForm.get("user");

		// ����domain object ���: from ������ where ....
		String query_condition = new ActionUtil_BlurQuery().getQueryCondition(user);

		user_service.removeSomeUsers(query_condition);

		// ִ���ƺ�����Ϣ�ķ�����
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.deleted"));
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

		user_service.removeAllUsers();
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"user.deleted"));
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
	 * ��½��֤�û��������롣
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
	public ActionForward login(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("login() - start");
		}
		DynaActionForm userForm = (DynaActionForm) form;
		User user = (User) userForm.get("user");
		
		
//		String query_condition = ActionUtil.getQueryCondition(user);
//		List users =  user_service.queryStandard(query_condition);
		//��������Ļ������⣺��Ϊuser����һ�����Զ���userStatus,����ƴװ��select sql
		//���ʱ����ִ������Բ���������ķ�����
		
		
		List users = _service.query(user);

		ActionForward returnActionForward =null;
		
		// û���������û���������
		if (users.size()==0) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
					"user.login_fail"));
			saveErrors(request, errors);
			returnActionForward= mapping.findForward("login_failure");
			
		}else{
			returnActionForward= mapping.findForward("login_success");
		}
			

		if (logger.isDebugEnabled()) {
			logger.debug("login() - end");
		}
		return returnActionForward;
	}
}
