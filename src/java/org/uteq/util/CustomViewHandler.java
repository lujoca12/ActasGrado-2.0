/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.util;

import java.util.List;
import java.util.Map;
import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

/**
 *
 * @author Moises
 */
public class CustomViewHandler extends ViewHandlerWrapper {
  private ViewHandler wrappped;

  public CustomViewHandler(ViewHandler wrappped) {
    super();
    this.wrappped = wrappped;
  }

  @Override
  public ViewHandler getWrapped() {
    return wrappped;
  }

  @Override
  public String getActionURL(FacesContext context, String viewId) {
    String url =  super.getActionURL(context, viewId);
    return removeContextPath(context, url);
  }

  @Override
  public String getRedirectURL(FacesContext context, String viewId, Map<String, List<String>> parameters, boolean includeViewParams) {
    String url =  super.getRedirectURL(context, viewId, parameters, includeViewParams);
    return removeContextPath(context, url);
  }

  @Override
  public String getResourceURL(FacesContext context, String path) {
    String url = super.getResourceURL(context, path);
    return removeContextPath(context, url);
  }

  private String removeContextPath(FacesContext context, String url) {
    ServletContext servletContext = (ServletContext) context.getExternalContext().getContext();
    String contextPath = servletContext.getContextPath();
    if("".equals(contextPath)) return url; // root context path, nothing to remove
    return url.startsWith(contextPath) ? url.substring(contextPath.length()) : url;
  }
}