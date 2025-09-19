package com.agenda.senai.Spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.senai.Spring.Model.Contato;
import com.agenda.senai.Spring.Repository.ContatoRepository;

@Controller
    public class ContatoController {
	
	@Autowired
	private ContatoRepository rs;
	
	@GetMapping(value = "/cadastrarContato")
	public String contato() {
	return "cadastrarContato";
   }
	@PostMapping(value = "/cadastrarContato")
	public String contato (Contato contato) {
	rs.save(contato);
		return "redirect:/cadastrarContato";
	}
	
	@RequestMapping ("/contato")
	public ModelAndView listascontato() {
		ModelAndView mv = new ModelAndView("Contato");
		Iterable<Contato> contato = rs.findAll();
		mv.addObject("contato", contato);
		return mv;
	}
	//MÉTODO PARA EDITAR SOLICITAÇÃO(GET)
	@GetMapping("/editarContato/{id}")
	public String editarContato(@PathVariable("id") long id, Model model) {
		Contato contato = rs.findById(id).orElse(null);
		if (contato != null) {
			model.addAttribute("contato", contato);
			return "editarContato";
		}
		return "redirect:/";
	}
	//MÉTODO POST COMO FALLBACK PARA ATUALIZAR 
	@PostMapping ("/atualizarContato/{id}")
	public String atualizarContatoPost(@PathVariable("id")long id, Contato contatoAtualizado) {
		return atualizarContato(id, contatoAtualizado);
		
	}
	//MÉTODO PUT PARA ATULIZAÇÃO 
	@PutMapping("/atualizarContato/{id}")
	public String atualizarContato(@PathVariable("id")long id, Contato contatoAtualizado) {
		Contato contatoExistente = rs.findById(id).orElse(null);
		
		if (contatoExistente !=null) {
			contatoExistente.setId(contatoAtualizado.getId());
			contatoExistente.setNome(contatoAtualizado.getNome());
			contatoExistente.setCelular(contatoAtualizado.getCelular());
	        contatoExistente.setEmail(contatoAtualizado.getEmail());
	        contatoExistente.setDataNascimento(contatoAtualizado.getDataNascimento());
	        contatoExistente.setEndereco(contatoAtualizado.getEndereco());
	        contatoExistente.setCidade(contatoAtualizado.getCidade());
	        contatoExistente.setRg(contatoAtualizado.getRg());
	        contatoExistente.setCpf(contatoAtualizado.getCpf());
	        
	        rs.save(contatoExistente);        
		}
		return "redirect:/";
	}
		//NOVO: MÉTODO DELETE PARA EXCLUIR SOLICIÇÃO
		@DeleteMapping ("/excluirSolicitacao/{id}")
		public String excluirContato(@PathVariable("id")long id,RedirectAttributes redirectAttibute) {
			try {
				Contato contato = rs.findById(id).orElse(null);
				if (contato != null) {
					rs.delete(contato);
					redirectAttibute.addFlashAttribute("Mensagem", "Contato excluido com sucesso!");
				}else {
					redirectAttibute.addFlashAttribute("Erro", "Contato não encontrado");
				}
			}catch (Exception e) {
				redirectAttibute.addFlashAttribute("Erro", "Erro ao excluir contato: " + e.getMessage());
			}
			return "redirect:/";
		}
		//MÉTODO POST COMO FALLBACK PARA DELETE 
		@PostMapping("/excluirContato/{id}")
		public String excluirContatoPost(@PathVariable("id")long id, RedirectAttributes redirectAttributes) {
			return excluirContato(id, redirectAttributes);
	}
}
