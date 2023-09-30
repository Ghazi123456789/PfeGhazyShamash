package tn.Shamash.Pfe.Entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contrat {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
  

    private String numeroContart;
    private String statusContrat;
    private String nomAdherent;
    private Date dateSignature;
    private Date dateDemarrage;
    
    private String typeContract;
    private String devise;
    private Date dateRealisation;
    private Date datePrRevesion;
    
    
    private String chiffreAffaire;
    private String montant;
    private String nbrFacture;
    private String assureur;
    private String primeAssurance;
    
    
    private String opnion;
    private String renouvellement;
    
    private String cheque;
    private String traite;
    private String virement;
    
    private String timbre;
    private String impaye;

    private String montantLimiteFinancement;
    private String commisionFactring;
    private String delai;
    
    private String montantLimiteCredit;
    private String modePayement;
    
    
    private Date dateLimiteCredit;
    private Date DateLimiteFinancement;
    private Boolean etat ;
 
@JsonIgnore
    @OneToOne(mappedBy = "contrat", cascade = CascadeType.ALL)
    private Facture facture;
@JsonIgnore
    @OneToOne
    private User user;
}
