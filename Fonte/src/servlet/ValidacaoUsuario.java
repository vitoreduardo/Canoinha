package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.TipoUsuario;
import model.Usuario;
import controller.LoginController;

public class ValidacaoUsuario implements Filter{

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filter) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;  
        HttpServletResponse res = (HttpServletResponse) response;
        String url = req.getServletPath();
        boolean valido = false;
        if(url.endsWith("/index.jsp") || url.endsWith(".css") || url.endsWith(".js")||url.endsWith("/LoginServlet")||
           url.endsWith("_cabecalho.jsp")||url.endsWith("_menu.jsp")||url.endsWith("_rodape.jsp")||
           url.endsWith(".png") || url.endsWith(".gif")|| url.endsWith("logout.jsp")||
           url.endsWith("ProdutoServlet")||url.endsWith("CategoriaServlet")){
        	valido = true;
        }
        
        HttpSession session = req.getSession();  
        Usuario user = (Usuario) session.getAttribute("administrador");  
        if(user!=null || valido){
        	filter.doFilter(request, response);
        	return;
        }  
        res.sendRedirect("http://localhost:8080/Canoinha/index.jsp");
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

}
