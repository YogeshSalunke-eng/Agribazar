package agribazar.service;

import agribazar.dtos.UserDto;
import agribazar.model.User;

public interface AuthService {

	public String verify(UserDto user);

	User register(UserDto dto);

	User forgetUser(UserDto dto);

}
