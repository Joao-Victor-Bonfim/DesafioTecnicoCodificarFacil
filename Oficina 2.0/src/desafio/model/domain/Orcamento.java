package desafio.model.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author João Victor Bonfim
 */
@Entity
@Table(name = "orcamento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orcamento.findAll", query = "SELECT o FROM Orcamento o"),
    @NamedQuery(name = "Orcamento.findByIdOrcamento", query = "SELECT o FROM Orcamento o WHERE o.idOrcamento = :idOrcamento"),
    @NamedQuery(name = "Orcamento.findByDataEHora", query = "SELECT o FROM Orcamento o WHERE o.dataEHora = :dataEHora"),
    @NamedQuery(name = "Orcamento.findByValor", query = "SELECT o FROM Orcamento o WHERE o.valor = :valor")})
public class Orcamento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_orcamento")
    private Integer idOrcamento;
    @Basic(optional = false)
    @Column(name = "data_e_hora")
    @Temporal(TemporalType.DATE)
    private Date dataEHora;
    @Basic(optional = false)
    @Column(name = "descricao")
    private String descricao;
    @Basic(optional = false)
    @Column(name = "valor")
    private double valor;
    @JoinColumn(name = "cliente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @JoinColumn(name = "vendedor", referencedColumnName = "nro_registro")
    @ManyToOne(optional = false)
    private Vendedor vendedor;

    public Orcamento() {
    }

    public Orcamento(Integer idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Orcamento(Integer idOrcamento, Date dataEHora, String descricao, double valor) {
        this.idOrcamento = idOrcamento;
        this.dataEHora = dataEHora;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Integer getIdOrcamento() {
        return idOrcamento;
    }

    public void setIdOrcamento(Integer idOrcamento) {
        this.idOrcamento = idOrcamento;
    }

    public Date getDataEHora() {
        return dataEHora;
    }

    public void setDataEHora(Date dataEHora) {
        this.dataEHora = dataEHora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        this.cliente.getOrcamentoList().add(this);
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
        this.vendedor.getOrcamentoList().add(this);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idOrcamento != null ? idOrcamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {

        if (!(object instanceof Orcamento)) {
            return false;
        }

        Orcamento other = (Orcamento) object;
        return !((this.idOrcamento == null && other.idOrcamento != null) || (this.idOrcamento != null && !this.idOrcamento.equals(other.idOrcamento)));
    }

    @Override
    public String toString() {
        return "Orçamento Nº " + idOrcamento + System.lineSeparator()
                + "Preço (R$): " + valor;
    }

    public static boolean isValidValor(Double valor) {
        //TO DO
        return valor != null;
    }

    public static boolean isValidIdOrcamento(Integer idOrcamento) {
        if (idOrcamento == null) {
            return false;
        }
        return idOrcamento >= 0;
    }

    public static boolean isValidDescricao(String descricao) {
        if (descricao == null) {
            return false;
        }
        return !descricao.isEmpty();
    }

    public static boolean isValidDataEHora(Date dataEHora) {
        if (dataEHora == null) {
            return false;
        }

        Date agora = new Date();
        return dataEHora.before(agora) || dataEHora.equals(agora);
    }

    public static boolean isValidCliente(Cliente cliente) {
        return Cliente.isValidCliente(cliente);
    }

    public static boolean isValidVendedor(Vendedor vendedor) {
        return Vendedor.isValidVendedor(vendedor);
    }

    public static boolean isValidOrcamento(Orcamento Orcamento) {
        if (Orcamento == null) {
            return false;
        }

        return isValidValor(Orcamento.valor)
                && isValidIdOrcamento(Orcamento.idOrcamento)
                && isValidDescricao(Orcamento.descricao)
                && isValidDataEHora(Orcamento.dataEHora)
                && isValidCliente(Orcamento.cliente)
                && isValidVendedor(Orcamento.vendedor);
    }
}
