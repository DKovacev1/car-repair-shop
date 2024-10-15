import { SettingOutlined } from "@ant-design/icons";
import { Button, Divider } from "antd";
import { useNavigate } from "react-router-dom";

export const UnauthenticatedContent = () => {
    const navigate = useNavigate();

    return (
        <>
            <Button
                style={{ width: "100%", display: "flex", alignItems: "center" }}
                onClick={() => navigate("/settings")}
            >
                <SettingOutlined />
                Settings
            </Button>

            <Divider style={{ margin: "10px 0px" }} />

            <Button style={{ width: "100%" }} onClick={() => navigate("/login")}>
                Log In
            </Button>
            <Button style={{ width: "100%" }} onClick={() => navigate("/register")}>
                Sign Up
            </Button>
        </>
    );
};
