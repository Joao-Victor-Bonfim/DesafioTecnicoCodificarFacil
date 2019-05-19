package desafio.view.telas.telaPrincipal.componentes.barraMenu;

import desafio.view.telas.telaPrincipal.componentes.barraMenu.menuCliente.MenuPesquisaCliente;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author João Victor Bonfim
 */
public class MenuCliente extends JMenu {
    
    private JMenuItem novo;
    private JMenu pesquisar;

    public JMenuItem getNovo() {
        return novo;
    }

    public JMenu getPesquisar() {
        return pesquisar;
    }

    public MenuCliente() {
        super("Cliente");
        
        novo = new JMenuItem("novo");
        add(novo);
        
        pesquisar = new MenuPesquisaCliente();
        add(pesquisar);
        
    }
}
