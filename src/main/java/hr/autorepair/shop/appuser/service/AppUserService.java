package hr.autorepair.shop.appuser.service;

import hr.autorepair.shop.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.appuser.dto.AppUserResponse;

import java.util.List;

public interface AppUserService {
    List<AppUserResponse> getAppUsers(AppUserLookupRequest request);
    void addAppUser(AddAppUserRequest request);
    void activateAppUser(Long idAppUser);
}
