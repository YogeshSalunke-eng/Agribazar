package agribazar.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import agribazar.model.User;
import agribazar.dtos.UserRequestDTO;
import agribazar.repository.UserRepository;
import agribazar.service.UserService;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
  private UserRepository repository;
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public UserRequestDTO getUser() {
		 String username=SecurityContextHolder
				.getContext()
				.getAuthentication()
				.getName();
		       User user= repository.findByEmail(username).orElseThrow(()->
		        	new RuntimeException("user not found"));
		        return modelMapper.map(user,UserRequestDTO.class);
	}

}
