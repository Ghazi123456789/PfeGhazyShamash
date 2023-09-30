package tn.Shamash.Pfe.Entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import tn.Shamash.Pfe.Enum.Langue;
@Entity
@Getter
@Setter
public class Indevedu {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private long id;
	  

	    private String name;
	    private String prenom;
	    private String nationalite;
	    private Langue Langue;
	    private String profession;
	    private Date dateDeNaissance;
	    private String codeAdherent;
	    private String codeAcheteur;
	    private String RIB;
	    private String banque;
	    private String agence;
	    
	    private String typePiece;
	    private String CIN;
	    private Date dateDelivre;
	    private String lieuCIN;
	    
	    
	    private String adresse;
	    private String ville;
	    private String state;
	    private String zip;
	    private String telephone;
	    private String fax;
	    private String email;



	    
}
