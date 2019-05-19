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

    public JMenuItem getNovo() {
        return novo;
    }

    public JMenu getPesquisar() {
        return pesquisar;
    }

    public MenuVendedor() {
        super("Vendedor");
        
        novo = new JMenuItem("novo");
        add(novo);
        
        pesquisar = new MenuPesquisaVendedor();
        add(pesquisar);
        
    }
    
}
