package tn.Shamash.Pfe.Entity;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "file_attachments")
@Getter
@Setter
@Data
public class FileAttachment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Lob
    private byte[] fileData;
    @ManyToOne
    @JoinColumn(name = "facture_id", nullable = false)
    private Facture facture;

    // Constructors, getters, and setters

    public FileAttachment() {
    }

    public FileAttachment(String fileName, Facture facture) {
        this.fileName = fileName;
        this.facture = facture;
    }

    // Getters and setters
    // ...
}
