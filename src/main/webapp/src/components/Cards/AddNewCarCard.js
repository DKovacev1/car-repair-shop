import { PlusOutlined } from "@ant-design/icons";
import { Button, Card } from "antd";
import { useNavigate } from "react-router-dom";

export const AddNewCarCard = () => {
    const navigate = useNavigate();
    return (
        <Button
            style={{
                width: "30%",
                height: "350px",
                margin: "1.5%",
                display: "flex",
                justifyContent: "center",
                alignItems: "center",
            }}
            onClick={() => navigate("/my-cars/add-new-car")}
        >
            <PlusOutlined style={{ fontSize: "3em" }} />
        </Button>
    );
};
