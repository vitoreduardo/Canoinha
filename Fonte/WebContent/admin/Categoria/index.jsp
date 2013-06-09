<%@ include file="/WEB-INF/cabecalho.jsp"%>
<c:if test="${not empty param.msg}">
	<div class="alert alert-error">
		${param.msg}
	</div>
</c:if>

	<div class="container">
    <form method="post" id="form_prepare">
	<a href="Cadastrar.jsp">Novo Cadastro</a>
	<table class="table table-striped">
		<thead>
			<tr>
				<th>Id</th>
				<th>Nome</th>
			</tr>
		</thead>
		<tbody>
			<%
			Conexao conn = null;
			conn = new Conexao();

			DaoCategoria dao = new DaoCategoria(conn);
			List<Categoria> listCategoria = dao.buscar();
			for (Categoria categoria : listCategoria) {
			%>
			<tr>
				<td><%=categoria.getId()%></td>
				<td><%=categoria.getNome()%></td>
				<td><a href=/Canoinha/CategoriaServlet?id=<%=categoria.getId()%>&acao=Alterar>Atualizar <i class="icon-edit"></i></a></td>
				<td><a href=/Canoinha/CategoriaServlet?id=<%=categoria.getId()%>&acao=Excluir>Excluir <i class="icon-trash"></i></a></td>
			</tr>
			<%
				}
			%>
		</tbody>
		</form>
	</table>
	</div>
<%@ include file="/WEB-INF/rodape.jsp"%>