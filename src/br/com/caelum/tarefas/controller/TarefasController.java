package br.com.caelum.tarefas.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.Tarefa;
import br.com.caleum.dao.JdbcTarefaDao;

@Controller
public class TarefasController {
	
	@RequestMapping("/novaTarefa")
	public	String	form()	{
		return	"tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adicionar(@Valid Tarefa tarefa, BindingResult result) throws ClassNotFoundException {
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		
		JdbcTarefaDao	dao	=	new	JdbcTarefaDao();
		dao.adicionar(tarefa);
		return	"tarefa/tarefa-adicionada";
	}
	
	@RequestMapping("listaTarefas")
	public String lista(Model model) throws ClassNotFoundException {
		JdbcTarefaDao	dao	=	new	JdbcTarefaDao();
		model.addAttribute("tarefas", dao.getList());
		return "tarefa/lista";
	}
	
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) throws ClassNotFoundException {
		JdbcTarefaDao	dao	=	new	JdbcTarefaDao();
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
	
	@RequestMapping("mostrarTarefa")
	public String mostrar(Long id, Model model) throws ClassNotFoundException {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		Tarefa tarefa = dao.bucartarefaById(id);
		model.addAttribute("tarefa", tarefa);
		return "tarefa/mostrar";
	}
	
	@RequestMapping("alterarTarefa")
	public String alterar(@Valid Tarefa tarefa, BindingResult result) throws ClassNotFoundException {
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/mostrar";
		}
		JdbcTarefaDao dao = new JdbcTarefaDao();
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}
}
