package application.aicomic.controllers;

import application.aicomic.config.Config;
import application.aicomic.dataAccess.CommentsDTO;
import application.aicomic.dataAccess.TransactionsDTO;
import application.aicomic.models.Comments;
import application.aicomic.models.Transactions;
import application.aicomic.services.TransactionsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/transaction")
@RestController
public class TransactionsController {
    private TransactionsService transactionsService;

    // Inject TransactionsService
    public TransactionsController(TransactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping("/getAll")
    public List<Transactions> getAllTransactions() {
        return transactionsService.getAllTransactions();
    }

    @PostMapping("/post")
    public Transactions addTransaction(@RequestBody Transactions transactions) {
        return transactionsService.addTransaction(transactions);
    }

    @PutMapping("/update")
    public Transactions updateTransaction(@PathVariable String id, @RequestBody TransactionsDTO transactionsDTO) {
        return transactionsService.updateTransaction(id, transactionsDTO);
    }

    @GetMapping("/getById")
    public Transactions getTransactionById(@PathVariable String id) {
        return transactionsService.getTransactionById(id);
    }

    @DeleteMapping("/delete")
    public Transactions deleteTransaction(@PathVariable String id) {
        return transactionsService.deleteTransaction(id);
    }

    @GetMapping("/return")
    public ResponseEntity<String> vnpReturn(@RequestParam Map<String, String> queryParams) {
        try {
            System.out.println("🔹 VNPAY Response: " + queryParams);

            String vnp_ResponseCode = queryParams.get("vnp_ResponseCode");
            String vnp_TxnRef = queryParams.get("vnp_TxnRef");
            String vnp_Amount = queryParams.get("vnp_Amount");
            String vnp_BankCode = queryParams.get("vnp_BankCode");
            String vnp_PayDate = queryParams.get("vnp_PayDate");
            String vnp_SecureHash = queryParams.get("vnp_SecureHash");

            if (vnp_ResponseCode == null || vnp_TxnRef == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Thiếu dữ liệu từ VNPAY");
            }

            // Kiểm tra chữ ký
            String signData = Config.hashAllFields(queryParams);
            if (!signData.equals(vnp_SecureHash)) {
                System.out.println("❌ Chữ ký không hợp lệ!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid signature");
            }

            boolean isSuccess = "00".equals(vnp_ResponseCode);
            transactionsService.saveTransactionToDB(vnp_TxnRef, vnp_Amount, vnp_BankCode, vnp_PayDate, isSuccess);

            return ResponseEntity.ok(isSuccess ? "✅ Payment successful" : "❌ Payment failed");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi xử lý giao dịch");
        }
    }
}
