package desafio.view.telas.telaPrincipal.componentes.palcoResultado.conteudo;

import desafio.model.domain.Cliente;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author João Victor Bonfim
 */
public class ConteudoCliente extends JPanel {

    private JLabel id;
    private JLabel nome;

    private void updateTextFromId(String text) {
        id.setText(text);
    }

    private void updateTextFromNome(String text) {
        nome.setText(text);
    }

    public void updateCliente(Cliente cliente) {
        updateTextFromId(cliente.getId().toString());
        updateTextFromNome(cliente.getNome());
    }

    public ConteudoCliente(Cliente cliente) {
        this(new GridLayout(2, 2), cliente);
    }

    public ConteudoCliente(LayoutManager layout, Cliente cliente) {
        super(layout);

        initComponents(cliente);
    }

    private void initComponents(Cliente cliente) {
        add(new JLabel("ID:"));
        id = new JLabel();
        id.setHorizontalAlignment(SwingConstants.CENTER);
        add(id);

        add(new JLabel("Nome:"));
        nome = new JLabel();
        nome.setHorizontalAlignment(SwingConstants.CENTER);
        add(nome);

        updateCliente(cliente);
    }
}
