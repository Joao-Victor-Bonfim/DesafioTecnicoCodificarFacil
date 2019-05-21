package desafio.view.telas.telaPrincipal.componentes.barraMenu.menuOrcamento;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author João Victor Bonfim
 */
public class MenuPesquisaOrcamento extends JMenu {

    private JMenuItem todos;
    private JMenuItem idOrcamento;
    private JMenuItem valor;
    private JMenuItem dataEHora;

    public MenuPesquisaOrcamento() {
        this("Listar:");
    }

    public MenuPesquisaOrcamento(String texto) {
        super(texto);
        initComponents();
    }

    public JMenuItem getTodos() {
        return todos;
    }

    public JMenuItem getIdOrcamento() {
        return idOrcamento;
    }

    public JMenuItem getValor() {
        return valor;
    }

    public JMenuItem getDataEHora() {
        return dataEHora;
    }

    private void initComponents() {
        todos = new JMenuItem("Todos");
        add(todos);

        idOrcamento = new JMenuItem("Por id");
        add(idOrcamento);

        valor = new JMenuItem("Por valor");
        add(valor);

        dataEHora = new JMenuItem("Por data e hora");
        add(dataEHora);
    }
}
