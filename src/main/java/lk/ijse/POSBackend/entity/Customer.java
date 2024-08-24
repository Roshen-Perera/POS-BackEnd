/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Customer {
    private int id;
    private String name;
    private String address;
    private String phone;
}