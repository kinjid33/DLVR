package mscreative.example.dlvr.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "delivery_table")
public class Delivery
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    @OneToMany
    @ToString.Exclude
    private List<Trip> trips;

    @Column(nullable = false)
    private double costOfDel;
}