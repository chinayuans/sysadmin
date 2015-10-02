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
 * 以下BaseAction 仅仅是为了告诉大家如何对Action进行编程, 在实际编程的时候要全部覆盖所有的方法.进行重写.
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

		// **********开始---以下代码表示如何获取一条详细记录的代码
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
		// **********结束---以下代码表示如何获取一条详细记录的代码

		request.setAttribute("data", return_object);
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

		// ********开始

		List objects = _service.getObjects(object);

		if (objects != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < objects.size(); i++) {
					BaseObject object = (BaseObject) objects.get(i);
					logger.debug("object id:" + object.getId());
				}
			}
		}
		// ********结束

		request.setAttribute("data", objects);
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

		// ********开始
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

		// ********结束

		request.setAttribute("data", objects);
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

		// ******开始
		DynaActionForm userForm = (DynaActionForm) form;

		object = (User) userForm.get("object");

		object.setFirstName("Bill");
		object.setLastName("Gate");
		userForm.set("object", object);

		// *******结束

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
		DynaActionForm userForm = (DynaActionForm) form;
		_service.save((BaseObject) userForm.get("object"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.saved"));
		saveMessages(request, messages);// 页面中使用<html:message .../>输出
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

		// ********** 开始
		DynaActionForm userForm = (DynaActionForm) form;

		String id = request.getParameter("id");

		object = new User();
		object.setId(id);

		BaseObject return_object = _service.getObject(object);
		userForm.set("object", return_object);

		// ***********结束

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
	 * 执行具体的更新一条记录的操作.
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

		// *********开始
		DynaActionForm userForm = (DynaActionForm) form;
		BaseObject object = (BaseObject) userForm.get("object");
		_service.update(object);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.saved"));
		saveMessages(request, messages);// 页面中使用<html:message .../>输出

		// **********结束

		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("updateOne() - end");
		}
		return returnActionForward;
	}

	/**
	 * 根据表单中的数据是否在数据库中已经拥有, 然后自动判断是增加还是更新数据.
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

		// ***** 开始
		DynaActionForm userForm = (DynaActionForm) form;
		_service.saveOrUpdate((User) userForm.get("object"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.saved"));
		saveMessages(request, messages);// 页面中使用<html:message .../>输出

		// ***** 结束

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
			logger.debug("removeOne() - start");
		}

		// *****开始
		String id = request.getParameter("id");
		BaseObject object = new BaseObject();
		object.setId(id);

		_service.remove(object);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.deleted"));
		saveMessages(request, messages);

		// *******结束

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
	public ActionForward removeOneById(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("removeOneById() - start");
		}

		// *****开始
		String[] ids = (String[]) request.getParameterValues("ids");

		// 删除失败的话
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

		// *******结束

		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("removeOneById() - end");
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

		// ******开始
		String remove_condition = request.getParameter("remove_condition");
		_service.removeSome(remove_condition);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.deleted"));
		saveMessages(request, messages);

		// ******结束

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

		// ********开始
		BaseObject object = new BaseObject();
		_service.removeAll(object);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"object.deleted"));
		saveMessages(request, messages);

		// ********结束

		ActionForward returnActionForward = queryAll(mapping, form, request,
				response);
		if (logger.isDebugEnabled()) {
			logger.debug("removeAll() - end");
		}
		return returnActionForward;
	}

	/**
	 * 另外一种获得service的方法。相当于ServiceLocator
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
