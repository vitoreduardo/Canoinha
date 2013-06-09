<%@ include file="/WEB-INF/cabecalho.jsp"%>
<c:if test="${not empty param.msg}">
	<div class="alert alert-error">
		${param.msg}
	</div>
</c:if>
<%
   List<Produto> produtos = null;
%>
<%
	produtos = (ArrayList<Produto>) request.getAttribute("listaProdutos");
%>
   <div class="container">
    <form method="post" id="form_prepare">
	<a href="Cadastrar.jsp">Novo Cadastro</a>
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
				for (int i = 0; i < produtos.size(); i++) {
			%>
			<tr>
				<td><%=produtos.get(i).getId()%></td>
				<td><%=produtos.get(i).getNome()%></td>
				<td><%=produtos.get(i).getPrecoDeVenda()%></td>
				<td><a href=/Canoinha/ProdutoServlet?id=<%=produtos.get(i).getId()%>&acao=Alterar>Atualizar <i class="icon-edit"></i></a></td>
				<td><a href=/Canoinha/ProdutoServlet?id=<%=produtos.get(i).getId()%>&acao=Excluir>Excluir <i class="icon-trash"></i></a></td>		
			</tr>
			<%
				}
			%>
			</tbody>
		</form>
	</table>
	</div>
<%@ include file="/WEB-INF/rodape.jsp"%>