package com.slippery.greenroots.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String location;
    private LocalDateTime startDate;
    private String status;
    private Long totalArea;
    @Lob
    private String goal;
    @Lob
    private String summary;
    private Long totalTreesPlanted;
    private String updates;
//    @ManyToOne
//    private Organization organization;
    @ManyToOne
    private Users createdBy;
    private LocalDateTime createdOn;
}
