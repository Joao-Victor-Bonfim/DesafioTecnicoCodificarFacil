package desafio.view.render;

import desafio.util.format.TextToSwingFormatter;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

/**
 *
 * @author João Victor Bonfim
 */
public class ListCellRendererGeral implements ListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        JLabel componente = new JLabel(new TextToSwingFormatter().format(value.toString()));
        componente.setOpaque(true);

        if (isSelected) {
            componente.setBackground(list.getSelectionBackground());
            componente.setForeground(list.getSelectionForeground());
        } else {
            componente.setBackground(list.getBackground());
            componente.setForeground(list.getForeground());
        }

        return componente;
    }

}
