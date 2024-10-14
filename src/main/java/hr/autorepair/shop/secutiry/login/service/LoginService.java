package hr.autorepair.shop.secutiry.login.service;

import hr.autorepair.shop.secutiry.login.dto.LoginRequest;
import hr.autorepair.shop.secutiry.login.dto.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}
