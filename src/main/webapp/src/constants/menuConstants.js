import {
    CarOutlined,
    HomeOutlined,
    ScheduleOutlined,
    SettingOutlined,
    SolutionOutlined,
    TeamOutlined,
    ThunderboltOutlined,
    ToolOutlined,
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
                icon: <TeamOutlined />,
            },
            {
                key: "/workplaces",
                label: "Workplaces",
                icon: <HomeOutlined />,
            },
            {
                key: "/parts",
                label: "Car Parts",
                icon: <SettingOutlined />
            },
            {
                key: "/repairs",
                label: "Repairs",
                icon: <ToolOutlined />
            }
        ];
    } else if (isAuth && role === ROLE_NAMES.Employee) {
        menuItems = [
            ...menuItems,
            {
                key: "/users",
                label: "Users",
                icon: <TeamOutlined />,
            },
            {
                key: "/job-orders",
                label: "Job Orders",
                icon: <SolutionOutlined />,
            },
            {
                key: "/new-job-order",
                label: "New Job Order",
                icon: <ScheduleOutlined />,
            }
        ];
    } else if (isAuth && role === ROLE_NAMES.User) {
        return [
            ...menuItems,
            {
                key: "/my-cars",
                label: "My Cars",
                icon: <CarOutlined />,
            },
            {
                key: "/profile",
                label: "My Profile",
                icon: <UserOutlined />,
            },
        ];
    }
    return menuItems;
};
