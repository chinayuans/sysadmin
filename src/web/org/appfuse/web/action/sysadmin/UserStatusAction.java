/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : UserStatusAction.java
 * class   name : UserStatusAction
 * Created on 2005-7-22 18:53:50
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.action.sysadmin;

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
import org.appfuse.model.sysadmin.UserStatus;
import org.appfuse.service.base.BaseService;
import org.appfuse.service.handleServiceExceptionSample.HandleServiceException;
import org.appfuse.web.action.base.BaseAction;
import org.appfuse.web.action.util.ActionUtil_BlurQuery;

public class UserStatusAction extends BaseAction {
	private static final Logger logger = Logger
			.getLogger(UserStatusAction.class);

	private BaseService _service = null;

	private HandleServiceException exception_service = null;

	public void setUserStatusService(BaseService service) {
		if (logger.isDebugEnabled()) {
			logger.debug("setUserStatusService() - start");
		}

		this._service = service;

		if (logger.isDebugEnabled()) {
			logger.debug("setUserStatusService() - end");
		}
	}

	public void setHandleServiceException(
			HandleServiceException exceptionService) {
		if (logger.isDebugEnabled()) {
			logger.debug("setHandleServiceException() - start");
		}

		exception_service = exceptionService;

		if (logger.isDebugEnabled()) {
			logger.debug("setHandleServiceException() - end");
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             Created on 2005-10-19 10:36:24
	 * @author ---Joson Yuan author comments:
	 * 
	 */
	public ActionForward handleServiceException(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("handleServiceException() - start");
		}

		// ִ�з�����ҵ�񷽷������ִ�гɹ��Ļ���ת�Ƶ�success.����
		// ʹ��sysadmin-struts-config.xml�����õ��쳣������
		// ע�⣺�����쳣�����ַ�ʽ��
		// 1��һ����ʹ�����÷�ʽ�����籾����
		// 2��һ������Action�����Ա�̷�ʽ�����쳣
		// ���磺
		// try{
		// new Model().businessMethod();
		// }catch(BaseException ex){
		// // Log the exception
		//
		// // Create and store the action error
		// ActionMessages errors = new ActionMessages( );
		// ActionMessage newMessage = new ActionMessage( ex.getMessageKey(),
		// ex.getMessageArgs() );
		// errors.add( ActionMessages.GLOBAL_MESSAGE, newMessage );
		// saveErrors( request, errors );
		//
		// // Return an ActionForward for the Failure resource
		// return mapping.findForward( Constants.APP_ERROR_PAGE );
		//
		// }catch( Throwable ex ){
		// // Log the exception
		//
		// // Create and store the action error
		// ActionMessage newError = new ActionMessage( "error.system.error" );
		// ActionMessages errors = new ActionMessages( );
		// errors.add( ActionMessages.GLOBAL_MESSAGE, newError );
		// saveErrors( request, errors );
		//
		// // Return an ActionForward for the system error resource
		// return mapping.findForward( Constants.APP_ERROR_PAGE );
		// }
		// // Forward control to the specified success URI
		// return (mapping.findForward("result"));
		//
		// }

		exception_service.businessMethod();
		ActionForward returnActionForward = mapping.findForward("success");

		if (logger.isDebugEnabled()) {
			logger.debug("handleServiceException() - end");
		}
		return returnActionForward;

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
		String userstatus_id = request.getParameter("id");
		UserStatus userstatus = new UserStatus();
		userstatus.setId(userstatus_id);
		userstatus = (UserStatus) _service.getObject(userstatus);
		if (userstatus == null)
			if (logger.isDebugEnabled()) {
				logger.debug("userstatus -----NullPointerException");
			}
		if (logger.isDebugEnabled()) {
			logger.debug("userstatusrid:" + userstatus.getId());
			logger.debug("userstatus firstName:" + userstatus.getDescription());

		}

		DynaActionForm userstatusForm = (DynaActionForm) form;
		userstatusForm.set("userstatus", userstatus);

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

		UserStatus single_userstatus = new UserStatus();
		List userstatuss = _service.getObjects(single_userstatus);

		if (userstatuss != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < userstatuss.size(); i++) {
					UserStatus userstatus = (UserStatus) userstatuss.get(i);
					logger.debug("userstatus id:" + userstatus.getId());
				}
			}
		}

		request.setAttribute("data", userstatuss);
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
		DynaActionForm userstatusForm = (DynaActionForm) form;
		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");
		userstatus.setDescription("����");
		userstatusForm.set("userstatus", userstatus);

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
		DynaActionForm userstatusForm = (DynaActionForm) form;
		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");

		// ����domain object ���: from ������ where ....
		String query_condition = new ActionUtil_BlurQuery()
				.getQueryCondition(userstatus);

		List userstatuss = _service.query(query_condition);

		if (userstatuss != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < userstatuss.size(); i++) {
					userstatus = (UserStatus) userstatuss.get(i);
					logger.debug("userstatus id:" + userstatus.getId());
				}
			}
		}

		// ˢ��ʹ��
		request.setAttribute("data", userstatuss);
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
		DynaActionForm userstatusForm = (DynaActionForm) form;

		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");

		userstatus.setDescription("����");

		userstatusForm.set("userstatus", userstatus);

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
		DynaActionForm userstatusForm = (DynaActionForm) form;

		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");
		// System.out.println("------"+userstatus.getFirstName());
		// System.out.println("------"+userstatus.getLastName());

		_service.save(userstatus);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.saved"));
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

		DynaActionForm userstatusForm = (DynaActionForm) form;

		String userstatus_id = request.getParameter("id");
		UserStatus userstatus = new UserStatus();
		userstatus.setId(userstatus_id);
		userstatus = (UserStatus) _service.getObject(userstatus);

		userstatusForm.set("userstatus", userstatus);

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
	 * ��Ϊuserstatus_InputOrEdit.jspҳ������userstatus.id ���ݵ��ύ
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

		DynaActionForm userstatusForm = (DynaActionForm) form;
		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");
		_service.update(userstatus);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.saved"));
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

		DynaActionForm userstatusForm = (DynaActionForm) form;
		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");
		_service.update(userstatus);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.saved"));
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

		DynaActionForm userstatusForm = (DynaActionForm) form;
		_service.saveOrUpdate((UserStatus) userstatusForm.get("userstatus"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.saved"));
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
		String userstatus_id = request.getParameter("id");
		UserStatus userstatus = new UserStatus();
		userstatus.setId(userstatus_id);
		_service.remove(userstatus);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.deleted"));
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

		_service.removeSome(new UserStatus(), ids);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.deleted"));
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
		DynaActionForm userstatusForm = (DynaActionForm) form;
		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");

		userstatus.setDescription("����");

		userstatusForm.set("userstatus", userstatus);
		// userstatus.setBirthday(new Date(System.currentTimeMillis()));

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
		DynaActionForm userstatusForm = (DynaActionForm) form;
		UserStatus userstatus = (UserStatus) userstatusForm.get("userstatus");

		// ����domain object ���: from ������ where ....
		// ��ȷ��ѯ
		String query_condition = new ActionUtil_BlurQuery()
				.getQueryCondition(userstatus);

		_service.removeSome(query_condition);

		// ִ���ƺ�����Ϣ�ķ�����
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.deleted"));
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

		_service.removeAll(new UserStatus());
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"userstatus.deleted"));
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
}
