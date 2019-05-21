package desafio.view.telas.telaPrincipal.componentes;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

/**
 *
 * @author João Victor Bonfim
 */
public class BarraAcao extends JToolBar {

    protected JButton editar;
    protected JButton deletar;

    public BarraAcao(String name, int orientation) {
        super(name, orientation);
        initComponents();
    }

    public BarraAcao() {
        this("Barra de ações.", HORIZONTAL);
    }

    private void initComponents() {
        editar = new JButton("Editar");
        editar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(editar);

        deletar = new JButton("Deletar");
        deletar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        add(deletar);
    }

    public JButton getEditar() {
        return editar;
    }

    public JButton getDeletar() {
        return deletar;
    }
}
