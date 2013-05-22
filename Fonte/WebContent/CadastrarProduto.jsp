<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.css" /> 
	<link href="css/bootstrap.min.css" rel="stylesheet" media="screen" />	
<script type="text/javascript" src="js/jquery-1.7.1.js"></script>
    <script>
        $(document).ready(function () {
            $("#btnAdicionarCaracteristica").click(function () {
				var tr = '<tr>'+
					'<td>'+$("input[name=caracteristicas1]").val()+' class="pr_descricao1" name="pr_Chave"</td>'+
					'<td>'+$("input[name=caracteristicas2]").val()+' class="pr_descricao2" name="pr_Valor"</td>'+
					'</tr>'
				$('#grid').find('tbody').append( tr );
				return false;
			});
		});				
    </script>   

   </head>
	   <body>
		  <form class="form-horizontal" method="post" action="" id="form_prepare">
			 <fieldset>
				<legend>Cadastro de Produtos</legend>
				  Id:<br>
				  <label for="nome">Nome:</label>
				  <input id="nome" type="text" name="nome"><br>
				  <label for="pcompra">Preço de compra: </label>
				  <input id="pcompra" type="text" name="preco_compra"> 
				  <label for="pvenda">Preço de Venda: </label>
				  <input id="pvenda" type="text" name="preco_venda"> 
				  <label for="vlrdesconto">Valor Desconto </label>
				  <input id="vlrdesconto" type="text" name="valor_desconto"><br>
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
				  <input id="quantidade" type="text" name="quantidade_disponivel"><br>
				  <input type="submit" value="Cadastrar" name="cadastrar">
				  <input type="reset" value="Limpar">
			  </fieldset>
		  </form>
	   </body>
</html>