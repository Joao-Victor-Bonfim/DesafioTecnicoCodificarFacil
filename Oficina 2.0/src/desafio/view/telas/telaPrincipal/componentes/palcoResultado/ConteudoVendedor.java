package desafio.view.telas.telaPrincipal.componentes.palcoResultado;

import desafio.model.domain.Vendedor;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author João Victor Bonfim
 */
public class ConteudoVendedor extends JPanel {

    private JLabel nroRegistro;
    private JLabel nome;

    private void updateTextFromNroRegistro(String text) {
        nroRegistro.setText(text);
    }

    private void updateTextFromNome(String text) {
        nome.setText(text);
    }

    public void updateVendedor(Vendedor vendedor) {
        updateTextFromNroRegistro(vendedor.getNroRegistro().toString());
        updateTextFromNome(vendedor.getNome());
    }
    
    public ConteudoVendedor(Vendedor vendedor) {
        this(new GridLayout(2, 2), vendedor);
    }

    public ConteudoVendedor(LayoutManager layout, Vendedor vendedor) {
        super(layout);

        initComponents(vendedor);
    }
    
    private void initComponents(Vendedor vendedor) {
        add(new JLabel("ID:"));
        nroRegistro = new JLabel();
        nroRegistro.setHorizontalAlignment(SwingConstants.CENTER);
        add(nroRegistro);

        add(new JLabel("Nome:"));
        nome = new JLabel();
        nome.setHorizontalAlignment(SwingConstants.CENTER);
        add(nome);
        
        updateVendedor(vendedor);
    }
}
