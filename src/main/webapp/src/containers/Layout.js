import { UserOutlined } from "@ant-design/icons";
import { Avatar, Layout, Menu, Popover, theme } from "antd";
import { Content, Header } from "antd/es/layout/layout";
import Sider from "antd/es/layout/Sider";
import React, { useEffect, useState } from "react";
import { AppContext } from "../AppContext";
import { getMenuItems } from "../constants";
import { useLocation, useNavigate } from "react-router-dom";
import { PopoverContent } from "../components";

export const CustomLayout = ({ children }) => {
    const {
        token: { colorBgContainer, borderRadiusLG },
    } = theme.useToken();

    const appContext = React.useContext(AppContext);
    const [menuItems, setMenuItems] = useState([]);

    const [collapsed, setCollapsed] = useState(false);

    const navigate = useNavigate();
    const currentLoc = useLocation();

    const [locationPathname, setLocationPathname] = useState("/");

    useEffect(() => {
        setMenuItems(
            getMenuItems(appContext.isAuthenticated, appContext.role.name)
        );
        setLocationPathname(currentLoc.pathname);
    }, [appContext]);

    return (
        <Layout style={{ minHeight: "100vh" }}>
            <Header
                style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                }}
            >
                <Avatar shape="square" size="large">
                    Tvoje Vozilo
                </Avatar>
                <Popover content={<PopoverContent />} placement="bottomRight">
                    <Avatar
                        shape="square"
                        size="large"
                        icon={<UserOutlined />}
                    />
                </Popover>
            </Header>
            <Layout>
                <Sider
                    collapsible
                    collapsed={collapsed}
                    onCollapse={(value) => setCollapsed(value)}
                    width={300}
                    style={{ background: colorBgContainer }}
                >
                    <Menu
                        theme="dark"
                        mode="inline"
                        selectedKeys={locationPathname}
                        style={{ height: "100%", borderRight: 0 }}
                        items={menuItems}
                        onClick={(item) => {
                            navigate(item.key);
                            setLocationPathname(item.key);
                        }}
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
        </Layout>
    );
};
