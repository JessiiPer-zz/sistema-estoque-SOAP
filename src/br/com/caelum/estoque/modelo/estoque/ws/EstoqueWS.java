package br.com.caelum.estoque.modelo.estoque.ws;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import br.com.caelum.estoque.modelo.item.Filtro;
import br.com.caelum.estoque.modelo.item.Filtros;
import br.com.caelum.estoque.modelo.item.Item;
import br.com.caelum.estoque.modelo.item.ItemDao;
import br.com.caelum.estoque.modelo.item.ListaItens;
import br.com.caelum.estoque.modelo.usuario.AutorizacaoException;
import br.com.caelum.estoque.modelo.usuario.TokenDao;
import br.com.caelum.estoque.modelo.usuario.TokenUsuario;

@WebService
public class EstoqueWS {

	private ItemDao dao = new ItemDao();
	
	@WebMethod(operationName="todosOsItens")
	//@ResponseWrapper(localName="itens")
	@WebResult(name="item")
	//@RequestWrapper(localName="listaItens")
	public ListaItens getItens(@WebParam(name="filtros") Filtros filtros){

	List<Filtro> lista = filtros.getLista();
	List<Item> result = dao.todosItens(lista);

	return new ListaItens(result);

	}
	
	@WebMethod(operationName="CadastrarItem")
	@WebResult(name="item")
	public Item cadastrarItem(@WebParam(name="tokenUsuario", header = true) TokenUsuario token,
			@WebParam(name="item") Item item) throws AutorizacaoException {
		
		System.out.println("Cadastrando item: " + item);
		
		boolean valido = new TokenDao().ehValido(token);
		
		if(!valido) {
			throw new AutorizacaoException("Falha de autorização");
		}
		
		this.dao.cadastrar(item);
		return item;
	}
}
