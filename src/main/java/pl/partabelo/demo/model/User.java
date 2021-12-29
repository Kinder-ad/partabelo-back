package pl.partabelo.demo.model;

import lombok.*;
import pl.partabelo.demo.Models.PlaylistModel.TrackJson;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @Column(name = "is_request", nullable = false)
    private boolean isRequest;

    @Transient
    private String token;

    @OneToMany(cascade = CascadeType.ALL)
    private List<TrackHistory> tracks = new ArrayList<>();

    public User(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String username, String firstName, String lastName, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public void addTrackToHistory(TrackHistory trackHistory){
        this.tracks.add(trackHistory);
    }


    public void setRequest(Boolean aBoolean) {
        this.isRequest = aBoolean;
    }
}
