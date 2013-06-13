<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="model.TipoUsuario"%>
<%@page import="model.Usuario" %>
<%@page import="java.text.DecimalFormat" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html lang="en-US" xmlns="http://www.w3.org/1999/xhtml" dir="ltr">
<head>
	<title>Canoinha.com - Aqui tem de tudo</title>
	<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
	<link rel="shortcut icon" href="css/images/favicon.ico" />
	<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>	
	
	<script type="text/javascript" src="js/jquery.jcarousel.min.js"></script>
	<!-- TWITTER BOOTSTRAP CSS -->
	<link href="css/bootstrap.css" rel="stylesheet" type="text/css" />
    <!-- TWITTER BOOTSTRAP JS -->
    <script src="js/bootstrap.min.js"></script>
	<!--[if IE 6]>
		<script type="text/javascript" src="js/png-fix.js"></script>
	<![endif]-->
	<script type="text/javascript" src="js/functions.js"></script>	
	<script>
		function carregarMetodosDeInicializacao(){
			postCategorias();
			postProdutos();
		}
	</script>
</head>
<body  onload="carregarMetodosDeInicializacao()">
<%@ include file="/WEB-INF/_carrinhodecompras.jsp"%>
	<!-- Header -->
	<div id="header" class="shell">
		<div id="logo"><h1><a href="#">Canoinha</a></h1><span><br>Aqui tem de tudo!</br></span></div>
		<!-- Navigation -->
		<div id="navigation">			
			<form action="LoginServlet" method="post">
			   <div class="input-append">
				<input name="login_email" type="text" class="field" placeholder="Digite seu Email" />								
				<input name="login_senha" type="password" class="field" placeholder="Digite sua Senha" />
				<button class="btn" type="submit">Login</button>
	           </div>		
			</form>
			<c:if test="${not empty menssagemErro }">			   
			   <div class="alert alert-block alert-error fade in">
            	 <p>"${menssagemErro}"</p>
          	   </div>          	   
			</c:if>	
			<c:if test="${not empty menssagemSucesso }">			   
			   <div class="alert alert-block alert-success fade in">
            	 <p>"${menssagemSucesso}"</p>
          	   </div>          	   
			</c:if>			
		</div>
		<!-- End Navigation -->
		<div class="cl">&nbsp;</div>
		<!-- Login-details -->
		<div id="login-details">
			<p>			
			<c:choose>
				<c:when test="${not empty usuario.nome}">
					<a href="#myModal" data-toggle="modal">
						${usuario.getNome()}
					</a>	
					<a href="admin/logout.jsp">Efetuar Logout(sair)</a>
				</c:when>
				<c:when test="${empty usuario.nome}">
					<a href="cadastroUsuario.jsp" >cadastre-se</a>					
				</c:when>
			</c:choose>			
			</p><p><a href="#" class="cart" ><img src="css/images/cart-icon.png" alt="" /></a>
			<a href="#myModal" class="sum">
					<% 
						double valorTotal = 0;
						List<ItemVenda> itensDeVenda = (List<ItemVenda>)session.getAttribute("carrinhoDeCompras");
						if(itensDeVenda!= null){
							DecimalFormat df = new DecimalFormat("#,###.00");  							
							for(ItemVenda itemVenda: itensDeVenda){  
							   valorTotal += (itemVenda.getQuantidade()*itemVenda.getValor());   
							}  						
							out.print("R$"+df.format(valorTotal));
						}
					%>
			</a></p>
		</div>
		<!-- End Login-details -->
	</div>
	<!-- End Header -->
	<!-- Slider -->
	<div id="slider">
		<div class="shell">
			<ul>
				<li>
					<div class="image">
						<img src="css/images/books.png" alt="" />
					</div>
					<div class="details">
						<h2>Livros de Informática</h2>
						<h3>Oferta Especial</h3>
						<p class="title">Livro - jQuery Mobile - Desenvolva Aplicações Web para Dispositivos Móveis com HTML5, CSS3, AJAX, jQuery e JQUERY UI</p>
						<p class="description">jQuery Mobile é um framework para o desenvolvimento de aplicações web para dispositivos móveis.</p>
						<a href="#" class="read-more-btn">Read More</a>
					</div>
				</li>
				<li>
					<div class="image">
						<img src="css/images/books.png" alt="" />
					</div>
					<div class="details">
						<h2>Bestsellers</h2>
						<h3>Special Offers</h3>
						<p class="title">Pellentesque congue lorem quis massa blandit non pretium nisi pharetra</p>
						<p class="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent id odio in tortor scelerisque dictum. Phasellus varius sem sit amet metus volutpat vel vehicula nunc lacinia.</p>
						<a href="#" class="read-more-btn">Read More</a>
					</div>
				</li>
				<li>
					<div class="image">
						<img src="css/images/books.png" alt="" />
					</div>
					<div class="details">
						<h2>Bestsellers</h2>
						<h3>Special Offers</h3>
						<p class="title">Pellentesque congue lorem quis massa blandit non pretium nisi pharetra</p>
						<p class="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent id odio in tortor scelerisque dictum. Phasellus varius sem sit amet metus volutpat vel vehicula nunc lacinia.</p>
						<a href="#" class="read-more-btn">Read More</a>
					</div>
				</li>
				<li>
					<div class="image">
						<img src="css/images/books.png" alt="" />
					</div>
					<div class="details">
						<h2>Bestsellers</h2>
						<h3>Special Offers</h3>
						<p class="title">Pellentesque congue lorem quis massa blandit non pretium nisi pharetra</p>
						<p class="description">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent id odio in tortor scelerisque dictum. Phasellus varius sem sit amet metus volutpat vel vehicula nunc lacinia.</p>
						<a href="#" class="read-more-btn">Read More</a>
					</div>
				</li>
			</ul>
			<div class="nav">
				<a href="#">1</a>
				<a href="#">2</a>
				<a href="#">3</a>
				<a href="#">4</a>
			</div>
		</div>
	</div>
	<!-- End Slider -->
		