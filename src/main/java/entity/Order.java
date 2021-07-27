package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Order belongs to certain customer
 * Order may contain N albums
 */
@Entity
@Getter
@Setter
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "customer_id")
    private int customerId;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Album> albums = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Track> tracks = new ArrayList<>();

    public Order() {
    }

    public Order(int id, @NonNull int customerId, List<Album> albums, List<Track> tracks) {
        this.id = id;
        this.customerId = customerId;
        this.albums = albums;
        this.tracks = tracks;
    }
}
