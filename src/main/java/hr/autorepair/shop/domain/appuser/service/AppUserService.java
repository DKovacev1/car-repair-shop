package hr.autorepair.shop.domain.appuser.service;

import hr.autorepair.shop.domain.appuser.dto.AddAppUserRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserLookupRequest;
import hr.autorepair.shop.domain.appuser.dto.AppUserResponse;
import hr.autorepair.shop.domain.appuser.dto.UpdateAppUserRequest;

import java.util.List;

public interface AppUserService {
    List<AppUserResponse> getAppUsers(AppUserLookupRequest request);
    AppUserResponse getAppUser(Long idAppUser);
    AppUserResponse addAppUser(AddAppUserRequest request);
    AppUserResponse activateAppUser(Long idAppUser);
    void deactivateAppUser(Long idAppUser);
    AppUserResponse updateAppUser(Long idAppUser, UpdateAppUserRequest request);
}
