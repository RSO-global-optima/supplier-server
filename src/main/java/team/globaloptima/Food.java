package team.globaloptima;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "food")
public class Food implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "supplierId")
    private Integer supplierId;
    @Column(name = "food")
    private String food;
    @Column(name = "price")
    private Double price;

    public String getFood() {
        return food;
    }
    public void setAddress(String food) {
        this.food = food;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getSupplierId() {
        return supplierId;
    }
    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    public Double getPrice() {
        return this.price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
