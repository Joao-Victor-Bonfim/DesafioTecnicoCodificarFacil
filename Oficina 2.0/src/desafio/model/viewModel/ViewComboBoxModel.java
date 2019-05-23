package desafio.model.viewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.MutableComboBoxModel;

/**
 *
 * @author João Victor Bonfim
 * @param <E>
 */
public class ViewComboBoxModel<E> extends AbstractListModel<E> implements MutableComboBoxModel<E>, Serializable {

    private List<E> itens;
    private Object itemSelecionado;

    public ViewComboBoxModel(List<E> itens) {
        this.itens = itens;
    }

    public ViewComboBoxModel() {
        this(new ArrayList());
    }

    @Override
    public int getSize() {
        return itens.size();
    }

    @Override
    public E getElementAt(int index) {
        if (index >= 0 && index < itens.size()) {
            return itens.get(index);
        } else {
            return null;
        }
    }

    @Override
    public void addElement(E item) {
        itens.add(item);
        int pos = itens.indexOf(item);
        fireIntervalAdded(this, pos, pos);
        if (itens.size() == 1 && itemSelecionado == null && item != null) {
            setSelectedItem(item);
        }
    }

    public int getIndexOf(Object anObject) {
        return itens.indexOf(anObject);
    }

    @Override
    public void removeElement(Object obj) throws ClassCastException {
        itens.remove((E) obj);
    }

    @Override
    public void insertElementAt(E item, int index) {
        itens.add(index, item);
        fireIntervalAdded(this, index, index);
    }

    @Override
    public void removeElementAt(int index) {
        if (getElementAt(index).equals(itemSelecionado)) {
            if (index == 0) {
                setSelectedItem(getSize() == 1 ? null : getElementAt(1));
            } else {
                setSelectedItem(getElementAt(index - 1));
            }
        }

        itens.remove(index);

        fireIntervalRemoved(this, index, index);
    }

    @Override
    public void setSelectedItem(Object objeto) {
        if ((itemSelecionado != null && !itemSelecionado.equals(objeto))
                || (itemSelecionado == null && objeto != null)) {
            itemSelecionado = objeto;
            fireContentsChanged(this, -1, -1);
        }
    }

    @Override
    public Object getSelectedItem() {
        return itemSelecionado;
    }

    public void removeAllElements() {
        itens.removeAll(itens);
        itemSelecionado = null;
    }

}
