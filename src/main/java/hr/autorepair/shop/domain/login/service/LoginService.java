package hr.autorepair.shop.domain.login.service;

import hr.autorepair.shop.domain.login.dto.LoginRequest;
import hr.autorepair.shop.domain.login.dto.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}
