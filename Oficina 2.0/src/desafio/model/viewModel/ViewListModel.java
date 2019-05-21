package desafio.model.viewModel;

import java.util.List;
import javax.swing.AbstractListModel;

/**
 *
 * @author João Victor Bonfim
 * @param <Tipo>
 */
public class ViewListModel<Tipo> extends AbstractListModel {

    List<Tipo> conteudo;

    @Override
    public int getSize() {
        return conteudo.size();
    }

    @Override
    public Tipo getElementAt(int index) {
        return conteudo.get(index);
    }

    public ViewListModel(List<Tipo> conteudo) {
        this.conteudo = conteudo;
    }
}
