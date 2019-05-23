package desafio.view.telas.telaPrincipal.componentes.palcoResultado.edicao;

import desafio.controller.TelaEdicaoClienteMouseListener;
import desafio.model.domain.Cliente;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author João Victor Bonfim
 */
public class TelaEdicaoCliente extends JPanel {

    private JTextField nome;
    private JTextField id;
    private JButton aplicar;
    private Cliente cliente;

    public TelaEdicaoCliente(Cliente cliente) {
        this(new GridLayout(3, 2), cliente);
    }

    public TelaEdicaoCliente(LayoutManager layout, Cliente cliente) {
        super(layout);
        initComponents(cliente);
    }

    private void initComponents(Cliente cliente) {

        add(new JLabel("Id:"));
        id = new JTextField();
        id.setEditable(false);
        add(id);

        add(new JLabel("Nome:"));
        nome = new JTextField();
        nome.setEditable(true);
        add(nome);

        add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 0)));
        aplicar = new JButton("Aplicar modificações");
        aplicar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        aplicar.addMouseListener(
                new TelaEdicaoClienteMouseListener(aplicar, id, nome, cliente));
        add(aplicar);

        updateCliente(cliente);
    }

    private void updateTextFromNome(String text) {
        nome.setText(text);
    }

    private void updateTextFromId(String text) {
        id.setText(text);
    }

    private void updateCliente(Cliente cliente) {
        this.cliente = cliente;
        updateTextFromNome(this.cliente.getNome());
        updateTextFromId(this.cliente.getId().toString());
    }

    public Cliente getCliente() {
        return cliente;
    }

}
