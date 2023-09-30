package tn.Shamash.Pfe.service;

import tn.Shamash.Pfe.Dto.Response.UserResponse;
import tn.Shamash.Pfe.Entity.User;
import tn.Shamash.Pfe.Exception.EntityNotFound;

import java.util.List;

public interface UsersInformation {
    public String deleteUser(Long idUser);
//    public String updateUser(UserUpdate userUpdate ,Long iduser) throws EntityNotFound;
    public List<User> getAllUsers();
    public Integer getNbreUsersHomme();
    public User getById(Long idUser);
    public User activeUser(Long idUser);
    public String archiverUser(Long idUser);
}
