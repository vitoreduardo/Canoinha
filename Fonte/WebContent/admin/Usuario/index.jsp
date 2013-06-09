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
				<th>CPF</th>
			</tr>
		</thead>
		<tbody>
			<%
			Conexao conn = null;
			conn = new Conexao();

			DaoUsuario dao = new DaoUsuario(conn);
			List<Usuario> listUsuario = dao.buscar();
			for (Usuario usuario : listUsuario) {
			%>
			<tr>
				<td><%=usuario.getId()%></td>
				<td><%=usuario.getNome()%></td>
				<td><%=usuario.getCpf()%></td>
				<td><a href=/Canoinha/UsuarioServlet?id=<%=usuario.getId()%>&acao=Alterar>Atualizar <i class="icon-edit"></i></a></td>
				<td><a href=/Canoinha/UsuarioServlet?id=<%=usuario.getId()%>&acao=Excluir>Excluir <i class="icon-trash"></i></a></td>
			</tr>
			<%
				}
			%>
		</tbody>
		</form>
	</table>
	</div>
<%@ include file="/WEB-INF/rodape.jsp"%>