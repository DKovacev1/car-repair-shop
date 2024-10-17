package hr.autorepair.shop.domain.appuser.service;

import hr.autorepair.shop.domain.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.appuser.dto.UpdateAppUserRequest;

import java.util.List;

public interface AppUserService {
    List<AppUserResponse> getAppUsers(AppUserLookupRequest request);
    void addAppUser(AddAppUserRequest request);
    void activateAppUser(Long idAppUser);
    void deactivateAppUser(Long idAppUser);
    void updateAppUser(Long idAppUser, UpdateAppUserRequest request);
}
