export const BASE_URL = "https://localhost:8080";

export const ROLE_NAMES = {
    Admin: "ADMIN",
    User: "USER",
    Employee: "EMPLOYEE",
};

export const initialFormUser = {
    firstName: "",
    lastName: "",
    email: "",
    isActivated: "",
    idRole: "",
};

export const initialUserDataState = {
    loading: true,
    isAuthenticated: false,
    firstName: "",
    lastName: "",
    email: "",
    role: "",
};
