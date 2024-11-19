import {
    CalendarOutlined,
    CarOutlined,
    DatabaseOutlined,
    FileAddOutlined,
    HomeOutlined,
    SettingOutlined,
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
                key: "/job-orders-table",
                label: "Job Orders",
                icon: <DatabaseOutlined />,
            },

            {
                key: "/job-orders-calendar",
                label: "Repair Shop Calendar",
                icon: <CalendarOutlined />,
            },
            {
                key: "/workplaces",
                label: "Workplaces",
                icon: <HomeOutlined />,
            },
            {
                key: "/parts",
                label: "Car Parts",
                icon: <SettingOutlined />,
            },
            {
                key: "/repairs",
                label: "Repairs",
                icon: <ToolOutlined />,
            },
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
                key: "/new-job-order",
                label: "New Job Order",
                icon: <FileAddOutlined />,
            },
            {
                key: "/job-orders-table",
                label: "Job Orders",
                icon: <DatabaseOutlined />,
            },

            {
                key: "/job-orders-calendar",
                label: "My Calendar",
                icon: <CalendarOutlined />,
            },
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
