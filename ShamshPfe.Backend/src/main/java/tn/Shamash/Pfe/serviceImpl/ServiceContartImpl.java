package tn.Shamash.Pfe.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Shamash.Pfe.Entity.Contrat;
import tn.Shamash.Pfe.Entity.Facture;
import tn.Shamash.Pfe.Entity.Indevedu;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Repository.ContratRepository;
import tn.Shamash.Pfe.Repository.FactureRepository;
import tn.Shamash.Pfe.Repository.IndeveuRepository;
import tn.Shamash.Pfe.Repository.UserRepository;
import tn.Shamash.Pfe.service.ServiceContart;

@Service
public class ServiceContartImpl implements ServiceContart{

	@Autowired 
	IndeveuRepository indeveduRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FactureRepository factureRepo;
	
	@Autowired
	ContratRepository contratRepo;
	
	
	public Contrat UpdateContray(Long idcont, Contrat contrat){
		Facture f = factureRepo.findById(idcont).orElse(null);
		contrat.setId(f.getContrat().getId());
		contrat.setUser(f.getUser());
		
        return contratRepo.save(contrat);
        }
	
	public Contrat getOne(Long idcont){
		Facture f  = factureRepo.findById(idcont).orElse(null);
	
		return f.getContrat();
		
		
        }
	
	public Contrat etat(Boolean etatt , Long id){
		Contrat c  = contratRepo.findById(id).orElse(null);
		c.setEtat(etatt);
		return contratRepo.save(c);
		
		
        }
        
	public List<Contrat> getallForUser(Long iduser){
		List<Contrat> ll = contratRepo.findAll();
		List<Contrat> lll = new ArrayList<>();
		for (Contrat contrat : ll) {
			if(contrat.getUser().getId() == iduser)
				lll.add(contrat);
		}
	
		return lll;
		
		
        }
	public List<Contrat> getall(){
		List<Contrat> ll = contratRepo.findAll();
		
		return ll;
		
		
        }
        
	
	public int nbrContart(){
		List<Contrat> ll = contratRepo.findAll();
		
		return ll.size();
		
		
        }
	
	public int nbrfacture(){
		List<Facture> ll = factureRepo.findAll();
		
		return ll.size();
		
		
        }
	
	public int nbrIndevedu(){
		List<Indevedu> ll = indeveduRepo.findAll();
		
		return ll.size();
		
		
        }
	
	public int nbrUser(){
		List<User> ll = userRepo.findAll();
		
		return ll.size();
		
		
        }
        
}
