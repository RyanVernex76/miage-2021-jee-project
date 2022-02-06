package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;

@Entity
public class Juicer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "juicer_id", nullable = false)
    private Integer id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "phone", length = 30)
    private String phone;

    @Column(name = "email_address", length = 50)
    private String emailAddress;

    @Column(name = "password", length = 50)
    private String password;

    @ManyToOne
    @JoinColumn(name = "juicer_account")
    private BankAccount juicerAccount;

    public BankAccount getJuicerAccount() {
        return juicerAccount;
    }

    public void setJuicerAccount(BankAccount juicerAccount) {
        this.juicerAccount = juicerAccount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}