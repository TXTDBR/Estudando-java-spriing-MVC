package br.com.caelum.tarefas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.caelum.tarefas.Tarefa;
import br.com.caelum.tarefas.dao.JdbcTarefaDao;

@Controller
public class TarefasController {
	
	private final JdbcTarefaDao dao;
	
	@Autowired
	public TarefasController(JdbcTarefaDao dao) {
		this.dao = dao;
	}
	
	@RequestMapping("/novaTarefa")
	public	String	form()	{
		return	"tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adicionar(@Valid Tarefa tarefa, BindingResult result)  {
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		
		dao.adicionar(tarefa);
		return	"tarefa/tarefa-adicionada";
	}
	
	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		model.addAttribute("tarefas", dao.getList());
		return "tarefa/lista";
	}
	
	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa){
		dao.remove(tarefa);
		return "redirect:listaTarefas";
	}
	
	@RequestMapping("mostrarTarefa")
	public String mostrar(Long id, Model model) {
		Tarefa tarefa = dao.bucartarefaById(id);
		model.addAttribute("tarefa", tarefa);
		return "tarefa/mostrar";
	}
	
	@RequestMapping("alterarTarefa")
	public String alterar(@Valid Tarefa tarefa, BindingResult result) {
		if(result.hasFieldErrors("descricao")) {
			return "tarefa/mostrar";
		}

		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}
	
	//@ResponseBody
	@RequestMapping("finalizaTarefa")
	public String finalizar(Long id, Model model) {
		dao.finalizar(id); 
		model.addAttribute("tarefa", dao.bucartarefaById(id));
		return "tarefa/finalizada";
	}
}
