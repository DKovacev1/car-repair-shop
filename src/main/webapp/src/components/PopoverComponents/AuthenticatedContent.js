import { SettingOutlined } from "@ant-design/icons";
import { Button, Divider, Tag } from "antd";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../../AppContext";
import React from "react";
import { ROLE_NAMES } from "../../constants";
import { AdminContent } from "./AdminContent";
import { UserContent } from "./UserContent";

export const AuthenticatedContent = () => {
    const navigate = useNavigate();
    const appContext = React.useContext(AppContext);

    return (
        <div style={{ width: 225 }}>
            <div
                style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "space-between",
                }}
            >
                <div>{"Hi, " + appContext.firstName + "!"}</div>
                <Tag
                    color={
                        appContext.role.name === ROLE_NAMES.Admin ? "red" : "blue"
                    }
                >
                    {appContext.role.name.toUpperCase()}
                </Tag>
            </div>

            <Divider style={{ margin: "10px 0px" }} />

            {appContext.role.name === ROLE_NAMES.Admin ? (
                <AdminContent />
            ) : (
                <UserContent />
            )}

            <Button
                style={{ width: "100%", display: "flex", alignItems: "center" }}
                onClick={() => navigate("/settings")}
            >
                <SettingOutlined />
                Settings
            </Button>

            <Divider style={{ margin: "10px 0px" }} />

            <Button
                style={{ width: "100%" }}
                onClick={() =>
                    appContext.dispatch({
                        type: "TOKEN_CLEAR",
                    })
                }
                danger
            >
                Sign Out
            </Button>
        </div>
    );
};
