package tn.Shamash.Pfe.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import tn.Shamash.Pfe.Entity.Contrat;
import tn.Shamash.Pfe.Entity.Facture;
import tn.Shamash.Pfe.Entity.FileAttachment;
import tn.Shamash.Pfe.Entity.Indevedu;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Repository.ContratRepository;
import tn.Shamash.Pfe.Repository.FactureRepository;
import tn.Shamash.Pfe.Repository.FileRepository;
import tn.Shamash.Pfe.Repository.IndeveuRepository;
import tn.Shamash.Pfe.Repository.UserRepository;
import tn.Shamash.Pfe.service.EmailService;
import tn.Shamash.Pfe.service.FactureService;

@Service
public class ServiceFactureImpl implements FactureService{
	@Autowired
	    EmailService emailService;
	@Autowired 
	IndeveuRepository indeveduRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	FactureRepository factureRepo;
	
	@Autowired
	ContratRepository contratRepo;
	
	@Autowired
	FileRepository fileRepo;
	public Facture novelleDemade(Long idUser , Facture facture, List<MultipartFile> files) throws java.io.IOException{
		User user = userRepo.findById(idUser).orElse(null);
		facture.setApproved(0);
        if (user == null) {
            return null;
        }
        else
        {
        	
        facture.setUser(user);
        facture.setIndevedu(user.getIndevedu());
        factureRepo.save(facture);
        List<FileAttachment> attachments = new ArrayList<>();
        for (MultipartFile file : files) {
            FileAttachment attachment = new FileAttachment();
            attachment.setFacture(facture);
            attachment.setFileName(file.getOriginalFilename());
            try {
                attachment.setFileData(file.getBytes()); // Convertit le MultipartFile en byte[]
            } catch (IOException e) {
                e.printStackTrace(); // Gérez l'exception de manière appropriée
            }
            attachments.add(attachment);
        }

        facture.setAttachments(attachments);
        
        }
        emailService.sendEmail(
                user.getEmail(),
                "Nouvelle demande de Factorisation",
                "Merci pour votre Demande.\n  votre demande a besoin de consulter par l'un  nos administrateurs.Un email sa sera envoyer pour reponde a votre demande, Bienvenue ! ");
        
        return factureRepo.save(facture);

	}
	public List<FileAttachment> getFactureFile(Long idfacture ) {
		List<FileAttachment> ll = fileRepo.findAll();
		List<FileAttachment> lll = new ArrayList<>(); 
		for (FileAttachment fileAttachment : ll) {
			if(fileAttachment.getFacture().getId() == idfacture)
			{lll.add(fileAttachment);}
		}
		return lll;
	}
	public List<Facture> gestDemandeUSer(Long idUser){
		List<Facture> ll = factureRepo.findAll();
		List<Facture> ll2 = new ArrayList<>();
		for (Facture facture : ll) {
			if(facture.getUser().getId() == idUser) 
			{ll2.add(facture);}
		}
        
        return ll2;
	}
	public Facture getFacture(Long idfacture){
		Facture f = factureRepo.findById(idfacture).orElse(null);
		return f;
	}
	public List<Facture> getAllDEmande(){
		return  factureRepo.findAll();
	
	}
	
	public Facture etatFacture(Long idfacture , int i){
		Facture f = factureRepo.findById(idfacture).orElse(null);
		f.setApproved(i);
		if(i == 1) {
			Contrat c = new Contrat();
			c.setUser(f.getUser());
			contratRepo.save(c);
			f.setContrat(c);
			
		}
		return factureRepo.save(f);
	}

}
