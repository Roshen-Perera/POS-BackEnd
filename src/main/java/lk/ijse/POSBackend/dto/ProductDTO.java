/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ProductDTO {
    int id;
    String name;
    String type;
    int qty;
    double price;
}
