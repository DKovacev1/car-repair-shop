import { SessionStorageService } from "../service/SessionStorageService";

export function authenticationReducer(state, action) {
    switch (action.type) {
        case "LOGIN_SUCCESS":
            return {
                ...state,
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
                loading: false,
                isAuthenticated: false,
                role: "",
                firstName: "",
                lastName: "",
                email: "",
            };
        default:
            return state;
    }
}