<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/bootstrap.css" rel="stylesheet" type="text/css" />
<script src="../js/bootstrap.min.js"></script>
</head>
<body>
      <form method="post" action="">
	     <fieldset>
		    <legend>Cadastro Usuário</legend>
			  Id:<br>
			  <label for="nome">Nome:</label>
			  <input id="nome" type="text" name=""><br>
			  <label for="rua">Rua:</label>
			  <input id="rua" type="text" size="60" name="">
			  <label for="numero">Número:</label>
			  <input type="text"><br>
			  <label for="bairro">Bairro:</label>
			  <input id="bairro" type="text" name="">
			  <label for="cidade">Cidade:</label>
			  <input id="cidade" type="text" name=""><br>
			  <label for="cep">Cep:</label>
			  <input id="cep" type="text" name="">
			  Estado: <select name="estado" id="estado">
						 <option value="BA">BA</option>
						 <option value="CE">CE</option>
						 <option value="MA">MA</option>
						 <option value="PB">PB</option>
						 <option value="RN" selected="selected">RN</option>
						 <option value="RJ">RJ</option>
						 <option value="RS">RS</option>
					  </select><br>
			  <label for="cpf">CPF:</label>
			  <input id="cpf" type="text" name=""><br>
			  <label for="email">E-mail:</label>
			  <input id="email" type="text" name=""><br>
			  <label for="senha">senha: </label>
			  <input id="senha" type="password" name=""> <br>
			  <label for="dtnascimento">Data Nascimento: </label>
			  <input id="dtnascimento" "type="text" name=""><br>
			  <input type="submit" value="Cadastrar">
			  <input type="reset" value="Limpar">
		  </fieldset>
	  </form>
   </body>
</html>