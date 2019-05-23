package desafio.controller;

import desafio.controller.dao.DAOCliente;
import desafio.controller.dao.DAOConstants;
import desafio.controller.dao.exceptions.IllegalOrphanException;
import desafio.controller.dao.exceptions.NonexistentEntityException;
import desafio.model.domain.Cliente;
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
public class TelaEdicaoClienteMouseListener implements MouseListener {

    private JButton origem;
    private JTextField id;
    private JTextField nome;
    private Cliente alvo;

    public TelaEdicaoClienteMouseListener(JButton origem, JTextField id, JTextField nome, Cliente alvo) {
        this.origem = origem;
        this.id = id;
        this.nome = nome;
        this.alvo = alvo;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().equals(origem)) {
            if (alvo.getId().toString().equals(id.getText())) {
                if (!alvo.getNome().equals(nome.getText())) {
                    try {
                        alvo.setNome(nome.getText());
                        new DAOCliente(DAOConstants.EMF).edit(alvo);

                    } catch (IllegalOrphanException exception) {
                        Logger.getLogger(TelaEdicaoClienteMouseListener.class.getName()).log(Level.SEVERE, null, exception);
                    } catch (NonexistentEntityException exception) {
                        JOptionPane.showMessageDialog(null, "O cliente que estava sendo editado não existe mais!", "Erro!",
                                JOptionPane.ERROR_MESSAGE);
                        Logger.getLogger(TelaEdicaoClienteMouseListener.class.getName()).log(Level.WARNING, null, exception);
                    } catch (Exception exception) {
                        Logger.getLogger(TelaEdicaoClienteMouseListener.class.getName()).log(Level.SEVERE, null, exception);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Isso deveria ser impossível, mas "
                        + "você conseguiu...", "Como!?", JOptionPane.INFORMATION_MESSAGE);

                Logger.getLogger(TelaEdicaoClienteMouseListener.class.getName()).log(Level.SEVERE,
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
