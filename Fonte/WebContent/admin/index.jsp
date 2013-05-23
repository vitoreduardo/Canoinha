<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*,
dao.*,
model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen">
<script src="http://code.jquery.com/jquery.js"></script>
<script src="../js/bootstrap.min.js"></script>
<title>Cadastro de Produtos</title>
</head>
<body>
    <form method="post" id="form_prepare">
	<a href="CadastrarProduto.jsp">Novo Cadastro</a>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
				<th>Preço</th>
			</tr>
		</thead>
		<tbody>
			<%
				Conexao conn = null;
				conn = new Conexao();

				DaoProduto dao = new DaoProduto(conn);
				List<Produto> contatos = dao.buscar();
				for (Produto contato : contatos) {
			%>
			<tr>
				<td><%=contato.getId()%></td>
				<td><%=contato.getNome()%></td>
				<td><%=contato.getPrecoDeVenda()%></td>
				<td><a href=/Canoinha/PreencherFormulario?id=<%=contato.getId()%>>Atualizar</a></td>		
				<td><input type="button" value="Excluir"></td>
			</tr>
			<%
				}
			%>
		</tbody>
		</form>
	</table>
</body>
</html>