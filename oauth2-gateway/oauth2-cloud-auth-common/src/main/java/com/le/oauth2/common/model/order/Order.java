package com.le.oauth2.common.model.order;


import com.le.oauth2.common.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    private Long id;

    private List<Product> products;

    private Long useMoney;
}
