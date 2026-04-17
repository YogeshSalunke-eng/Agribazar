package agribazar.service;

import agribazar.dtos.LoginResponse;
import agribazar.dtos.UserDto;
import agribazar.model.User;

public interface AuthService {

	public LoginResponse verify(UserDto user);

	User register(UserDto dto);

	User forgetUser(UserDto dto);

}
