/*
 * project name : equinox-self
 * package name : org.appfuse.web
 * file    name : DepartmentAction.java
 * class   name : DepartmentAction
 * Created on 2005-7-22 18:53:50
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.action.sysadmin;

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
import org.appfuse.model.sysadmin.Company;
import org.appfuse.model.sysadmin.Department;
import org.appfuse.service.base.BaseService;
import org.appfuse.web.action.base.BaseAction;
import org.appfuse.web.action.util.ActionUtil_BlurQuery;

public class DepartmentAction extends BaseAction {
	private static final Logger logger = Logger
			.getLogger(DepartmentAction.class);

	private BaseService _service = null;

	public void setDepartmentService(BaseService service) {
		if (logger.isDebugEnabled()) {
			logger.debug("setDepartmentService() - start");
		}

		this._service = service;

		if (logger.isDebugEnabled()) {
			logger.debug("setDepartmentService() - end");
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
		String department_id = request.getParameter("id");
		Department department = new Department();
		department.setId(department_id);
		department = (Department) _service.getObject(department);
		if (department == null)
			if (logger.isDebugEnabled()) {
				logger.debug("department -----NullPointerException");
			}
		if (logger.isDebugEnabled()) {
			logger.debug("departmentrid:" + department.getId());
			logger.debug("department firstName:" + department.getName());

		}

		DynaActionForm departmentForm = (DynaActionForm) form;
		departmentForm.set("department", department);

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

		Department single_department = new Department();
		List departments = _service.getObjects(single_department);

		if (departments != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < departments.size(); i++) {
					Department department = (Department) departments.get(i);
					logger.debug("department id:" + department.getId());
				}
			}
		}

		request.setAttribute("data", departments);
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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");
		department.setName("1部门");
		departmentForm.set("department", department);

		
		
		// 把这下一步要执行的方法 保存到页面中. 取出公司信息
		List companys = _service.getObjects(new Company());
		for (Iterator iter = companys.iterator(); iter.hasNext();) {
			Company element = (Company) iter.next();

			if (logger.isDebugEnabled()) {
				logger.debug("company name:" + element.getName());
				logger.debug("company id:" + element.getId());
			}
		}
		request.setAttribute("companys", companys);
		
		

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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");

		// 根据domain object 获得: from 对象名 where ....
		String query_condition = new ActionUtil_BlurQuery()
				.getQueryCondition(department);

		List departments = _service.query(query_condition);

		if (departments != null) {
			if (logger.isDebugEnabled()) {
				for (int i = 0; i < departments.size(); i++) {
					department = (Department) departments.get(i);
					logger.debug("department id:" + department.getId());
				}
			}
		}

		// 刷新使用
		request.setAttribute("data", departments);
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

		DynaActionForm departmentForm = (DynaActionForm) form;

		Department department = (Department) departmentForm.get("department");

		department.setName("1部门");

		departmentForm.set("department", department);

		// 把这下一步要执行的方法 保存到页面中. 取出公司信息
		List companys = _service.getObjects(new Company());
		for (Iterator iter = companys.iterator(); iter.hasNext();) {
			Company element = (Company) iter.next();

			if (logger.isDebugEnabled()) {
				logger.debug("company name:" + element.getName());
				logger.debug("company id:" + element.getId());
			}
		}
		request.setAttribute("companys", companys);

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
		DynaActionForm departmentForm = (DynaActionForm) form;

		Department department = (Department) departmentForm.get("department");
		// System.out.println("------"+department.getFirstName());
		// System.out.println("------"+department.getLastName());

		// 根据用户选择的company(input_company)的id，然后到数据库中找到对应的记录(output_company),
		// 将output_company与department相关联。 达到一对多的关联(company 1 ->department m)
		Company input_company = department.getCompany();
		Company output_company = (Company) _service.getObject(input_company);
		department.setCompany(output_company);

		// 通过Department对象中的company属性 cascade="save-update" 级联保存数据。
		_service.save(department);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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

		DynaActionForm departmentForm = (DynaActionForm) form;

		String department_id = request.getParameter("id");
		Department department = new Department();
		department.setId(department_id);
		department = (Department) _service.getObject(department);

		departmentForm.set("department", department);

		// 把这下一步要执行的方法 保存到页面中.
		List companys = _service.getObjects(new Company());
		for (Iterator iter = companys.iterator(); iter.hasNext();) {
			Company element = (Company) iter.next();

			if (logger.isDebugEnabled()) {
				logger.debug("company name:" + element.getName());
				logger.debug("company id:" + element.getId());
			}
		}
		request.setAttribute("companys", companys);

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
	 * 因为department_InputOrEdit.jsp页面上有department.id 数据的提交
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

		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");

		// 根据用户选择的company(input_company)的id，然后到数据库中找到对应的记录(output_company),
		// 将output_company与department相关联。 达到一对多的关联(company 1 ->department m)
		Company input_company = department.getCompany();
		Company output_company = (Company) _service.getObject(input_company);
		department.setCompany(output_company);

		_service.update(department);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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

		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");
		_service.update(department);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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

		DynaActionForm departmentForm = (DynaActionForm) form;
		_service.saveOrUpdate((Department) departmentForm.get("department"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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
		String department_id = request.getParameter("id");
		Department department = new Department();
		department.setId(department_id);

		// 必须设置company为空，因为默认的情况下是new Company().会导致如下问题
		// object references an unsaved transient instance - save the transient
		// instance before flushing
		department.setCompany(null);

		_service.remove(department);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.deleted"));
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

		_service.removeSome(new Department(), ids);
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.deleted"));
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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");
		department.setName("1部门");
		departmentForm.set("department", department);

		// 把这下一步要执行的方法 保存到页面中. 取出公司信息
		List companys = _service.getObjects(new Company());
		for (Iterator iter = companys.iterator(); iter.hasNext();) {
			Company element = (Company) iter.next();

			if (logger.isDebugEnabled()) {
				logger.debug("company name:" + element.getName());
				logger.debug("company id:" + element.getId());
			}
		}
		request.setAttribute("companys", companys);

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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");

		// 根据domain object 获得: from 对象名 where ....
		// 精确查询
		String query_condition = new ActionUtil_BlurQuery()
				.getQueryCondition(department);

		_service.removeSome(query_condition);

		// 执行善后处理，消息的反馈。
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.deleted"));
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

		_service.removeAll(new Department());
		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.deleted"));
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
}
