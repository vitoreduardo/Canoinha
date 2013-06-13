<%@ include file="/WEB-INF/_cabecalho.jsp"%>
<%@ include file="/WEB-INF/_menu.jsp"%>

<div id="content">
	<!-- Products -->
	<div class="products">
		<h3>Lista de Produtos</h3>
		<ul id="itens_produtos">
			
			<c:forEach var="produto" items="${produtos}">			
			<li>
				<div class="product">
					<a href="ProdutoServlet?codigoProduto=${produto.getId()}&acao=MostrarDetalheProduto" class="info">
						<span class="holder">
							<img src="css/images/image01.jpg" alt="" />
							<span class="book-name">
				   				 <c:out value="${fn:substring(produto.getNome(),1,40)}"></c:out>
							</span>
							<span class="description">
								<c:out value="${fn:substring(produto.getInformacoes(),1,90)}"></c:out>
							</span>
						</span>
					</a>
					<a href="ProdutoServlet?codigoProduto=${produto.getId()}&acao=MostrarDetalheProduto" class="buy-btn">now<span class="price"><span class="low">$</span>
						<c:out value="${produto.getPrecoDeVenda()}"></c:out>
					<span class="high">00</span></span></a>
				</div>
			</li>
			</c:forEach>
			
																									
		</ul>
		<!-- End Products -->
	</div>
	<!-- Products -->
</div>

<%@ include file="/WEB-INF/_rodape.jsp"%>