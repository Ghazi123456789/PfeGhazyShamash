package tn.Shamash.Pfe.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.Shamash.Pfe.Entity.Image;
import tn.Shamash.Pfe.Entity.User;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
        List<Image> findByOrderById();
        Image findByUser(User user);
        }