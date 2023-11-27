package mscreative.example.dlvr.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "trip_table")
public class Trip
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", nullable = false)
    private Long id;

    @JoinColumn(nullable = false)
    @OneToOne
    private Location origin;

    @JoinColumn(nullable = false)
    @OneToOne
    private Location destination;
}