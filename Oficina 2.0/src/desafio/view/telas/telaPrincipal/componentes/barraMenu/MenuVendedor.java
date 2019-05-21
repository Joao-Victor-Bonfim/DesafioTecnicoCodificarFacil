package desafio.view.telas.telaPrincipal.componentes.barraMenu;

import desafio.view.telas.telaPrincipal.componentes.barraMenu.menuVendedor.MenuPesquisaVendedor;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author João Victor Bonfim
 */
public class MenuVendedor extends JMenu {

    private JMenuItem novo;
    private JMenu pesquisar;

    public MenuVendedor(String texto) {
        super(texto);
        initComponents();
    }

    public MenuVendedor() {
        this("Vendedor");
    }

    public JMenuItem getNovo() {
        return novo;
    }

    public JMenu getPesquisar() {
        return pesquisar;
    }

    private void initComponents() {
        novo = new JMenuItem("novo");
        add(novo);

        pesquisar = new MenuPesquisaVendedor();
        add(pesquisar);
    }

}
