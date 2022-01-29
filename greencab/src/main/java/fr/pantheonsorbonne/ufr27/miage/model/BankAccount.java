package fr.pantheonsorbonne.ufr27.miage.model;

import javax.persistence.*;

@Table(name = "Bank_Account")
@Entity
public class BankAccount {
    @Id
    @Column(name = "account_id", nullable = false)
    private Long accountId;

    @Column(name = "IBAN", nullable = false, length = 50)
    private String iban;

    @Column(name = "BIC", length = 15)
    private String bic;

    @ManyToOne(optional = false)
    @JoinColumn(name = "juicer_id", nullable = false)
    private Juicer juicer;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Juicer getJuicer() {
        return juicer;
    }

    public void setJuicer(Juicer juicer) {
        this.juicer = juicer;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}