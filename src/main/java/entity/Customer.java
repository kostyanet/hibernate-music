package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer may buy N albums and M separated tracks
 */
@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "full_name")
    private String fullName;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    public Customer() {
    }

    public Customer(int id, @NonNull String fullName, List<Order> orders) {
        this.id = id;
        this.fullName = fullName;
        this.orders = orders;
    }
}
