import { CarOutlined } from "@ant-design/icons";
import { Button } from "antd";
import { useNavigate } from "react-router-dom";

export const UserContent = () => {
    const navigate = useNavigate();

    return (
        <>
            <Button
                style={{ width: "100%", display: "flex", alignItems: "center" }}
                onClick={() => navigate("/my-cars")}
            >
                <CarOutlined />
                My Cars
            </Button>
        </>
    );
};
