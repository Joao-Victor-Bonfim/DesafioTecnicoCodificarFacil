package desafio.view.telas.telaPrincipal.componentes.barraMenu.menuCliente;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author João Victor Bonfim
 */
public class MenuPesquisaCliente extends JMenu {
    
    private JMenuItem todos;
    private JMenuItem id;
    private JMenuItem nome;

    public JMenuItem getTodos() {
        return todos;
    }

    public JMenuItem getId() {
        return id;
    }

    public JMenuItem getNome() {
        return nome;
    }
    
    public MenuPesquisaCliente() {
        super("Listar:");
        
        todos = new JMenuItem("Todos");
        add(todos);
        
        id = new JMenuItem("Por id");
        add(id);
        
        nome = new JMenuItem("Por nome");
        add(nome);
    }
    
}
