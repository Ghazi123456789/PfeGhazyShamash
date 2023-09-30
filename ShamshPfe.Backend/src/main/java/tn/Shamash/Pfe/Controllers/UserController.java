package tn.Shamash.Pfe.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.Shamash.Pfe.Dto.Response.ResponseMessage;
import tn.Shamash.Pfe.Dto.Response.UserResponse;
import tn.Shamash.Pfe.Entity.Indevedu;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Exception.EntityNotFound;
import tn.Shamash.Pfe.service.IndeveduService;
import tn.Shamash.Pfe.service.UsersInformation;
import tn.Shamash.Pfe.serviceImpl.IndeveduServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    UsersInformation usersInformation;
    IndeveduServiceImpl indevserv;
    public UserController(UsersInformation usersInformation,IndeveduServiceImpl indevservv) {
        this.usersInformation = usersInformation;
        this.indevserv = indevservv;
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers(){
        return usersInformation.getAllUsers();
    }

    @DeleteMapping("/deleteUser/{idUser}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long idUser){
        String message = "";
        message = usersInformation.deleteUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));

    }
 /*   @PutMapping("/updateUser/{idUser}")
    public ResponseEntity<ResponseMessage> updateUser(@RequestBody UserUpdate userUpdate, @PathVariable Long idUser) throws EntityNotFound {
        String message = "";
        message = usersInformation.updateUser(userUpdate,idUser);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }*/
    
    @PostMapping("/updateIndevedu/{idUser}")
    public ResponseEntity<?> updateIndevedu(@RequestBody Indevedu i, @PathVariable Long idUser) throws EntityNotFound {
      return indevserv.updateFicheIndevedu(idUser, i);
    }
    @GetMapping("/getUserById/{idUser}")
    public User getByid(@PathVariable Long idUser){
        return  usersInformation.getById(idUser);
    }
    @GetMapping("/getIndeveduById/{idUser}")
    public Indevedu getIndeveduByid(@PathVariable Long idUser){
        return  indevserv.getIndByUserId(idUser);
    }
    
    @GetMapping("/activeUser/{idUser}")
    public User activeUser(@PathVariable Long idUser){
        return  usersInformation.activeUser(idUser);
    }
    @PutMapping("archiveUser/{idUser}")
    public ResponseEntity<ResponseMessage> archiveuser(@PathVariable Long idUser){
        String message = "";
        message = usersInformation.archiverUser(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    }

    @GetMapping("/getNbreHomme")
    private ResponseEntity<Integer> getNbreHomme(){
        return ResponseEntity.ok(usersInformation.getNbreUsersHomme());
    }
}
