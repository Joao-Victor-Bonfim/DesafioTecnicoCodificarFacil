package desafio.view.telas.telaPrincipal.componentes.palcoResultado.edicao;

import desafio.controller.TelaEdicaoOrcamentoMouseListener;
import desafio.controller.dao.DAOCliente;
import desafio.controller.dao.DAOConstants;
import desafio.controller.dao.DAOVendedor;
import desafio.model.domain.Cliente;
import desafio.model.domain.Orcamento;
import desafio.model.domain.Vendedor;
import desafio.model.viewModel.ViewComboBoxModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

/**
 *
 * @author João Victor Bonfim
 */
public class TelaEdicaoOrcamento extends JPanel {

    private JTextField id;
    private JFormattedTextField data;
    private JTextArea descricao;
    private JTextField valor;
    private JComboBox<Cliente> cliente;
    private JComboBox<Vendedor> vendedor;
    private JButton aplicar;
    private Orcamento orcamento;

    public TelaEdicaoOrcamento(Orcamento orcamento) {
        this(new GridLayout(3, 2), orcamento);
    }

    public TelaEdicaoOrcamento(LayoutManager layout, Orcamento orcamento) {
        super(layout);
        initComponents(orcamento);
    }

    private void initComponents(Orcamento orcamento) {

        add(new JLabel("Id:"));
        id = new JTextField();
        id.setEditable(false);
        id.setEnabled(false);
        add(id);

        add(new JLabel("Data e hora:"));
        try {
            MaskFormatter formatter = new MaskFormatter("##/##/#### ##:##");
            formatter.setPlaceholder("_");
            formatter.setValidCharacters("0123456789");
            data = new JFormattedTextField(formatter);
        } catch (ParseException ex) {
            Logger.getLogger(TelaEdicaoOrcamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        data.setEditable(true);
        data.setEnabled(true);
        add(data);

        add(new JLabel("Descrição do orçamento:"));
        descricao = new JTextArea(5, 30);
        descricao.setEditable(true);
        descricao.setEnabled(true);
        add(descricao);

        add(new JLabel("Valor (R$):"));
        valor = new JTextField();
        valor.setEditable(true);
        valor.setEnabled(true);
        add(valor);

        add(new JLabel("Cliente:"));
        cliente = new JComboBox<>();
        cliente.setModel(new ViewComboBoxModel<>(new DAOCliente(DAOConstants.EMF).findClienteEntities()));
        cliente.setEditable(false);
        cliente.setEnabled(true);
        add(cliente);

        add(new JLabel("Vendedor"));
        vendedor = new JComboBox<>();
        vendedor.setModel(new ViewComboBoxModel<>(new DAOVendedor(DAOConstants.EMF).findVendedorEntities()));
        vendedor.setEditable(false);
        vendedor.setEnabled(true);
        add(vendedor);

        add(new Box.Filler(new Dimension(0, 0), new Dimension(0, 0), new Dimension(0, 0)));
        aplicar = new JButton("Aplicar modificações");
        aplicar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        aplicar.addMouseListener(
                new TelaEdicaoOrcamentoMouseListener(aplicar, id, data, descricao, valor, cliente, vendedor, orcamento));
        add(aplicar);

        updateOrcamento(orcamento);
    }

    private void updateId(Integer id) {
        this.id.setText(id.toString());
    }

    private void updateData(Date data) {
        this.data.setValue(data);
    }

    private void updateDescricao(String descricao) {
        this.descricao.setText(descricao);
    }

    private void updateValor(Double valor) {
        this.valor.setText(valor.toString());
    }

    private void updateCliente(Cliente cliente) {
        this.cliente.setSelectedItem(cliente);
    }

    private void updateVendedor(Vendedor vendedor) {
        this.vendedor.setSelectedItem(vendedor);
    }

    private void updateOrcamento(Orcamento orcamento) {
        this.orcamento = orcamento;
        updateId(orcamento.getId());
        updateData(orcamento.getDataEHora());
        updateDescricao(orcamento.getDescricao());
        updateValor(orcamento.getValor());
        updateCliente(orcamento.getCliente());
        updateVendedor(orcamento.getVendedor());
    }

    public Orcamento getOrcamento() {
        return orcamento;
    }

}
