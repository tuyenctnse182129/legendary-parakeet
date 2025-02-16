package application.aicomic.dataAccess;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TransactionsDTO {
    private String transactionId;
    private String transactionCode;
    private String content;
    private String bankName;
    private LocalDateTime transactionTime;
    private double amount;
    private byte status;
    private String orderId;
    private String walletId;
    private String purchasedCoinId;
}
