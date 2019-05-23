package desafio.controller;

import desafio.controller.dao.DAOConstants;
import desafio.controller.dao.DAOOrcamento;
import desafio.controller.dao.exceptions.IllegalOrphanException;
import desafio.controller.dao.exceptions.NonexistentEntityException;
import desafio.model.domain.Cliente;
import desafio.model.domain.Orcamento;
import desafio.model.domain.Vendedor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author João Victor Bonfim
 */
public class TelaEdicaoOrcamentoMouseListener implements MouseListener {

    private JButton origem;
    private JTextField id;
    private JFormattedTextField data;
    private JTextArea descricao;
    private JTextField valor;
    private JComboBox<Cliente> cliente;
    private JComboBox<Vendedor> vendedor;
    private Orcamento alvo;

    public TelaEdicaoOrcamentoMouseListener(JButton origem, JTextField id, JFormattedTextField data, JTextArea descricao, JTextField valor, JComboBox<Cliente> cliente, JComboBox<Vendedor> vendedor, Orcamento alvo) {
        this.origem = origem;
        this.id = id;
        this.data = data;
        this.descricao = descricao;
        this.valor = valor;
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.alvo = alvo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(origem)) {
            if (alvo.getId().toString().equals(id.getText())) {
                try {
                    Date valorData = new SimpleDateFormat("dd/MM/yyyy hh:mm").parse(this.data.getText());
                    Double valorValor = Double.parseDouble(valor.getText());
                    if (!alvo.getCliente().equals((Cliente) cliente.getSelectedItem())
                            || !alvo.getDataEHora().equals(valorData)
                            || !alvo.getDescricao().equals(descricao.getText())
                            || !alvo.getValor().equals(valorValor)
                            || !alvo.getVendedor().equals((Vendedor) vendedor.getSelectedItem())) {
                        try {
                            alvo.setCliente((Cliente) cliente.getSelectedItem());
                            alvo.setDataEHora(valorData);
                            alvo.setDescricao(descricao.getText());
                            alvo.setVendedor((Vendedor) vendedor.getSelectedItem());
                            alvo.setValor(valorValor);
                            new DAOOrcamento(DAOConstants.EMF).edit(alvo);
                        } catch (NonexistentEntityException ex) {
                            JOptionPane.showMessageDialog(null, "O orçamento que estava sendo editado não existe mais!", "Erro!",
                                    JOptionPane.ERROR_MESSAGE);
                            Logger.getLogger(TelaEdicaoOrcamentoMouseListener.class.getName()).log(Level.WARNING, null, ex);
                        } catch (IllegalOrphanException ex) {
                            Logger.getLogger(TelaEdicaoOrcamentoMouseListener.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(TelaEdicaoOrcamentoMouseListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(TelaEdicaoOrcamentoMouseListener.class.getName()).log(Level.WARNING, null, ex);
                    JOptionPane.showMessageDialog(null, "A data fornecida é inválida, tente novamente.", "Data inválida", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Isso deveria ser impossível, mas "
                        + "você conseguiu...", "Como!?", JOptionPane.INFORMATION_MESSAGE);

                Logger.getLogger(TelaEdicaoOrcamentoMouseListener.class.getName()).log(Level.SEVERE,
                        "Isso deveria ser impossível, mas você conseguiu...");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource().equals(origem)) {
            origem.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource().equals(origem)) {
            origem.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
