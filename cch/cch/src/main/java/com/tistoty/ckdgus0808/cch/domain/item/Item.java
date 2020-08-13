package com.tistoty.ckdgus0808.cch.domain.item;

import com.tistoty.ckdgus0808.cch.domain.Category;
import com.tistoty.ckdgus0808.cch.exception.NotEnoughtStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name ="dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    /**
     * stock 증가
     */
    //==비지니스 로직==//
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * stock 감소
     */
    public void removeStock(int quantity) {
        int restStock = this.stockQuantity-quantity;
        if( restStock < 0 ) {
            throw new NotEnoughtStockException("더 많은 재고가 필요합니다.");
        }
        this.stockQuantity = restStock;
    }

}
