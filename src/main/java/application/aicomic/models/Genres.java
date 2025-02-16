package application.aicomic.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "Genres")
@Data
public class Genres {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "genres_id", length = 50)
    private String genresId;

    @Column(name = "genres_name", length = 50)
    private String genresName;

    @Column(name = "genres_description", length = 250)
    private String genresDescription;

    @NotNull
    @Column(name = "status")
    private byte status;

    @ManyToMany
    @JoinTable(
            name = "Genres_Comics",
            joinColumns = @JoinColumn(name = "genres_id"),
            inverseJoinColumns = @JoinColumn(name = "comic_id")
    )
    private List<Comics> comics;
}
