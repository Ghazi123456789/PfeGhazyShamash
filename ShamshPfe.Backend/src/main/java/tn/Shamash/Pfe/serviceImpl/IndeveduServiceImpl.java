package tn.Shamash.Pfe.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import tn.Shamash.Pfe.Entity.Indevedu;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Exception.AccountLockedException;
import tn.Shamash.Pfe.Repository.IndeveuRepository;
import tn.Shamash.Pfe.Repository.UserRepository;
import tn.Shamash.Pfe.Request.SignupRequest;
import tn.Shamash.Pfe.service.IndeveduService;
@Service
public class IndeveduServiceImpl implements IndeveduService{

	@Autowired 
	IndeveuRepository indeveduRepo;
	
	@Autowired
	UserRepository userRepo;
	
	public ResponseEntity<?> updateFicheIndevedu(Long idUser , Indevedu indevedu){
		User user = userRepo.findById(idUser).orElse(null);
	 	Indevedu i = indeveduRepo.findById(user.getIndevedu().getId()).orElse(null);
		indevedu.setId(i.getId());
		indeveduRepo.saveAndFlush(indevedu);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("User Not Found");
        }
        else
        {
        	user.setIndevedu(indevedu);
        	userRepo.save(user);
            return ResponseEntity.status(HttpStatus.OK).body("Information update");

        }
        
        
	}
	public Indevedu getIndByUserId(Long idUser ){
		User user = userRepo.findById(idUser).orElse(null);
	 	
        if (user == null) {
            return null;
        }
        else
        {
        	if(user.getIndevedu() != null)
            return user.getIndevedu();
        	else
        		return new Indevedu();
        }
	}
	
	
}
