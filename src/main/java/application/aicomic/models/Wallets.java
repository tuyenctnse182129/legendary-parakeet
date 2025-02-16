package application.aicomic.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Wallets")
@Data
public class Wallets {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "wallet_id", length = 50)
    private String walletId;

    @Column(name = "balance")
    private double balance;

    @NotNull
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @NotNull
    @Column(name = "user_id", length = 50)
    private String userId;

    @NotNull
    @Column(name = "status")
    private byte status;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private Users user;

    @OneToMany(mappedBy = "wallets")
    private List<Transactions> transactions;

}
