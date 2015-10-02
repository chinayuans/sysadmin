/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : BaseAction.java
 * class   name : BaseAction
 * Created on 2005-7-22 18:53:50
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.action.base;

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
import org.apache.struts.actions.DispatchAction;
import org.appfuse.model.base.BaseObject;
import org.appfuse.model.sysadmin.User;
import org.appfuse.service.base.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * ����BaseAction ������Ϊ�˸��ߴ����ζ�Action���б��, ��ʵ�ʱ�̵�ʱ��Ҫȫ���������еķ���.������д.
 * 
 * Created on 2005-7-27 8:38:17
 * 
 * @author ---Joson Yuan author comments:
 * 
 */
public class BaseAction extends DispatchAction {
	private static final Logger logger = Logger.getLogger(BaseAction.class);

	User object = new User();

	private static ApplicationContext ctx = null;

	private BaseService _service = null;

	public void setBaseService(BaseService service) {
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

		// **********��ʼ---���´����ʾ��λ�ȡһ����ϸ��¼�Ĵ���
		String user_id = request.getParameter("id");
		object.setId(user_id);

		BaseObject return_object = _service.getObject(object);
		if (return_object == null)
			if (logger.isDebugEnabled()) {
				logger.debug("object -----NullPointerException");
			}
		if (logger.isDebugEnabled()) {
			logger.debug("object id:" + return_object.getId());
		}
		// **********����---���´����ʾ��λ�ȡһ����ϸ��¼�Ĵ���

		request.setAttribute("data", return_object);
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

		// ********��ʼ

		List objects = _service.getObjects(object);

		if (objects != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < objects.size(); i++) {
					BaseObject object = (BaseObject) objects.get(i);
					logger.debug("object id:" + object.getId());
				}
			}
		}
		// ********����

		request.setAttribute("data", objects);
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

		// ********��ʼ
		// String condition = (String) request.getAttribute("query_condition");
		//
		// if (condition == null) {
		// logger.debug("condition is null pointer");
		// }
		// List objects = _service.query(condition);
		// if (objects != null) {
		// if (logger.isDebugEnabled()) {
		// for (int i = 0; i < objects.size(); i++) {
		// BaseObject object = (BaseObject) objects.get(i);
		// logger.debug("object id:" + object.getId());
		// }
		// }
		// }

		DynaActionForm userForm = (DynaActionForm) form;

		object = (User) userForm.get("object");
		List objects = _service.query(object);
		if (objects != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < objects.size(); i++) {
					BaseObject object = (BaseObject) objects.get(i);
					logger.debug("object id:" + object.getId());
				}
			}
		}

		// ********����

		request.setAttribute("data", objects);
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

		// ******��ʼ
		DynaActionForm userForm = (DynaActionForm) form;

		object = (User) userForm.get("object");

		object.setFirstName("Bill");
		object.setLastName("Gate");
		userForm.set("object", object);

		// *******����

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
		_service.save((BaseObject) userForm.get("object"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.saved"));
		saveMessages(request, messages);// ҳ����ʹ��<html:message .../>���
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

		// ********** ��ʼ
		DynaActionForm userForm = (DynaActionForm) form;

		String id = request.getParameter("id");

		object = new User();
		object.setId(id);

		BaseObject return_object = _service.getObject(object);
		userForm.set("object", return_object);

		// ***********����

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
	 * ִ�о���ĸ���һ����¼�Ĳ���.
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

		// *********��ʼ
		DynaActionForm userForm = (DynaActionForm) form;
		BaseObject object = (BaseObject) userForm.get("object");
		_service.update(object);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.saved"));
		saveMessages(request, messages);// ҳ����ʹ��<html:message .../>���

		// **********����

		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("updateOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * ���ݱ��е������Ƿ������ݿ����Ѿ�ӵ��, Ȼ���Զ��ж������ӻ��Ǹ�������.
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
	public ActionForward saveOrUpdateOne(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("saveOrUpdateOne() - start");
		}

		// ***** ��ʼ
		DynaActionForm userForm = (DynaActionForm) form;
		_service.saveOrUpdate((User) userForm.get("object"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.saved"));
		saveMessages(request, messages);// ҳ����ʹ��<html:message .../>���

		// ***** ����

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
			logger.debug("removeOne() - start");
		}

		// *****��ʼ
		String id = request.getParameter("id");
		BaseObject object = new BaseObject();
		object.setId(id);

		_service.remove(object);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.deleted"));
		saveMessages(request, messages);

		// *******����

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
	public ActionForward removeOneById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("removeOneById() - start");
		}

		// *****��ʼ
		String[] ids = (String[]) request.getParameterValues("ids");

		// ɾ��ʧ�ܵĻ�
		if (ids == null) {
			ActionForward returnActionForward = queryAll(mapping, form,
					request, response);
			if (logger.isDebugEnabled()) {
				logger.debug("removeOneById() - end");
			}
			return returnActionForward;
		}

		BaseObject object = new BaseObject();
		_service.removeSome(object, ids);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.deleted"));
		saveMessages(request, messages);

		// *******����

		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("removeOneById() - end");
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

		// ******��ʼ
		String remove_condition = request.getParameter("remove_condition");
		_service.removeSome(remove_condition);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.deleted"));
		saveMessages(request, messages);

		// ******����

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

		// ********��ʼ
		BaseObject object = new BaseObject();
		_service.removeAll(object);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.deleted"));
		saveMessages(request, messages);

		// ********����

		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("removeAll() - end");
		}
		return returnActionForward;
	}

	/**
	 * ����һ�ֻ��service�ķ������൱��ServiceLocator
	 * 
	 * @param name
	 * @return
	 */
	public Object getBean(String name) {
		if (ctx == null) {
			ctx = WebApplicationContextUtils
					.getRequiredWebApplicationContext(servlet
							.getServletContext());
		}
		return ctx.getBean(name);
	}

}
