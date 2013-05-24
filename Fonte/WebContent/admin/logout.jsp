
<% 
request.getSession().removeAttribute("usuario");
response.sendRedirect("../index.jsp");
%>