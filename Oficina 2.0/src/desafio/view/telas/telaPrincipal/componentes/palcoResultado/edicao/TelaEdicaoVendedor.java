package desafio.view.telas.telaPrincipal.componentes.palcoResultado.edicao;

import desafio.controller.TelaEdicaoVendedorMouseListener;
import desafio.model.domain.Vendedor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.Box.Filler;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author João Victor Bonfim
 */
public class TelaEdicaoVendedor extends JPanel {

    private JTextField nome;
    private JTextField nroRegistro;
    private JButton aplicar;
    private Vendedor vendedor;

    public TelaEdicaoVendedor(Vendedor vendedor) {
        this(new GridLayout(3, 2), vendedor);
    }

    public TelaEdicaoVendedor(LayoutManager layout, Vendedor vendedor) {
        super(layout);
        initComponents(vendedor);
    }

    private void initComponents(Vendedor vendedor) {

        add(new JLabel("Número de registro:"));
        nroRegistro = new JTextField();
        nroRegistro.setEditable(false);
        add(nroRegistro);

        add(new JLabel("Nome:"));
        nome = new JTextField();
        nome.setEditable(true);
        add(nome);

        add(new Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 0)));
        aplicar = new JButton("Aplicar modificações");
        aplicar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        aplicar.addMouseListener(new TelaEdicaoVendedorMouseListener(aplicar, nroRegistro, nome, vendedor));
        add(aplicar);

        updateCliente(vendedor);
    }

    private void updateTextFromNome(String text) {
        nome.setText(text);
    }

    private void updateTextFromNroRegistro(String text) {
        nroRegistro.setText(text);
    }

    private void updateCliente(Vendedor vendedor) {
        this.vendedor = vendedor;
        updateTextFromNome(this.vendedor.getNome());
        updateTextFromNroRegistro(this.vendedor.getNroRegistro().toString());
    }

    public Vendedor getVendedor() {
        return vendedor;
    }
}
