import { PlusOutlined } from "@ant-design/icons";
import { Button } from "antd";

export const AddNewWorkplaceCard = ({onClick}) => {
    return (
        <Button
            style={{
                width: "30%",
                height: "390px",
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
