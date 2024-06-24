package br.com.simplifiedpicpay.mapper;

import br.com.simplifiedpicpay.domains.user.User;
import br.com.simplifiedpicpay.dtos.UserDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User convertToUserEntity(UserDtoRequest userDtoRequest) {
        User user = new User();
        user.setName(userDtoRequest.firstName() + " " + userDtoRequest.lastName());
        user.setDocument(userDtoRequest.document());
        user.setEmail(userDtoRequest.email());
        user.setPassword(userDtoRequest.password());
        user.setBalance(userDtoRequest.balance());
        user.setUserType(userDtoRequest.userType());

        return user;
    }
}
