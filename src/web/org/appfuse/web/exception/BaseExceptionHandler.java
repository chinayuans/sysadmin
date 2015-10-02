/*
 * project name : sysadmin
 * package name : org.appfuse.web.exception
 * file    name : BaseExceptionHandler.java
 * class   name : BaseExceptionHandler
 * Created on 2005-10-19 8:58:24
 * creator ---Joson Yuan
 * author comments:
 * 
 */
package org.appfuse.web.exception;

import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;
import org.appfuse.service.exception.BaseServiceException;


public class BaseExceptionHandler extends ExceptionHandler {

   public ActionForward execute(Exception ex,
                                 ExceptionConfig config,
                                 ActionMapping mapping,
                                 ActionForm formInstance,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws ServletException {

      ActionMessages errors = new ActionMessages();
      ActionForward forward = null;
      ActionMessage error = null;
      String property = null;

      /* Get the path for the forward either from the exception element
       * or from the input attribute.
       */
      String path = null;
      if (config.getPath(  ) != null) {
       path = config.getPath(  );
      }else{
        path = mapping.getInput(  );
      }
      // Construct the forward object
      forward = new ActionForward(path);

      /* Figure out what type of exception has been thrown. The Struts
       * AppException is not being used in this example.
       */
      if( ex instanceof BaseServiceException) {
        /*// This is the specialized behavior
        BaseException baseException = (BaseException)ex;
        String messageKey = baseException.getMessageKey(  );
        Object[] exArgs = baseException.getMessageArgs(  );
        if ( exArgs != null && exArgs.length > 0 ){
          // If there were args provided, use them in the ActionError
          error = new ActionMessage( messageKey, exArgs );
        }else{
          // Create an ActionError without any arguments
          error = new ActionMessage( messageKey );
        }*/

         processBaseException( (BaseServiceException)ex, config,request,forward, property,errors);
        // See if this exception contains a list of subexceptions
         List exceptions = ((BaseServiceException)ex).getExceptions(  );
         if (exceptions != null && !exceptions.isEmpty(  ) ){
           Iterator iter = exceptions.iterator(  );

           while( iter.hasNext(  ) ){
            // All subexceptions must be BaseExceptions
                 BaseServiceException subException = (BaseServiceException)iter.next(  );

                 processBaseException(subException, config,request,forward, property,errors);
           }
         }
      }else{
        error = new ActionMessage(config.getKey(  ));
        property = error.getKey(  );
        storeException(request, property, error, forward, config.getScope(  ),errors);
      }

      return forward;
    }

    protected void processBaseException( BaseServiceException ex,
                                 ExceptionConfig config,
                                 HttpServletRequest request,
                                 ActionForward forward,String property,ActionMessages errors){

        String messageKey = ex.getMessageKey(  );
        ActionMessage error=null;
        Object[] exArgs = ex.getMessageArgs(  );
        if ( exArgs != null && exArgs.length > 0 ){
          // If there were args provided, use them in the ActionError
          error = new ActionMessage( messageKey, exArgs );
        }else{
          // Create an ActionError without any arguments
          error = new ActionMessage( messageKey );
        }
        storeException(request, property, error, forward, config.getScope(  ),errors);
  }

   protected void storeException(HttpServletRequest request,
                        String property,
                        ActionMessage error,
                        ActionForward forward,
                        String scope,ActionMessages errors) {


        errors.add(property, error);

        if ("request".equals(scope)){
            request.setAttribute(Globals.ERROR_KEY, errors);
        } else {
            request.getSession().setAttribute(Globals.ERROR_KEY, errors);
        }
    }

}
