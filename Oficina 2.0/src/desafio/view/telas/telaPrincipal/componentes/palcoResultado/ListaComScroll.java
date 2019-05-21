package desafio.view.telas.telaPrincipal.componentes.palcoResultado;

import desafio.view.render.ListCellRendererGeral;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author João Victor Bonfim
 */
public class ListaComScroll extends JScrollPane {

    private JList lista;

    public ListaComScroll() {
        this(VERTICAL_SCROLLBAR_AS_NEEDED, HORIZONTAL_SCROLLBAR_AS_NEEDED);
    }

    public ListaComScroll(int vsbPolicy, int hsbPolicy) {
        super(vsbPolicy, hsbPolicy);
        initComponents();
    }

    private void initComponents() {
        lista = new JList();
        lista.setCellRenderer(new ListCellRendererGeral());
        setViewportView(lista);
    }

    public void setModel(ListModel model) {
        lista.setModel(model);
    }

    public void addListSelectionListener(ListSelectionListener listener) {
        lista.addListSelectionListener(listener);
    }

    public JList getLista() {
        return lista;
    }

}
