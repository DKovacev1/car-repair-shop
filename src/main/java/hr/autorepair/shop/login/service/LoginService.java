package hr.autorepair.shop.login.service;

import hr.autorepair.shop.login.dto.LoginRequest;
import hr.autorepair.shop.login.dto.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}
