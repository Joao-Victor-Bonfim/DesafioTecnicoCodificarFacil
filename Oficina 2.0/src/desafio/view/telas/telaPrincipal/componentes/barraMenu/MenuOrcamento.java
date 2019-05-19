package desafio.view.telas.telaPrincipal.componentes.barraMenu;

import desafio.view.telas.telaPrincipal.componentes.barraMenu.menuOrcamento.MenuPesquisaOrcamento;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author João Victor Bonfim
 */
public class MenuOrcamento extends JMenu {
    
    private JMenuItem novo;
    private JMenu pesquisar;

    public JMenuItem getNovo() {
        return novo;
    }

    public JMenu getPesquisar() {
        return pesquisar;
    }

    public MenuOrcamento() {
        super("Orcamento");
        
        novo = new JMenuItem("novo");
        add(novo);
        
        pesquisar = new MenuPesquisaOrcamento();
        add(pesquisar);
        
    }
    
}
