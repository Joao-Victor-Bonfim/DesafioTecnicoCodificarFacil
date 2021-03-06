package desafio.view.telas.telaPrincipal.componentes;

import desafio.view.telas.telaPrincipal.componentes.barraMenu.MenuCliente;
import desafio.view.telas.telaPrincipal.componentes.barraMenu.MenuOrcamento;
import desafio.view.telas.telaPrincipal.componentes.barraMenu.MenuVendedor;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author João Victor Bonfim
 */
public class BarraMenu extends JMenuBar {

    private JMenu orcamento;
    private JMenu cliente;
    private JMenu vendedor;

    public BarraMenu() {
        initComponents();
    }

    public JMenu getOrcamento() {
        return orcamento;
    }

    public JMenu getCliente() {
        return cliente;
    }

    public JMenu getVendedor() {
        return vendedor;
    }

    private void initComponents() {
        orcamento = new MenuOrcamento();
        add(orcamento);

        cliente = new MenuCliente();
        add(cliente);

        vendedor = new MenuVendedor();
        add(vendedor);
    }

}
