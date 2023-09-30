package tn.Shamash.Pfe.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import tn.Shamash.Pfe.Entity.Image;
import tn.Shamash.Pfe.Exception.Mensaje;
import tn.Shamash.Pfe.service.IImageService;
import tn.Shamash.Pfe.serviceImpl.CloudinaryService;
@RestController
@RequestMapping("/image")
@CrossOrigin(origins = "*")

public class ImageController {
    @Autowired
    CloudinaryService cloudinaryService;

    @Autowired
    IImageService imagenService;
    @GetMapping("/list")
    public ResponseEntity<List<Image>> list(){
        List<Image> list = imagenService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/imageByUser")
    public Image getByUser(@RequestParam Long idUser){
        return imagenService.getByUserId(idUser);
    }

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestPart("file") MultipartFile image,@RequestParam Long idUser)throws IOException {
        return imagenService.save(image,idUser);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id)throws IOException {
        if(!imagenService.exists(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Image imagen = imagenService.getOne(id).get();
        Map result = cloudinaryService.delete(imagen.getImagenId());
        imagenService.delete(id);
        return new ResponseEntity(new Mensaje("imagen eliminada"), HttpStatus.OK);
    }

}
