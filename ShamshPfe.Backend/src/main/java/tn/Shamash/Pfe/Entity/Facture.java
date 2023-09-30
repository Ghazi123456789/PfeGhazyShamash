package tn.Shamash.Pfe.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Facture {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
  

    private String name;
    private String nomRespo;
    private String emailRespo;
    private String telRespo;
    
    
    private String numFacture;
    private String montantFacture;
    private Date DateCreation;
 
    
    
    private String periode;
    private Date debutPeriode;
    private Date finPeriode;

    private int approved;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user")
    private User user;
    
@JsonIgnore
    @ManyToOne
    @JoinColumn(name = "indevedu")
    private Indevedu  indevedu;

@OneToOne
private Contrat contrat;

@JsonIgnore
@OneToMany(mappedBy = "facture", cascade = CascadeType.ALL, orphanRemoval = true)
private List<FileAttachment> attachments;
}
