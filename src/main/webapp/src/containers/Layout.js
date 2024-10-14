import {
    LaptopOutlined,
    NotificationOutlined,
    UserOutlined,
} from "@ant-design/icons";
import { Layout, Menu, theme } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";
import React, { useEffect, useState } from "react";
import { AppContext } from "../AppContext";
import { getMenuItems } from "../constants";
import { useLocation, useNavigate } from "react-router-dom";

const items1 = ["1", "2", "3"].map((key) => ({
    key,
    label: `nav ${key}`,
}));

const items2 = [UserOutlined, LaptopOutlined, NotificationOutlined].map(
    (icon, index) => {
        const key = String(index + 1);

        return {
            key: `sub${key}`,
            icon: React.createElement(icon),
            label: `subnav ${key}`,

            children: new Array(4).fill(null).map((_, j) => {
                const subKey = index * 4 + j + 1;
                return {
                    key: subKey,
                    label: `option${subKey}`,
                };
            }),
        };
    }
);

export const CustomLayout = ({ children }) => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();

    const appContext = React.useContext(AppContext);
    const [menuItems, setMenuItems] = useState([]);

    const [collapsed, setCollapsed] = useState(false);

    const navigate = useNavigate();
    const currentLoc = useLocation();

    useEffect(() => {
        setMenuItems(
            getMenuItems(appContext.isAuthenticated, appContext.role.name)
        );
    }, [appContext]);

    return (
        <Layout style={{ minHeight: "100vh" }}>
            <Sider collapsible collapsed={collapsed} onCollapse={(value) => setCollapsed(value)} width={300} style={{ background: colorBgContainer }}>
                <Menu
                    theme="dark"
                    mode="inline"
                    defaultSelectedKeys={currentLoc.pathname}
                    style={{ height: "100%", borderRight: 0 }}
                    items={menuItems}
                    onClick={(item) => navigate(item.key)}
                />
            </Sider>
            <Layout style={{ padding: "24px" }}>
                <Content
                    style={{
                        padding: 24,
                        margin: 0,
                        minHeight: 280,
                        background: colorBgContainer,
                        borderRadius: borderRadiusLG,
                    }}
                >
                    {children}
                </Content>
            </Layout>
        </Layout>
    );
};
