<script>
	function postCategorias() {
		$.post('http://localhost:8080/Canoinha/CategoriaServlet',
				function(data) {
					$('#itens_menu').html(data);
				});
	}
</script>
<style type="text/css">
#img_loading {
	padding-left: 40px;
	padding-top: 50px;
}
</style>
<!-- Main -->
<div id="main" class="shell">
	<!-- Sidebar -->
	<div id="sidebar">
		<ul class="categories">
			<li>
				<h4>Categorias</h4>
				<ul id="itens_menu">
					<img id="img_loading" src="img/loading.gif" width="50" height="50">
				</ul>
			</li>
		</ul>
	</div>
	<!-- End Sidebar -->