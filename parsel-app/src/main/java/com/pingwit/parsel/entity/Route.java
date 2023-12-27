package com.pingwit.parsel.entity;

import com.pingwit.parsel.entity.enums.Shortcode;
import com.pingwit.parsel.entity.enums.Status;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Sender can't be empty")
    private Shortcode sender;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Receiver can't be empty")
    private Shortcode receiver;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status can't be empty")
    private Status status;
}
