package com.pingwit.parsel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Phone number can't be empty")
    @Size(max = 9, message = "Phone number can't contain more than 9 digits")
    private String number;
    @NotBlank(message = "Email can't be empty")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Email must contain @")
    private String email;
}
