package tn.Shamash.Pfe.service;

import org.springframework.http.ResponseEntity;

import tn.Shamash.Pfe.Entity.Indevedu;

public interface IndeveduService {
	ResponseEntity<?> updateFicheIndevedu(Long idUser , Indevedu indevedu);
	Indevedu getIndByUserId(Long idUser );
}
