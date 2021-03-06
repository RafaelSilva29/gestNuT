package smartThings.gestNuT.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Please provide a valid e-mail")
	@NotEmpty(message = "Please provide an e-mail")
    private String email;

    @Column(name = "password")
    @Transient
    private String password;

    @Column(name = "first_name", nullable = false)
    @NotEmpty(message = "Please provide your first name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
	@NotEmpty(message = "Please provide your last name")
	private String lastName;

    // <USER> , <ADMIN>
    @Column(name = "role")
    private String role;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "confirmation_token")
	private String confirmationToken;

    @Column(name = "enabled")
	private boolean enabled;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user")
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "user")
    private Set<Parcel> parcels = new HashSet<>();

    public User() { 
        
    }

    public User(String email, String pass,  String firstname, String lastname, String phone, String role) {
        this.email = email;
        this.password = pass;
        this.firstName = firstname;
        this.lastName = lastname;
        this.phoneNumber = phone;
        this.role = role;
    }

	// Getters
    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPassword() { return password; }
    public String getConfirmationToken() { return confirmationToken; }
    public boolean getEnabled() { return enabled; }
    public String getRole() { return role; }
    public String getPhoneNumber() { return this.phoneNumber; }
    public Set<Address> getAddresses() {
        Set<Address> resp = new HashSet<>();
        for(Address a : addresses) { resp.add(a); }
        return resp;
    }
    public Set<Parcel> getParcels() {
        Set<Parcel> resp = new HashSet<>();
        for(Parcel p : parcels) { resp.add(p); }
        return resp;
    }
    
    // Setters
	public void setConfirmationToken(String confirmationToken) { this.confirmationToken = confirmationToken; }
    public void setEnabled(boolean value) { this.enabled = value; }
    public void setPassword(String password) { this.password = password; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setEmail(String email) { this.email = email; }
    public void setRole(String role) { this.role = role; }
    public void setPhoneNumber(String phone) { this.phoneNumber = phone; }

    @Override
    public String toString(){
        String user = String.format(
            "User[ID: %d, FirstName: '%s', LastName: '%s', Email: '%s', Pass: '%s', Phone: '%s', Role: '%s']%n",
            id, firstName, lastName, email, password, phoneNumber, role);
        if (addresses != null) {
            for(Address address : addresses) {
                user += address.toString();
            }
        }
        if (parcels != null) {
            for(Parcel parcel : parcels) {
                user += parcel.toString();
            }
        }
        return user;
    }

}
