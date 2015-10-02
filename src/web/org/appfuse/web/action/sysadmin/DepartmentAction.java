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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");
		department.setName("1����");
		departmentForm.set("department", department);

		
		
		// ������һ��Ҫִ�еķ��� ���浽ҳ����. ȡ����˾��Ϣ
		List companys = _service.getObjects(new Company());
		for (Iterator iter = companys.iterator(); iter.hasNext();) {
			Company element = (Company) iter.next();

			if (logger.isDebugEnabled()) {
				logger.debug("company name:" + element.getName());
				logger.debug("company id:" + element.getId());
			}
		}
		request.setAttribute("companys", companys);
		
		

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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");

		// ����domain object ���: from ������ where ....
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

		// ˢ��ʹ��
		request.setAttribute("data", departments);
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

		DynaActionForm departmentForm = (DynaActionForm) form;

		Department department = (Department) departmentForm.get("department");

		department.setName("1����");

		departmentForm.set("department", department);

		// ������һ��Ҫִ�еķ��� ���浽ҳ����. ȡ����˾��Ϣ
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
		DynaActionForm departmentForm = (DynaActionForm) form;

		Department department = (Department) departmentForm.get("department");
		// System.out.println("------"+department.getFirstName());
		// System.out.println("------"+department.getLastName());

		// �����û�ѡ���company(input_company)��id��Ȼ�����ݿ����ҵ���Ӧ�ļ�¼(output_company),
		// ��output_company��department������� �ﵽһ�Զ�Ĺ���(company 1 ->department m)
		Company input_company = department.getCompany();
		Company output_company = (Company) _service.getObject(input_company);
		department.setCompany(output_company);

		// ͨ��Department�����е�company���� cascade="save-update" �����������ݡ�
		_service.save(department);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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

		DynaActionForm departmentForm = (DynaActionForm) form;

		String department_id = request.getParameter("id");
		Department department = new Department();
		department.setId(department_id);
		department = (Department) _service.getObject(department);

		departmentForm.set("department", department);

		// ������һ��Ҫִ�еķ��� ���浽ҳ����.
		List companys = _service.getObjects(new Company());
		for (Iterator iter = companys.iterator(); iter.hasNext();) {
			Company element = (Company) iter.next();

			if (logger.isDebugEnabled()) {
				logger.debug("company name:" + element.getName());
				logger.debug("company id:" + element.getId());
			}
		}
		request.setAttribute("companys", companys);

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
	 * ��Ϊdepartment_InputOrEdit.jspҳ������department.id ���ݵ��ύ
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

		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");

		// �����û�ѡ���company(input_company)��id��Ȼ�����ݿ����ҵ���Ӧ�ļ�¼(output_company),
		// ��output_company��department������� �ﵽһ�Զ�Ĺ���(company 1 ->department m)
		Company input_company = department.getCompany();
		Company output_company = (Company) _service.getObject(input_company);
		department.setCompany(output_company);

		_service.update(department);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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

		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");
		_service.update(department);

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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

		DynaActionForm departmentForm = (DynaActionForm) form;
		_service.saveOrUpdate((Department) departmentForm.get("department"));

		ActionMessages messages = new ActionMessages();
		messages.add(ActionMessages.GLOBAL_MESSAGE, new ActionMessage(
				"department.saved"));
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
		String department_id = request.getParameter("id");
		Department department = new Department();
		department.setId(department_id);

		// ��������companyΪ�գ���ΪĬ�ϵ��������new Company().�ᵼ����������
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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");
		department.setName("1����");
		departmentForm.set("department", department);

		// ������һ��Ҫִ�еķ��� ���浽ҳ����. ȡ����˾��Ϣ
		List companys = _service.getObjects(new Company());
		for (Iterator iter = companys.iterator(); iter.hasNext();) {
			Company element = (Company) iter.next();

			if (logger.isDebugEnabled()) {
				logger.debug("company name:" + element.getName());
				logger.debug("company id:" + element.getId());
			}
		}
		request.setAttribute("companys", companys);

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
		DynaActionForm departmentForm = (DynaActionForm) form;
		Department department = (Department) departmentForm.get("department");

		// ����domain object ���: from ������ where ....
		// ��ȷ��ѯ
		String query_condition = new ActionUtil_BlurQuery()
				.getQueryCondition(department);

		_service.removeSome(query_condition);

		// ִ���ƺ�����Ϣ�ķ�����
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
