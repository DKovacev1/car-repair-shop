export const SessionStorageService = {
    getToken: () => {
        return sessionStorage.getItem("authenticationToken");
    },

    addToken: (token) => {
        sessionStorage.setItem("authenticationToken", token);
    },

    removeToken: () => {
        if (sessionStorage.getItem("authenticationToken")) {
            sessionStorage.removeItem("authenticationToken");
        }
    },
};
