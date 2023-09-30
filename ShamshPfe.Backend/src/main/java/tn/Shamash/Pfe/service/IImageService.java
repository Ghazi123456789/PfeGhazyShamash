package tn.Shamash.Pfe.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import tn.Shamash.Pfe.Entity.Image;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IImageService {
    public List<Image> list();
    public Optional<Image> getOne(int id);
    public ResponseEntity<?> save(MultipartFile imagen, Long idUser) throws IOException;
    public void delete(int id);
    public boolean exists(int id);
    public Image getByUserId(Long userId);
}
