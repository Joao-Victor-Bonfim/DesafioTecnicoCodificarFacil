package desafio.view.telas.telaPrincipal.componentes.barraMenu.menuVendedor;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author João Victor Bonfim
 */
public class MenuPesquisaVendedor extends JMenu {
    
    private JMenuItem todos;
    
    private JMenuItem nome;
    
    private JMenuItem nroRegistro;

    public JMenuItem getTodos() {
        return todos;
    }

    public JMenuItem getNome() {
        return nome;
    }

    public JMenuItem getNroRegistro() {
        return nroRegistro;
    }
    
    public MenuPesquisaVendedor() {
        super("Listar:");
        
        todos = new JMenuItem("Todos");
        add(todos);
        
        nome = new JMenuItem("Por nome");
        add(nome);
        
        nroRegistro = new JMenuItem("Por número de registro");
        add(nroRegistro);
    }
    
}
