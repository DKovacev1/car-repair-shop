package hr.autorepair.shop.domain.appuser.service;

import hr.autorepair.shop.domain.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;

import java.util.List;

public interface AppUserService {
    List<AppUserResponse> getAppUsers(AppUserLookupRequest request);
    void addAppUser(AddAppUserRequest request);
    void activateAppUser(Long idAppUser);
}
