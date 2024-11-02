import {
    CarOutlined,
    HomeOutlined,
    SolutionOutlined,
    ThunderboltOutlined,
    UserOutlined,
} from "@ant-design/icons";
import { ROLE_NAMES } from "./appConstants";

export const getMenuItems = (isAuth, role) => {
    var menuItems = [
        {
            key: "/",
            label: "Home",
            icon: <HomeOutlined />,
        },
    ];

    if (isAuth && role === ROLE_NAMES.Admin) {
        menuItems = [
            ...menuItems,
            {
                key: "/admin",
                label: "Admin",
                icon: <ThunderboltOutlined />,
            },
            {
                key: "/users",
                label: "Users",
                icon: <SolutionOutlined />,
            },
        ];
    } else if (isAuth && role === ROLE_NAMES.Employee) {
        menuItems = [
            ...menuItems,
            {
                key: "/users",
                label: "Users",
                icon: <SolutionOutlined />,
            },
        ];
    } else if (isAuth && role === ROLE_NAMES.User) {
        return [
            ...menuItems,
            {
                key: "/user",
                label: "User",
                icon: <UserOutlined />,
            },
            {
                key: "/my-cars",
                label: "My Cars",
                icon: <CarOutlined />,
            },
        ];
    }
    return menuItems;
};
