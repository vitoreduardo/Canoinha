<%@ include file="/WEB-INF/_cabecalho.jsp"%>
<%@ include file="/WEB-INF/_menu.jsp"%>
<div id="content">
	<div class="span10">
      <form method="post" action="UsuarioServlet">
	     <fieldset>
		    <legend>Cadastro Usuário</legend>
			  <label for="nome">Nome:</label>
			  <input id="nome" type="text" name="nome">
			  <label for="rua">Rua:</label>
			  <input id="rua" type="text" size="60" name="rua">
			  <label for="numero">Número:</label>
			  <input type="text" name="numero"><br>
			  <label for="bairro">Bairro:</label>
			  <input id="bairro" type="text" name="bairro">
			  <label for="cidade">Cidade:</label>
			  <input id="cidade" type="text" name="cidade"><br>
			  <label for="cep">Cep:</label>
			  <input id="cep" type="text" name="cep">
			  Estado: <select name="estado" id="estado">
			  			 <option value="AC">AC</option>
			  			 <option value="AL">AL</option>
			  			 <option value="AP">AP</option>
			  			 <option value="AM">AM</option>
						 <option value="BA">BA</option>
						 <option value="CE">CE</option>
						 <option value="DF">DF</option>
						 <option value="ES">ES</option>
						 <option value="GO">GO</option>
						 <option value="MA">MA</option>
						 <option value="MT">MT</option>
						 <option value="MS">MS</option>
						 <option value="RN" selected="selected">RN</option>
						 <option value="MG">MG</option>
						 <option value="PA">PA</option>
						 <option value="PB">PB</option>
						 <option value="PA">A</option>
						 <option value="BA">BA</option>
					  </select><br>
			  <label for="cpf">CPF:</label>
			  <input id="cpf" type="text" name="cpf"><br>
			  <label for="email">E-mail:</label>
			  <input id="email" type="text" name="email"><br>
			  <label for="senha">senha: </label>
			  <input id="senha" type="password" name="senha"> <br>
			  <label for="dtnascimento">Data Nascimento: </label>
			  <input id="dtnascimento" type="text" name="dtNascimento"><br>
			  <input type="hidden" name="acao" value="CadastrarUsuario">
			  <input type="hidden" name="tipoUsuario" value="Usuario">
			  <input class="btn btn-primary" type="submit" value="Cadastrar">
			  <input class="btn" type="reset" value="Limpar">
		  </fieldset>
	  </form>
	</div>
</div>
<%@ include file="/WEB-INF/_rodape.jsp"%>