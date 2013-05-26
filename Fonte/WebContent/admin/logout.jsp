
<% 
request.getSession().removeAttribute("administrador");
response.sendRedirect("../index.jsp");
%>