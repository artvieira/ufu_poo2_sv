package edu.ufu.poo2.si.control;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ufu.poo2.si.control.utils.FactoryConnection;
import edu.ufu.poo2.si.model.ItemPedido;

public class ItemPedidoDAO {

    private FactoryConnection fc;
    private String tabela = "item_pedido";
    private String columns = "qtd, "
            + "valor, "
            + "codigo_pedido, "
            + "codigo_produto ";

    public ItemPedidoDAO() {
        this.fc = FactoryConnection.getInstance();
    }

    public List<ItemPedido> buscarPorPedido(Long codigoPedido) {
        List<ItemPedido> retorno = new ArrayList<>();
        
        String sql = "select * from " + tabela + " where codigo_pedido = ?";

        try {
            PreparedStatement stmt = fc.getConnection().prepareStatement(sql);
            stmt.setLong(1, codigoPedido);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                retorno.add(montaItemPedido(rs));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return retorno;
    }

    public ItemPedido insert(ItemPedido ip) {
        String sql = "insert into "
                + tabela
                + "(" + columns
                + ") " + "values (?,?,?,?)";

        PreparedStatement stmt;
        try {
            stmt = fc.getConnection().prepareStatement(sql);

            stmt.setInt(1, ip.getQuantidade());
            stmt.setBigDecimal(2, ip.getValor());
            stmt.setLong(3, ip.getCodigoPedido());
            stmt.setLong(4, ip.getCodigoProduto());

            stmt.execute();
            stmt.close();

            fc.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ip;
    }

    public void deletePorPedido(Long codigoPedido) {
        String sql = "delete from " + tabela + " where codigo_pedido = ?";

        PreparedStatement stmt;

        try {
            stmt = fc.getConnection().prepareStatement(sql);

            stmt.setLong(1, codigoPedido);

            stmt.execute();
            stmt.close();

            fc.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private ItemPedido montaItemPedido(ResultSet rs) throws SQLException {
        ItemPedido retorno = new ItemPedido();

        retorno.setCodigoPedido(rs.getLong("codigo_pedido"));
        retorno.setCodigoProduto(rs.getLong("codigo_produto"));
        retorno.setQuantidade(rs.getInt("qtd"));
        retorno.setValor(rs.getBigDecimal("valor"));

        return retorno;
    }
}