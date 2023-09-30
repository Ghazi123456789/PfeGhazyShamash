package tn.Shamash.Pfe.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import tn.Shamash.Pfe.Entity.Facture;
import tn.Shamash.Pfe.Entity.FileAttachment;
import tn.Shamash.Pfe.Entity.Indevedu;
import tn.Shamash.Pfe.Exception.EntityNotFound;
import tn.Shamash.Pfe.service.FactureService;
import tn.Shamash.Pfe.service.UsersInformation;
import tn.Shamash.Pfe.serviceImpl.IndeveduServiceImpl;
import tn.Shamash.Pfe.serviceImpl.ServiceFactureImpl;

@RestController
@RequestMapping("/facture")
@CrossOrigin(origins = "*")
public class FactureController {
	
	
	   	UsersInformation usersInformation;
	    IndeveduServiceImpl indevserv;
	    ServiceFactureImpl serviceFact;
	    public FactureController(UsersInformation usersInformation,IndeveduServiceImpl indevservv,ServiceFactureImpl serviceFactt) {
	        this.usersInformation = usersInformation;
	        this.indevserv = indevservv;
	        this.serviceFact = serviceFactt;

	    }
	    
	    @PostMapping("/addDemande/{userId}")
	    public Facture nouvelleDemande(@PathVariable Long userId, @RequestParam("files") List<MultipartFile> files, @RequestPart("facture") Facture facture) throws IOException, java.io.IOException {
	        return serviceFact.novelleDemade(userId, facture, files);
	    }
	    @GetMapping("/getFactureUser/{idUser}")
	    public List<Facture> getFactureUser(@PathVariable Long idUser) throws EntityNotFound {
	      return serviceFact.gestDemandeUSer(idUser);
	    }
	    @GetMapping("/getFactureFile/{idfacture}")
	    public List<FileAttachment> getFactureFile(@PathVariable Long idfacture) {
	    return serviceFact.getFactureFile(idfacture);
	    }
	    @GetMapping("/getAll")
	    public List<Facture> getAll() throws EntityNotFound {
	      return serviceFact.getAllDEmande();
	    }
	    @GetMapping("/getFacture/{idUser}")
	    public Facture getOne(@PathVariable Long idUser) {
	      return serviceFact.getFacture(idUser);
	    }
	    @PutMapping("/etatFacture/{idUser}/{val}")
	    public Facture accepteFacture(@PathVariable Long idUser , @PathVariable int val) {
	      return serviceFact.etatFacture(idUser , val);
	    }
	    
}
