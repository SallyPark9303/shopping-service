package shoppingproject.shop.repository;

import lombok.Getter;
import lombok.Setter;
import shoppingproject.shop.domain.OrderStatus;

@Getter
@Setter
public class OrderSearch {

    private String memberName; // 회원이름
    private OrderStatus orderStatus;  // 주문상태
}
