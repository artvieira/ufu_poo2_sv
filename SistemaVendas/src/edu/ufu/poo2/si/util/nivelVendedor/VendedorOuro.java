package edu.ufu.poo2.si.util.nivelVendedor;

import edu.ufu.poo2.si.api.NivelVendedor;
import edu.ufu.poo2.si.model.ItemPedido;
import edu.ufu.poo2.si.util.enums.EnumNivelVendedor;
import edu.ufu.poo2.si.util.exceptions.ValidacaoException;

public class VendedorOuro implements NivelVendedor {

	public NivelVendedor sucessor = new VendedorPlatina();

	public void validarDesconto(ItemPedido ip) throws ValidacaoException {
		if (ip.getDesconto() > 10) {
			sucessor.validarDesconto(ip);
		} else {
			ip.setNivelVendedoLibera(EnumNivelVendedor.Ouro);
		}
	}
}
