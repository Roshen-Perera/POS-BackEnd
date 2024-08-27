package lk.ijse.POSBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    String orderId;
    String customerId;
    String customerName;
    String productId;
    String productName;
    String productType;
    int productQTYNeeded;
    double productPrice;
    double productTotal;
}
