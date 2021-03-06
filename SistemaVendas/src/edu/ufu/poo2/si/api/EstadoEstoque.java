package edu.ufu.poo2.si.api;

import edu.ufu.poo2.si.model.Estoque;
import edu.ufu.poo2.si.util.exceptions.ValidacaoException;

public abstract class EstadoEstoque {
	public Estoque estoque;

	public abstract void verificarEstado();

	public abstract void adicionar(int quantidade);

	public abstract void faturar(int quantidade) throws ValidacaoException;
}
