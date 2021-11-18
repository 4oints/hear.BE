package com.heardot.domain.Region;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "region")
@Getter
@ToString
@AllArgsConstructor @NoArgsConstructor
public class Region {

    @Id
    @Column(name = "region_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long regionId;

    private String latitude; //위도

    private String longitude; //경도
}
