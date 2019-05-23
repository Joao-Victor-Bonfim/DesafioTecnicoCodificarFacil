package desafio.controller;

import desafio.controller.dao.DAOConstants;
import desafio.controller.dao.DAOVendedor;
import desafio.controller.dao.exceptions.IllegalOrphanException;
import desafio.controller.dao.exceptions.NonexistentEntityException;
import desafio.model.domain.Vendedor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

/**
 *
 * @author João Victor Bonfim
 */
public class TelaEdicaoVendedorMouseListener implements MouseListener {

    private JButton origem;
    private JTextField nroRegistro;
    private JTextField nome;
    private Vendedor alvo;

    public TelaEdicaoVendedorMouseListener(JButton origem, JTextField nroRegistro, JTextField nome, Vendedor alvo) {
        this.origem = origem;
        this.nroRegistro = nroRegistro;
        this.nome = nome;
        this.alvo = alvo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(origem)) {
            if (alvo.getNroRegistro().toString().equals(nroRegistro.getText())) {
                if (!alvo.getNome().equals(nome.getText())) {
                    try {
                        alvo.setNome(nome.getText());
                        new DAOVendedor(DAOConstants.EMF).edit(alvo);
                    } catch (NonexistentEntityException ex) {
                        JOptionPane.showMessageDialog(null, "O vendedor que estava sendo editado não existe mais!", "Erro!",
                                JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(TelaEdicaoVendedorMouseListener.class.getName()).log(Level.WARNING, null, ex);
                    } catch (IllegalOrphanException ex) {
                        Logger.getLogger(TelaEdicaoVendedorMouseListener.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(TelaEdicaoVendedorMouseListener.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Isso deveria ser impossível, mas "
                        + "você conseguiu...", "Como!?", JOptionPane.INFORMATION_MESSAGE);

                Logger.getLogger(TelaEdicaoVendedorMouseListener.class.getName()).log(Level.SEVERE,
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
