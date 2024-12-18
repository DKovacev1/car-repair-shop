import { initialUserDataState } from "../constants";
import { SessionStorageService } from "../service/SessionStorageService";

export function authenticationReducer(state, action) {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                ...state,
                idAppUser: action.payload.idAppUser,
                firstName: action.payload.firstName,
                lastName: action.payload.lastName,
                email: action.payload.email,
                role: action.payload.role ? action.payload.role : { name: "" },
                data: action.payload,
                loading: false,
                isAuthenticated: true,
            };
        case "LOGIN_FAILURE":
            return {
                ...state,
                loading: false,
                isAuthenticated: false,
            };
        case "TOKEN_CLEAR":
            SessionStorageService.removeToken();
            return {
                ...state,
                ...initialUserDataState,
                loading: false,
            };
        default:
            return state;
    }
}