<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
		  <form class="form-horizontal" method="post" action="" id="form_prepare">
			 <fieldset>
				<legend>Cadastro de Usuario</legend>
				  Id:<br>
				  <label for="nome">Nome:</label>
				  <input id="nome" type="text" name="nome"><br>
				  <label for="endereco">Endereco:</label>
				  <input id="endereco" type="text" name="endereco"><br>
				  
				  
				  
				  	private int id;
	private String nome;
	private Endereco endereco;
	private String cpf;
	private String email;
	private String senha;
	private Date dataNascimento;
	private TipoUsuario tipo;
				  
				  <input type="submit" value="Cadastrar" name="cadastrar">
				  <input type="reset" value="Limpar">
			  </fieldset>
		  </form>
</body>
</html>