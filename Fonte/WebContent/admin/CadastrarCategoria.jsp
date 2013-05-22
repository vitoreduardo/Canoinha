<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="js/jquery.jcarousel.min.js"></script>
<title>Cadastro de Categoria</title>
</head>
<body>
	<form class="form-horizontal" method="post"
		action="/Canoinha/AdicionarCategoriaServlet" id="form_prepare">
		<fieldset>
			<legend>Cadastro de Categoria</legend>
			Id:<br> <label for="nome">Nome:</label> <input id="nome"
				type="text" name="nome"><br> <input type="submit"
				value="Cadastrar" name="cadastrar"> <input type="reset"
				value="Limpar">
		</fieldset>
	</form>
</body>
</html>