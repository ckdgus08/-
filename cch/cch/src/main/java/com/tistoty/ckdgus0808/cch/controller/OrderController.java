package com.tistoty.ckdgus0808.cch.controller;

import com.tistoty.ckdgus0808.cch.domain.Member;
import com.tistoty.ckdgus0808.cch.domain.Order;
import com.tistoty.ckdgus0808.cch.domain.item.Item;
import com.tistoty.ckdgus0808.cch.repository.OrderSearch;
import com.tistoty.ckdgus0808.cch.service.ItemService;
import com.tistoty.ckdgus0808.cch.service.MemberService;
import com.tistoty.ckdgus0808.cch.service.OrderService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {

        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String order(@RequestParam("memberId") Long memberId,
                        @RequestParam("itemId") Long itemId,
                        @RequestParam("count") int count) {

        orderService.order(memberId ,itemId, count);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute("orderSearch") OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancleOrder(@PathVariable("orderId") Long orderId) {
        orderService.cancleOrder(orderId);
        return "redirect:/orders";
    }
}
