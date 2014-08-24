package com.mankind.washers;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.mankind.washers.domain.User;

@Component
public class SessionUserHandlerMethodArgumentResolver implements
		HandlerMethodArgumentResolver {

private static Logger logger = LoggerFactory.getLogger(SessionUserHandlerMethodArgumentResolver.class);
	
	public SessionUserHandlerMethodArgumentResolver(){}

	/**
	 * This method makes the @SessionUser tag possible in Spring MVC controllers.  Grabs the authenticated
	 * user from the SecurityContext. 
	 */
	@Override  
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer arg1, NativeWebRequest webRequest,
			WebDataBinderFactory arg3) throws Exception {

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		
		try {
			if (this.supportsParameter(methodParameter)) {
				
				if ( session.getAttribute("sessionUser") == null
					&& SecurityContextHolder.getContext().getAuthentication() != null 
					&& SecurityContextHolder.getContext().getAuthentication().getPrincipal() != null){
					User sessionUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					logger.info("Adding sessionUser " + sessionUser + " to HttpSession.");
					session.setAttribute("sessionUser", sessionUser);
					
				}
				
				return (User) session.getAttribute("sessionUser");
				
	        } else {
	            return WebArgumentResolver.UNRESOLVED;
	        } 
		} catch (Exception e){
			logger.error("Error resolving @SessionUser annotation: " + e.getMessage());
			return null;
		}
	}

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterAnnotation(SessionUser.class) != null
	              && methodParameter.getParameterType().equals(User.class);
	}

}
