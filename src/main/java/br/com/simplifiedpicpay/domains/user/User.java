package br.com.simplifiedpicpay.domains.user;

import br.com.simplifiedpicpay.enums.UserType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity(name = "users")
@Table(name = "User")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String document;
    private String email;
    private String password;
    private UserType userType;


}
