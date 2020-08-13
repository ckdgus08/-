package com.tistoty.ckdgus0808.cch.service;

import com.tistoty.ckdgus0808.cch.domain.Delivery;
import com.tistoty.ckdgus0808.cch.domain.Member;
import com.tistoty.ckdgus0808.cch.domain.Order;
import com.tistoty.ckdgus0808.cch.domain.OrderItem;
import com.tistoty.ckdgus0808.cch.domain.item.Item;
import com.tistoty.ckdgus0808.cch.repository.ItemRepository;
import com.tistoty.ckdgus0808.cch.repository.MemberRepository;
import com.tistoty.ckdgus0808.cch.repository.OrderRepository;
import com.tistoty.ckdgus0808.cch.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        //엔티티 생성
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAdress(member.getAddress());

        //주문상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문 생성
        Order order = Order.createMember(member, delivery, orderItem);

        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 취소
     */
    @Transactional
    public void cancleOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }

    /**
     * 검색
     */
    public List<Order> findOrders(OrderSearch orderSearch) {
        return orderRepository.findAllByCriteria(orderSearch);
    }
}
