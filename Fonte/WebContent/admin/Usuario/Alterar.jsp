<%@ include file="/WEB-INF/admin/cabecalho.jsp"%>
	<div class="span10">
      <form method="post" action="/Canoinha/UsuarioServlet">
	     <fieldset>
		    <legend>Cadastro Usuário</legend>
			  <label>Id:</label>
			  <input id="id" type="text" name="id" readonly="readonly" value=${usuarios.id}>
			  <label for="nome">Nome:</label>
			  <input id="nome" type="text" name="nome" value=${usuarios.nome}>
			  <label for="rua">Rua:</label>
			  <input id="rua" type="text" size="60" name="rua" value=${usuarios.endereco.rua}>
			  <label for="numero">Número:</label>
			  <input type="text" name="numero" value=${usuarios.endereco.numero}><br>
			  <label for="bairro">Bairro:</label>
			  <input id="bairro" type="text" name="bairro" value=${usuarios.endereco.bairro}>
			  <label for="cidade">Cidade:</label>
			  <input id="cidade" type="text" name="cidade" value=${usuarios.endereco.cidade}><br>
			  <label for="cep">Cep:</label>
			  <input id="cep" type="text" name="cep" value=${usuarios.endereco.cep}>
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
			  <input id="cpf" type="text" name="cpf" value=${usuarios.cpf}><br>
			  <label for="email">E-mail:</label>
			  <input id="email" type="text" name="email" value=${usuarios.email}><br>
			  <label for="senha">senha: </label>
			  <input id="senha" type="password" name="senha" value=${usuarios.senha}> <br>
			  <label for="dtnascimento">Data Nascimento: </label>
			  <input id="dtnascimento" type="text" name="dtNascimento"><br>
			  <input type="hidden" name="acao" value="AlterarUsuario">
			  <input type='hidden' name='tipoUsuario' value='Administrador'>
			  <input class="btn btn-primary" type="submit" value="Alterar" name="alterar">
			  <input class="btn" type="reset" value="Limpar">
		  </fieldset>
	  </form>
	</div>
<%@ include file="/WEB-INF/admin/rodape.jsp"%>