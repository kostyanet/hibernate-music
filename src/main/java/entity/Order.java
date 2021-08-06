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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_album",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "album_id")
    )
    private List<Album> albums = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "order_track",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "track_id")
    )
    private List<Track> tracks = new ArrayList<>();

    public Order() {
    }

    public Order(@NonNull int customerId, List<Album> albums, List<Track> tracks) {
        this.customerId = customerId;
        this.albums = albums;
        this.tracks = tracks;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", albums=" + albums +
                ", tracks=" + tracks +
                '}';
    }
}
