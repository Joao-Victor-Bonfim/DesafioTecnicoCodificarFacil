package desafio.view.telas.telaPrincipal.componentes;

import desafio.view.telas.telaPrincipal.componentes.palcoResultado.ListaComScroll;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author João Victor Bonfim
 */
public class PalcoResultado extends JPanel {

    private ListaComScroll resultados;
    private JPanel conteudo;

    public PalcoResultado(LayoutManager layout) {
        super(layout);
        initComponents(new ListaComScroll(), new JPanel());
    }

    public PalcoResultado() {
        this(new GridLayout(1, 2));
    }

    private void initComponents(ListaComScroll resultados, JPanel conteudo) {
        this.resultados = resultados;
        add(this.resultados);

        this.conteudo = conteudo;
        add(this.conteudo);
    }

    public ListaComScroll getResultados() {
        return resultados;
    }

    public void updateConteudo(JPanel conteudo) {
        removeAll();
        initComponents(this.resultados, conteudo);
        revalidate();
        repaint();
    }

    public void updateResultados(ListaComScroll resultados) {
        removeAll();
        initComponents(resultados, this.conteudo);
        revalidate();
        repaint();
    }

    public JPanel getConteudo() {
        return conteudo;
    }

    public void setModel(ListModel model) {
        resultados.setModel(model);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        resultados.addListSelectionListener(listener);
    }
    
    public JList getListaFromResultados() {
        return resultados.getLista();
    }
}
