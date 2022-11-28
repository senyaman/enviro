package com.enviro.assessment.grad001.giftmasenya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "AccountProfiles")
public class AccountProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String accountHolderName;

    @Column(name = "surname")
    private String accountHolderSurname;

    @Column(name = "image_link")
    private URI httpImageLink;
}
