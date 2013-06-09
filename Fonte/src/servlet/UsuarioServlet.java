package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Categoria;
import model.Endereco;
import model.Produto;
import model.TipoUsuario;
import model.Usuario;
import controller.ProdutoController;
import controller.UsuarioController;

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

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer idUsuario = Integer.parseInt(request.getParameter("id"));
		String acao = request.getParameter("acao");
		
		if(acao.equals("Alterar")){	
			Usuario usuario = new Usuario();
			UsuarioController controller = null;
			try {
				controller = new UsuarioController();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				usuario = controller.buscar(idUsuario);
				
				request.setAttribute("usuarios", usuario);
				RequestDispatcher disp = request.getRequestDispatcher("/admin/Usuario/Alterar.jsp");
				disp.forward(request, response);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if( acao.equals("Excluir") ){		
			UsuarioController controller = null;
			try {
				controller = new UsuarioController();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				controller.excluir(idUsuario);

				response.sendRedirect("/Canoinha/admin/Usuario/index.jsp?msg=Usuario Excluido com Sucesso");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}   
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		
		if (acao.equals("CadastrarUsuario")){
			adicionarUsuario(request, response);
		}else if(acao.equals("AlterarUsuario")){
			alterarUsuario(request, response);
		}
	}

	private void alterarUsuario(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String rua = request.getParameter("rua");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String cep = request.getParameter("cep");
		String estado = request.getParameter("estado");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
		Date dtNascimento = null;
		try {
			dtNascimento = format.parse(request.getParameter("dtNascimento"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession(true);
		
		Usuario usuario = new Usuario();
		usuario.setId(id);
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setCpf(cpf);
		usuario.setSenha(senha);
		usuario.setDataNascimento(dtNascimento);
		
		Endereco endereco = new Endereco();
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setCep(cep);
		endereco.setBairro(bairro);
		endereco.setUf(estado);
		endereco.setCidade(cidade);
		usuario.setEndereco(endereco);
		usuario.setTipo(TipoUsuario.USUARIO);
		
		UsuarioController controller = null;
		try {
			controller = new UsuarioController();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.atualizar(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Canoinha/admin/Usuario/index.jsp?msg=Usuario alterado com Sucesso");
	}

	private void adicionarUsuario(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String nome = request.getParameter("nome");
		String rua = request.getParameter("rua");
		String numero = request.getParameter("numero");
		String bairro = request.getParameter("bairro");
		String cidade = request.getParameter("cidade");
		String cep = request.getParameter("cep");
		String estado = request.getParameter("estado");
		String cpf = request.getParameter("cpf");
		String email = request.getParameter("email");
		String senha = request.getParameter("senha");
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date dtNascimento = null;
		try {
			dtNascimento = format.parse(request.getParameter("dtNascimento"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HttpSession session = request.getSession(true);
		
		String caracteres = "./-";
		
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setEmail(email);
		usuario.setCpf(cpf.toString().replace(caracteres, ""));
		usuario.setSenha(senha);
		usuario.setDataNascimento(dtNascimento);
		
		Endereco endereco = new Endereco();
		endereco.setRua(rua);
		endereco.setNumero(numero);
		endereco.setCep(cep.toString().replace(caracteres, ""));
		endereco.setBairro(bairro);
		endereco.setUf(estado);
		endereco.setCidade(cidade);
		usuario.setEndereco(endereco);
		usuario.setTipo(TipoUsuario.ADMINISTRADOR);
		
		UsuarioController controller = null;
		try {
			controller = new UsuarioController();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			controller.inserir(usuario);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.sendRedirect("/Canoinha/admin/Usuario/index.jsp?msg=Usuario Cadastrado com Sucesso");

		
	}


}
