/*
 * Copyright © 2024. Roshen Perera
 */

package lk.ijse.POSBackend.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    private String id;
    private String name;
    private String address;
    private String phone;
}
