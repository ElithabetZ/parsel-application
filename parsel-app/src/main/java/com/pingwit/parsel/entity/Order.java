package com.pingwit.parsel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.pingwit.parsel.entity.enums.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_sender")
    @NotNull(message = "Sender can't be empty")
    private User sender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_receiver")
    @NotNull(message = "Receiver can't be empty")
    private User receiver;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_route")
    @NotNull(message = "Route can't be empty")
    private Route route;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_transaction")
    @NotNull(message = "Transaction can't be empty")
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Type can't be empty")
    private Type type;
}
