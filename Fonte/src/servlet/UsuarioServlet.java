package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Categoria;
import model.Conexao;
import model.Endereco;
import model.Produto;
import model.TipoUsuario;
import model.Usuario;
import controller.ProdutoController;
import controller.UsuarioController;
import dao.DaoProduto;
import dao.DaoUsuario;

/**
 * Servlet implementation class UsuarioServlet
 */
public class UsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void alterarUsuario(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			UsuarioController controller = new UsuarioController();
			Integer idUsuario = Integer.parseInt(request.getParameter("id"));
			Usuario usuario = controller.buscar(idUsuario);

			request.setAttribute("usuarios", usuario);
			RequestDispatcher disp = request
					.getRequestDispatcher("/admin/Usuario/Alterar.jsp");
			disp.forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void excluirUsuario(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		try {
			UsuarioController controller = new UsuarioController();
			Integer idUsuario = Integer.parseInt(request.getParameter("id"));
			controller.excluir(idUsuario);

			response.sendRedirect("/Canoinha/admin/Usuario/index.jsp?msg=Usuario Excluido com Sucesso");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void listarUsuarios(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		try {
			DaoUsuario daoUsuario = new DaoUsuario(Conexao.getConexao());
			List<Usuario> usuarios = daoUsuario.buscar();

			for (Usuario usuario : usuarios) {
				out.println("<tr>"+
					           "<td>"+
						           usuario.getId()+
						       "</td>"+
						       "<td>"+
					   	           usuario.getNome()+
						       "</td>"+
						       "<td>"
						         + usuario.getCpf()+
						       "</td>" +
						       "<td>"+
						           "<a href=/Canoinha/UsuarioServlet?id="+usuario.getId()+"&acao=Alterar>Atualizar <i class='icon-edit'></i></a>"+
						       "</td>"+
						       "<td>" +
						           "<a href=/Canoinha/UsuarioServlet?id="+usuario.getId()+"&acao=Excluir>Excluir <i class='icon-trash'></i></a>"+
						       "</td>"+						        
						    "</tr>");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void alterarUsuarioPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		try {
			Usuario usuario = new Usuario();
			usuario.setId(Integer.parseInt(request.getParameter("id")));
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setCpf(request.getParameter("cpf"));
			usuario.setSenha(request.getParameter("senha"));
			usuario.setDataNascimento(format.parse(request
					.getParameter("dtNascimento")));

			Endereco endereco = new Endereco();
			endereco.setRua(request.getParameter("rua"));
			endereco.setNumero(request.getParameter("numero"));
			endereco.setCep(request.getParameter("cep"));
			endereco.setBairro(request.getParameter("bairro"));
			endereco.setUf(request.getParameter("estado"));
			endereco.setCidade(request.getParameter("cidade"));
			usuario.setEndereco(endereco);
			if (request.getParameter("tipoUsuario").equals("Administrador")) {
				usuario.setTipo(TipoUsuario.ADMINISTRADOR);
			} else {
				usuario.setTipo(TipoUsuario.USUARIO);
			}

			UsuarioController controller = new UsuarioController();
			controller.atualizar(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Canoinha/admin/Usuario/index.jsp?msg=Usuario alterado com Sucesso");
	}

	private void adicionarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			Date dtNascimento = format.parse(request.getParameter("dtNascimento"));
			Usuario usuario = new Usuario();
			usuario.setNome(request.getParameter("nome"));
			usuario.setEmail(request.getParameter("email"));
			usuario.setCpf(request.getParameter("cpf"));
			usuario.setSenha(request.getParameter("senha"));
			usuario.setDataNascimento(dtNascimento);

			Endereco endereco = new Endereco();
			endereco.setRua(request.getParameter("rua"));
			endereco.setNumero(request.getParameter("numero"));
			endereco.setCep(request.getParameter("cep"));
			endereco.setBairro(request.getParameter("bairro"));
			endereco.setUf(request.getParameter("estado"));
			endereco.setCidade(request.getParameter("cidade"));
			usuario.setEndereco(endereco);
			if(request.getParameter("tipoUsuario").equals("Administrador")){
				usuario.setTipo(TipoUsuario.ADMINISTRADOR);
			}else{
				usuario.setTipo(TipoUsuario.USUARIO);
			}		
			UsuarioController controller = new UsuarioController();
			controller.inserir(usuario);
			
			if (usuario.getTipo().equals("Administrador")){
				response.sendRedirect("/Canoinha/admin/Usuario/index.jsp?msg=Usuario Cadastrado com Sucesso");
			}else{
				request.setAttribute("menssagemErro", "Usuario cadastrado com sucesso");
				response.sendRedirect("/Canoinha/index.jsp");
			}	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao.equals("Alterar")) {
			alterarUsuario(request, response);
		} else if (acao.equals("Excluir")) {
			excluirUsuario(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");

		if (acao.equals("CadastrarUsuario")) {
			adicionarUsuario(request, response);
		} else if (acao.equals("AlterarUsuario")) {
			alterarUsuarioPost(request, response);
		} else if (acao.equals("ListarUsuarios")) {
			listarUsuarios(request, response);
		}
	}
}
