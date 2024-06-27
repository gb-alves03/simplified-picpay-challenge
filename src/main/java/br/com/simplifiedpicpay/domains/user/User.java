package br.com.simplifiedpicpay.domains.user;

import br.com.simplifiedpicpay.dtos.UserDtoRequest;
import br.com.simplifiedpicpay.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "User")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String document;
    @Column(unique = true)
    private String email;
    private String password;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private UserType userType;


    public User (UserDtoRequest userDto) {
     this.name = userDto.firstName() + " " + userDto.lastName();
     this.balance = userDto.balance();
     this.document = userDto.document();
     this.email = userDto.email();
     this.password = userDto.password();
     this.userType =  userDto.userType();
    }
}
