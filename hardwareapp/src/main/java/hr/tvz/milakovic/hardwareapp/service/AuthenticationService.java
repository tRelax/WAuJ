package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.command.LoginCommand;
import hr.tvz.milakovic.hardwareapp.DTO.LoginDTO;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
