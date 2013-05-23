<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="produtos" class="model.Produto" scope="request"/>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="../css/bootstrap.css" /> 
	<link href="../css/bootstrap.min.css" rel="stylesheet" media="screen" />	
<script type="text/javascript" src="../js/jquery-1.7.1.js"></script>
    <script>
        $(document).ready(function () {
            $("#btnAdicionarCaracteristica").click(function () {
				var tr = '<tr>'+
					'<td>'+$("input[name=caracteristicas1]").val()+' </td>'+
					'<td>'+$("input[name=caracteristicas2]").val()+' </td>'+
					'</tr>'
				$('#grid').find('tbody').append( tr );
				return false;
			});
		});				
    </script>   

   </head>
	   <body>
		  <form method="post" action="/Canoinha/AdicionarProdutoServlet" id="form_prepare">
			 <fieldset>
				<legend>Alteração de Produtos</legend>
				  Id:<input id="id" type="text" name="id" value=${produtos.id}> <br />
				  <label for="nome">Nome:</label>
				  <input id="nome" type="text" name="nome" value=${produtos.nome}> <br />
				  <label for="pcompra">Preço de compra: </label>
				  <input id="pcompra" type="text" name="preco_compra" value=${produtos.precoDeCompra}> <br/> 
				  <label for="pvenda">Preço de Venda: </label>
				  <input id="pvenda" type="text" name="preco_venda" value=${produtos.precoDeVenda}><br/> 
				  <label for="vlrdesconto">Valor Desconto </label>
				  <input id="vlrdesconto" type="text" name="valor_desconto" value=${produtos.valorDesconto}><br/>
				  <label for="foto">Foto:</label>
				  <input id="foto" name=""> <br>
				  Informações: <br>
				  <textarea rows="10" cols="50" name="informacoes"></textarea><br>
				  <label>Caracteristicas: </label>
				  <input type="text" name="caracteristicas1" id="caracteristicas1">
				  <input type="text" name="caracteristicas2" id="caracteristicas2">
				  <input type="button" id="btnAdicionarCaracteristica" value="Adicionar Caracteristicas"><br>
					<table id="grid" class="table table-hover">
						<thead>
						</thead>
						<tbody>
						</tbody>
					</table>
				  <label for="quantidade">Quantidade Disponivel: </label>
				  <input id="quantidade" type="text" name="quantidade_disponivel" value=${produtos.quantidadeDisponivel}><br>
				  <input type="submit" value="Alterar" name="alterar">
				  <input type="reset" value="Limpar">
			  </fieldset>
		  </form>
	   </body>
</html>