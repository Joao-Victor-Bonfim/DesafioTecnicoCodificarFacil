package desafio.model.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author João Victor Bonfim
 */
@Entity
@Table(name = "vendedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vendedor.findAll", query = "SELECT v FROM Vendedor v"),
    @NamedQuery(name = "Vendedor.findByNroRegistro", query = "SELECT v FROM Vendedor v WHERE v.nroRegistro = :nroRegistro"),
    @NamedQuery(name = "Vendedor.findByNome", query = "SELECT v FROM Vendedor v WHERE v.nome = :nome")})
public class Vendedor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "nro_registro")
    private Integer nroRegistro;
    @Basic(optional = false)
    @Column(name = "nome")
    private String nome;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private List<Orcamento> orcamentoList;

    public Vendedor() {
    }

    public Vendedor(Integer nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public Vendedor(Integer nroRegistro, String nome) {
        this.nroRegistro = nroRegistro;
        this.nome = nome;
    }

    public Integer getNroRegistro() {
        return nroRegistro;
    }

    public void setNroRegistro(Integer nroRegistro) {
        this.nroRegistro = nroRegistro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @XmlTransient
    public List<Orcamento> getOrcamentoList() {
        return orcamentoList;
    }

    public void setOrcamentoList(List<Orcamento> orcamentoList) {
        this.orcamentoList = orcamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nroRegistro != null ? nroRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Vendedor)) {
            return false;
        }
        Vendedor other = (Vendedor) object;
        return !((this.nroRegistro == null && other.nroRegistro != null) || (this.nroRegistro != null && !this.nroRegistro.equals(other.nroRegistro)));
    }

    @Override
    public String toString() {
        return "desafio.model.domain.Vendedor[ nroRegistro=" + nroRegistro + " ]";
    }
    
    public static boolean isValidNome(String nome) {
        //TO DO
        return true;
    }
    
    public static boolean isValidNroRegistro(Integer nroRegistro) {
        return nroRegistro >= 0;
    }
    
    public static boolean isValidVendedor(Vendedor vendedor) {
        if(vendedor == null)
            return false;
        return isValidNome(vendedor.nome) && isValidNroRegistro(vendedor.nroRegistro);
    }
    
}
