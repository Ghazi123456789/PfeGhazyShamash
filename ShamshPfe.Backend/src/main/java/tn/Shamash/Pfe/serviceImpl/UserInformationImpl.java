package tn.Shamash.Pfe.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.Shamash.Pfe.Dto.Response.UserResponse;
import tn.Shamash.Pfe.Entity.Indevedu;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Exception.EntityNotFound;
import tn.Shamash.Pfe.Repository.IndeveuRepository;
import tn.Shamash.Pfe.Repository.UserRepository;
import tn.Shamash.Pfe.service.UsersInformation;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class UserInformationImpl implements UsersInformation {

    @Autowired
    UserRepository userRepository;
    @Autowired
    IndeveuRepository IndRepository;

    @Override
    public String deleteUser(Long idUser) {
        if (idUser == null){
            return "inserer idUser Svp";
        }
        User user = userRepository.findById(idUser).get();
        for (User user1 :userRepository.findAll()){
            if (user1.equals(user)){
                userRepository.delete(user);
                return "User supprimé avec success";
            }
        }
        return "utilisateur introuvable avec id:"+idUser;
    }

    /* @Override
    public String updateUser(UserUpdate userUpdate , Long iduser) throws EntityNotFound {
        User user = userRepository.findById(iduser).orElseThrow(() -> new EntityNotFoundException("User Not Found with username: " ));


        user.setGender(userUpdate.getGender());
        user.setAdresse(userUpdate.getAdresse());
 
        user.setAge(userUpdate.getAge());


        userRepository.saveAndFlush(user);
        return "utilisateur modifier avec succée" +user;
    }
*/
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Integer getNbreUsersHomme() {
        Integer x=0 ;

        for (User user : userRepository.findAll()){
            String gender = user.getGender();
            if (gender != null) {
                if (gender.equals("homme")) {
                    x++;
            }

            }
        }
        return x;
    }

    @Override
    public User getById(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        return user;
    }

    @Override
    public String archiverUser(Long idUser) {
        User user = userRepository.findById(idUser).orElse(null);
        if (user.isLocked()==true){
            user.setLocked(false);
            userRepository.saveAndFlush(user);
            return "utilisateur archivé avec succéss";
        }
        if (user.isLocked()==false)
            user.setLocked(true);
        userRepository.saveAndFlush(user);
        return "ban est desactivé";
    }

	@Override
	public User activeUser(Long idUser) {
		User user = userRepository.findById(idUser).orElse(null);
		user.setEnabled(!user.isEnabled());
		Indevedu indevedu = new Indevedu();
		user.setIndevedu(indevedu);
		IndRepository.save(indevedu);
		userRepository.saveAndFlush(user);
		return null;
	}

}
