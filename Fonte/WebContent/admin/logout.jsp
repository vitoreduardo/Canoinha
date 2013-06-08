
<% 
request.getSession().removeAttribute("administrador");
request.getSession().removeAttribute("usuario");
request.getSession().removeAttribute("carrinhoDeCompras");
response.sendRedirect("../index.jsp");
%>