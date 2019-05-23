package desafio.controller;

import desafio.model.domain.Cliente;
import desafio.model.domain.Orcamento;
import desafio.model.domain.Vendedor;
import desafio.view.telas.telaPrincipal.componentes.PalcoResultado;
import desafio.view.telas.telaPrincipal.componentes.palcoResultado.conteudo.ConteudoCliente;
import desafio.view.telas.telaPrincipal.componentes.palcoResultado.conteudo.ConteudoOrcamento;
import desafio.view.telas.telaPrincipal.componentes.palcoResultado.conteudo.ConteudoVendedor;
import java.util.List;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author João Victor Bonfim
 */
public class PalcoResultadoListSelectionListener implements ListSelectionListener {

    private JList origem;
    private PalcoResultado alvo;

    public PalcoResultadoListSelectionListener(JList origem, PalcoResultado alvo) {
        this.origem = origem;
        this.alvo = alvo;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource().equals(origem)) {
            List valoresSelecionados = origem.getSelectedValuesList();
            if (valoresSelecionados.size() == 1) {
                Object valor = valoresSelecionados.get(0);
                if (valor instanceof Cliente) {
                    alvo.updateConteudo(new ConteudoCliente((Cliente) valor));
                } else if (valor instanceof Vendedor) {
                    alvo.updateConteudo(new ConteudoVendedor((Vendedor) valor));
                } else if (valor instanceof Orcamento) {
                    alvo.updateConteudo(new ConteudoOrcamento((Orcamento) valor));
                } else if (valor == null) {
                    throw new NullPointerException("O valor selecionado é nulo."
                            + System.lineSeparator() + "NANI!?");
                } else {
                    throw new UnsupportedOperationException("Ainda não existe "
                            + "tratamento para Objetos do tipo: " + valor.getClass());
                }
            }
        }
    }

}
