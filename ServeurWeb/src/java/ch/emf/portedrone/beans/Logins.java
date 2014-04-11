/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.emf.portedrone.beans;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ramosdasilm
 */
@Entity
@Table(name = "t_logins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Logins.findAll", query = "SELECT l FROM Logins l"),
    @NamedQuery(name = "Logins.findByPkLogin", query = "SELECT l FROM Logins l WHERE l.pkLogin = :pkLogin"),
    @NamedQuery(name = "Logins.findByLogin", query = "SELECT l FROM Logins l WHERE l.login = :login"),
    @NamedQuery(name = "Logins.findByPassword", query = "SELECT l FROM Logins l WHERE l.password = :password")})
public class Logins implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_login")
    private Integer pkLogin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "login")
    private String login;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 32)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkLogin")
    private List<Vols> volsList;

    public Logins() {
    }

    public Logins(Integer pkLogin) {
        this.pkLogin = pkLogin;
    }

    public Logins(Integer pkLogin, String login, String password) {
        this.pkLogin = pkLogin;
        this.login = login;
        this.password = password;
    }

    public Integer getPkLogin() {
        return pkLogin;
    }

    public void setPkLogin(Integer pkLogin) {
        this.pkLogin = pkLogin;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public List<Vols> getVolsList() {
        return volsList;
    }

    public void setVolsList(List<Vols> volsList) {
        this.volsList = volsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkLogin != null ? pkLogin.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Logins)) {
            return false;
        }
        Logins other = (Logins) object;
        if ((this.pkLogin == null && other.pkLogin != null) || (this.pkLogin != null && !this.pkLogin.equals(other.pkLogin))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.emf.portedrone.beans.Logins[ pkLogin=" + pkLogin + " ]";
    }
    
}
