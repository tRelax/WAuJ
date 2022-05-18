package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.entity.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
