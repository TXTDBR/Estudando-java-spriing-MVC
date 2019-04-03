package br.com.caelum.tarefas.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.Usuario;
import br.com.caelum.tarefas.dao.JdbcTarefaDao;

@Controller
public class LoginController {

	private final JdbcTarefaDao dao;

	@Autowired
	public LoginController(JdbcTarefaDao dao) {
		this.dao = dao;
	}

	@RequestMapping("/loginForm")
	public String form() {
		return "formulario-login";
	}

	@RequestMapping("efetuarLogin")
	public String efetuarLogin(Usuario usuario, HttpSession session) throws ClassNotFoundException {
		if (dao.usuarioExiste(usuario)) {
			session.setAttribute("usuarioLogado", usuario);
			return "menu";
		} else {
			return "redirect:loginForm";
		}
	}

	@RequestMapping("logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:loginForm";
	}
}
