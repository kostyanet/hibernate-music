package entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Artist has N albums.
 */
@Entity
@Getter
@Setter
@Table(name = "artist")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "full_name")
    private String fullName;

    @ManyToMany(mappedBy = "artists")
    private List<Album> albums = new ArrayList<>();

    public Artist() {
    }

    public Artist(@NonNull String fullName) {
        this.fullName = fullName;
    }
}
