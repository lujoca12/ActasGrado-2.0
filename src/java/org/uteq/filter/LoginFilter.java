package org.uteq.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.uteq.bean.Login;
import java.io.Serializable;
import javax.servlet.http.HttpSession;
import org.uteq.model.Usuario;
import org.uteq.util.SessionUtil;

@WebFilter(urlPatterns = {"/*"})
public class LoginFilter implements Filter, Serializable {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        Usuario usuario = null;

        // Obtengo el bean que representa el usuario desde el scope sesiónDo
        try {
            try{
                usuario = (Usuario) session.getAttribute("usuario");
            }catch(Exception ex){
                ex.getMessage();
            }

            String urlStr = req.getRequestURL().toString().toLowerCase();
            boolean noProteger = noProteger(urlStr);
            System.out.println(urlStr + " - desprotegido=[" + noProteger + "]");
            String url = req.getRequestURI();
            url.substring(0, url.length() - req.getRequestURI().length());

            // Si no requiere protección continúo normalmente.
            if (noProteger(urlStr)) {
                chain.doFilter(request, response);
                return;
            }

            // El usuario no está logueado
            if (usuario == null) {
                res.sendRedirect(req.getContextPath()+"/upa/login/");
                //request.getRequestDispatcher("/upa/login/").forward(request, response);
                System.out.println("No logueado");
            }
            chain.doFilter(request, response);
            System.out.println("Logueado");
        } catch (Exception e) {
            e.getMessage();
        }
        // El recurso requiere protección, pero el usuario ya está logueado.
    }

    private boolean noProteger(String urlStr) {
        /*
		 * Este es un buen lugar para colocar y programar todos los patrones que
		 * creamos convenientes para determinar cuales de los recursos no
		 * requieren protección. Sin duda que habría que crear un mecanizmo tal
		 * que se obtengan de un archivo de configuración o algo que no requiera
		 * compilación.
         */
        if (urlStr.indexOf("/login.xhtml") != -1) {
            return true;
        }
        if (urlStr.indexOf("/upa/login/") != -1) {
            return true;
        }
        if (urlStr.indexOf("/javax.faces.resource/") != -1) {
            return true;
        }
        if (urlStr.indexOf("/newjsf.xhtml") != -1) {
            return true;
        }
        if (urlStr.indexOf("/error/") != -1) {
            return true;
        }
        if (urlStr.indexOf("/borrar/") != -1) {
            return true;
        }
        return false;
    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {
    }

    @Override
    public void destroy() {

    }
}
