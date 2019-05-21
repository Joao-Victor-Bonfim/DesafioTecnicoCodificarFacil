package desafio.view.telas.telaPrincipal.componentes.palcoResultado;

import desafio.model.domain.Orcamento;
import desafio.util.format.TextToSwingFormatter;
import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author João Victor Bonfim
 */
public class ConteudoOrcamento extends JPanel {

    private JLabel idOrcamento;
    private JLabel dataEHora;
    private JLabel descricao;
    private JLabel preco;
    private JLabel registroVendedor;
    private JLabel registroCliente;

    public ConteudoOrcamento(Orcamento orcamento) {
        super(new GridLayout(8, 2));
        
        initComponents(orcamento);
    }
    
    private void initComponents(Orcamento orcamento) {
        
        idOrcamento = new JLabel();
        idOrcamento.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JLabel("ID:"));
        add(idOrcamento);
        
        dataEHora = new JLabel();
        dataEHora.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JLabel("Data e hora (DD/MM/AAAA HH:mm):"));
        add(dataEHora);
        
        descricao = new JLabel();
        descricao.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JLabel("Descrição:"));
        add(descricao);
        
        preco = new JLabel();
        preco.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JLabel("Preço (R$):"));
        add(preco);
        
        registroVendedor = new JLabel();
        registroVendedor.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JLabel("Vendedor:"));
        add(registroVendedor);
        
        registroCliente = new JLabel();
        registroCliente.setHorizontalAlignment(SwingConstants.CENTER);
        add(new JLabel("Cliente:"));
        add(registroCliente);
        
        updateOrcamento(orcamento);
    }

    private void updateTextFromIdOrcamento(String text) {
        idOrcamento.setText(text);
    }
    
    private void updateTextFromDataEHora(String text) {
        dataEHora.setText(text);
    }

    private void updateTextFromDescricao(String text) {
        descricao.setText(text);
    }
    
    private void updateTextFromPreco(String text) {
        preco.setText(text);
    }
    
    private void updateTextFromRegistroVendedor(String text) {
        registroVendedor.setText(text);
    }
    
    private void updateTextFromRegistroCliente(String text) {
        registroCliente.setText(text);
    }
    
    public void updateOrcamento(Orcamento orcamento) {
        updateTextFromIdOrcamento(orcamento.getIdOrcamento().toString());
        updateTextFromDataEHora(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(orcamento.getDataEHora()));
        updateTextFromDescricao(orcamento.toString());
        updateTextFromPreco(new DecimalFormat("#.00").format(orcamento.getValor()));
        updateTextFromRegistroVendedor(new TextToSwingFormatter().format(orcamento.getVendedor().toString()));
        updateTextFromRegistroCliente(new TextToSwingFormatter().format(orcamento.getCliente().toString()));
    }

}
