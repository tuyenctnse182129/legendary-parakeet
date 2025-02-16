package application.aicomic.services;

import application.aicomic.dataAccess.CommentsDTO;
import application.aicomic.dataAccess.TransactionsDTO;
import application.aicomic.enums.CommentsEnums;
import application.aicomic.enums.TransactionsEnums;
import application.aicomic.mapper.Mapper;
import application.aicomic.models.Comments;
import application.aicomic.models.Transactions;
import application.aicomic.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
@Service
public class TransactionsService {
    private TransactionsRepository transactionsRepository;
    private Mapper mapper;

    @Autowired
    public TransactionsService(TransactionsRepository transactionsRepository, Mapper mapper) {
        this.transactionsRepository = transactionsRepository;
        this.mapper = mapper;
    }

    public List<Transactions> getAllTransactions() {
        return transactionsRepository.findAll();
    }

    public Transactions getTransactionById(String id) {
        return transactionsRepository.findById(id).get();
    }

    public Transactions addTransaction(Transactions transaction) {
        return transactionsRepository.save(transaction);
    }

    public Transactions updateTransaction(String id, TransactionsDTO transactionsDTO) {
        Transactions transactions = transactionsRepository.findById(id).orElseThrow(() -> new RuntimeException("Transaction not found"));
        mapper.updateTransactions(transactions, transactionsDTO);
        return transactionsRepository.save(transactions);
    }

    public Transactions deleteTransaction(String id) {
        Optional<Transactions> transactions = transactionsRepository.findById(id);
        if (transactions.isPresent()) {
            Transactions x = transactions.get();
            x.setStatus(TransactionsEnums.NOT_PAID.getValue());
            return transactionsRepository.save(x);
        }
        return null;
    }
    public void saveTransactionToDB(String transactionCode, String amount, String bankName, String payDate, boolean isSuccess) {
        try {
            System.out.println("🔹 Đang lưu giao dịch...");
            System.out.println("Mã giao dịch: " + transactionCode);
            System.out.println("Số tiền: " + amount);
            System.out.println("Ngân hàng: " + bankName);
            System.out.println("Ngày thanh toán: " + payDate);
            System.out.println("Trạng thái: " + (isSuccess ? "PAID" : "NOT_PAID"));
//            System.out.println("walletId: " + transactions.getWalletId());
//            System.out.println("orderId: " + transaction.getOrderId());
//            System.out.println("purchasedCoinId: " + transaction.getPurchasedCoinId());
//            transactionsRepository.save(transaction);


            Transactions transaction = new Transactions();
            transaction.setTransactionCode(transactionCode);
            transaction.setAmount(Double.parseDouble(amount) / 100);
            transaction.setBankName(bankName);
            transaction.setTransactionTime(LocalDateTime.parse(payDate, DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
            transaction.setStatus(isSuccess ? TransactionsEnums.PAID.getValue() : TransactionsEnums.NOT_PAID.getValue());

            transactionsRepository.save(transaction);
            System.out.println("✅ Giao dịch đã lưu thành công!");
        } catch (Exception e) {
            System.out.println("❌ Lỗi khi lưu giao dịch:");
            e.printStackTrace();
        }
    }

}
