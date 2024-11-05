import { PlusOutlined } from "@ant-design/icons";
import { Button } from "antd";

export const AddNewCarCard = ({onClick}) => {
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
            onClick={onClick}
        >
            <PlusOutlined style={{ fontSize: "3em" }} />
        </Button>
    );
};
