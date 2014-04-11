/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.emf.portedrone.beans;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ramosdasilm
 */
@Entity
@Table(name = "t_vols")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vols.findAll", query = "SELECT v FROM Vols v"),
    @NamedQuery(name = "Vols.findByPkVol", query = "SELECT v FROM Vols v WHERE v.pkVol = :pkVol"),
    @NamedQuery(name = "Vols.findByTempsDepart", query = "SELECT v FROM Vols v WHERE v.tempsDepart = :tempsDepart"),
    @NamedQuery(name = "Vols.findByTempsFin", query = "SELECT v FROM Vols v WHERE v.tempsFin = :tempsFin"),
    @NamedQuery(name = "Vols.findByStatus", query = "SELECT v FROM Vols v WHERE v.status = :status")})
public class Vols implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pk_vol")
    private Integer pkVol;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temps_depart")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempsDepart;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temps_fin")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tempsFin;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status")
    private int status;
    @JoinColumn(name = "fk_login", referencedColumnName = "pk_login")
    @ManyToOne(optional = false)
    private Logins fkLogin;

    public Vols() {
    }

    public Vols(Integer pkVol) {
        this.pkVol = pkVol;
    }

    public Vols(Integer pkVol, Date tempsDepart, Date tempsFin, int status) {
        this.pkVol = pkVol;
        this.tempsDepart = tempsDepart;
        this.tempsFin = tempsFin;
        this.status = status;
    }

    public Integer getPkVol() {
        return pkVol;
    }

    public void setPkVol(Integer pkVol) {
        this.pkVol = pkVol;
    }

    public Date getTempsDepart() {
        return tempsDepart;
    }

    public void setTempsDepart(Date tempsDepart) {
        this.tempsDepart = tempsDepart;
    }

    public Date getTempsFin() {
        return tempsFin;
    }

    public void setTempsFin(Date tempsFin) {
        this.tempsFin = tempsFin;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Logins getFkLogin() {
        return fkLogin;
    }

    public void setFkLogin(Logins fkLogin) {
        this.fkLogin = fkLogin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkVol != null ? pkVol.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vols)) {
            return false;
        }
        Vols other = (Vols) object;
        if ((this.pkVol == null && other.pkVol != null) || (this.pkVol != null && !this.pkVol.equals(other.pkVol))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.emf.portedrone.beans.Vols[ pkVol=" + pkVol + " ]";
    }
    
}
