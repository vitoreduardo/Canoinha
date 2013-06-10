<%@ page import="java.util.*,
dao.*,
model.*,
controller.*"
%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="/Canoinha/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/Canoinha/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/Canoinha/js/jquery-1.2.6.pack.js"></script>
<script type="text/javascript" src="/Canoinha/js/jquery.maskedinput-1.3.min.js"/></script>

<script>
 $(document).ready(function(){
 	$("#cpf").mask("999.999.999-99");
 	$("#cep").mask("99.999-999");
 	$("#dtnascimento").mask("99/99/9999");
 });
</script>

</head>
<body>