import { ROLE_NAMES } from "./appConstants";

export const getMenuItems = (isAuth, role) => {
    var menuItems = [
        {
            key: "/",
            label: "Home",
        },
        {
            key: "/register",
            label: "Register",
        },
        {
            key: "/login",
            label: "Login",
        },
    ];
    
    if (isAuth && role === ROLE_NAMES.Admin) {
        menuItems = [...menuItems,
            {
                key: "/admin",
                label: "Admin",
            },
            {
                key: "/users",
                label: "Users",
            },
        ];
    }
    else if (isAuth && role === ROLE_NAMES.User) {
        return [
            {
                key: "/user",
                label: "User",
            },
        ];
    }
    return menuItems;
};