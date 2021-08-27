package pl.partabelo.demo.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "expenditure")

public class Expenditure {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "cost", nullable = false)
    private Double cost;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "create_time", nullable = false)
    private LocalDateTime createTime;
}

