package mscreative.example.dlvr.models;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "location_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String address;

//    @Column(nullable = false)
//    private Coordinates coordinates;

    @Column(nullable = false)
    private double clearingCost;
}