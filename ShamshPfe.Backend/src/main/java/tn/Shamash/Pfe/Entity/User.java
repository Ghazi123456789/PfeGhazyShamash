package tn.Shamash.Pfe.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "utilisateur")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    private String gender;
    private String adresse;
    private String description;
    private String cin;
    private String tel;

    @Column(name = "age")
    private int age;
    boolean isLocked=true;
    @Transient
    String accessToken;

    @Transient
    String refreshToken;

    // ManyToMany Relations
    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Role> roles = new HashSet<>();

    @Setter(value = AccessLevel.NONE)
    @Basic(optional = false)
    @CreationTimestamp
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt = new Date();

    @Setter(value = AccessLevel.NONE)
    @UpdateTimestamp
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt = new Date();

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    Image image;
    @JsonIgnore

    @OneToOne
    Indevedu indevedu;
    @JsonIgnore

    @OneToMany(mappedBy = "user")
    private List<Facture> Factures;

    public User() {
    }

    public User(long id, String firstName, String name, String lastName, String username, String email, String password, boolean enabled, Date dateDeNaissance, String poste,@NonNull int age, @NonNull String phoneNumber, boolean isLocked, String accessToken, String refreshToken, Set<Role> roles, Date createdAt, Date updatedAt, Image image) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.isLocked = isLocked;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.roles = roles;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.image = image;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }






    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }



    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }



 



//    @Override
//    public String toString() {
//        return "User{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", name='" + name + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", username='" + username + '\'' +
//                ", email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", enabled=" + enabled +
//                ", dateDeNaissance=" + dateDeNaissance +
//                ", poste=" + poste +
//                ", age=" + age +
//                ", phoneNumber='" + phoneNumber + '\'' +
//                ", isLocked=" + isLocked +
//                ", accessToken='" + accessToken + '\'' +
//                ", refreshToken='" + refreshToken + '\'' +
//                ", roles=" + roles +
//                ", createdAt=" + createdAt +
//                ", updatedAt=" + updatedAt +
//                ", offreEmplois=" + offreEmplois +
//                ", attestations=" + attestations +
//                ", documentEntreprises=" + documentEntreprises +
//                ", conges=" + conges +
//                ", image=" + image +
//                ", missions=" + missions +
//                ", autorisation=" + autorisation +
//                ", Teletravail=" + Teletravail +
//                ", informationContrat=" + informationContrat +
//                '}';
//    }
}
