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
    
    public BarraAcao() {
        super( "Barra de ações.", HORIZONTAL);
        
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
