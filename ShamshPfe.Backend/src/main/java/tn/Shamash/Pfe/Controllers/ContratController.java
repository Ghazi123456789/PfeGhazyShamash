package tn.Shamash.Pfe.Controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.Shamash.Pfe.Entity.Contrat;
import tn.Shamash.Pfe.Entity.Facture;
import tn.Shamash.Pfe.Exception.EntityNotFound;
import tn.Shamash.Pfe.service.UsersInformation;
import tn.Shamash.Pfe.serviceImpl.IndeveduServiceImpl;
import tn.Shamash.Pfe.serviceImpl.ServiceContartImpl;
import tn.Shamash.Pfe.serviceImpl.ServiceFactureImpl;

@RestController
@RequestMapping("/contrat")
@CrossOrigin(origins = "*")
public class ContratController {

   	UsersInformation usersInformation;
    IndeveduServiceImpl indevserv;
    ServiceFactureImpl serviceFact;
    ServiceContartImpl serviceContrat;
    public ContratController(UsersInformation usersInformation,IndeveduServiceImpl indevservv,ServiceFactureImpl serviceFactt , ServiceContartImpl serviceContratt) {
        this.usersInformation = usersInformation;
        this.indevserv = indevservv;
        this.serviceFact = serviceFactt;
        this.serviceContrat = serviceContratt;
    }
    
    @PostMapping("/addDemande/{idContrat}")
    public Contrat newDemande(@RequestBody Contrat c, @PathVariable Long idContrat) throws EntityNotFound {
      return serviceContrat.UpdateContray(idContrat, c);
    }
    
    @PutMapping("/ChangeEtat/{idContrat}/{etat}")
    public Contrat ChangeEtat( @PathVariable Long idContrat,@PathVariable Boolean etat) throws EntityNotFound {
      return serviceContrat.etat(etat,idContrat);
    }
    
    @GetMapping("/getContrat/{id}")
    public Contrat getOne(@PathVariable Long id) {
      return serviceContrat.getOne(id);
    }
    
    @GetMapping("/getAll/{id}")
    public List<Contrat> getall(@PathVariable Long id) {
      return serviceContrat.getallForUser(id);
    }
    @GetMapping("/getAll")
    public List<Contrat> getal() {
      return serviceContrat.getall();
    }
    
    @GetMapping("/getNbrFacture")
    public int getnbrFac() {
      return serviceContrat.nbrfacture();
    }
    @GetMapping("/getNbrContrat")
    public int getNbrContrat() {
      return serviceContrat.nbrContart();
    }
    @GetMapping("/getNbrUser")
    public int getNbrUser() {
      return serviceContrat.nbrUser();
    }
    @GetMapping("/getNbrIndevedu")
    public int getNbrIndevedu() {
      return serviceContrat.nbrIndevedu();
    }
}
