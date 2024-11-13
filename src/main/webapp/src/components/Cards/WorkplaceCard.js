import {
    DeleteOutlined,
    EditOutlined,
} from "@ant-design/icons";
import { Card } from "antd";
import Meta from "antd/es/card/Meta";
import workplaceImage from "../../images/workplace.png";

export const WorkplaceCard = ({ WorkplaceDetails, deleteFunction, editFunction }) => {
    return (
        <Card
            style={{
                width: "30%",
                margin: "1.5%",
                minHeight: "350px",
                fontSize: "1.2em",
            }}
            cover={
                <img
                    alt="example"
                    src={workplaceImage}
                    style={{
                        margin: "25px auto 0",
                        maxWidth: "250px",
                        width: "80%",
                    }}
                />
            }
            actions={[
                <EditOutlined
                    key="edit"
                    style={{ fontSize: "1.6em" }}
                    onClick={editFunction}
                />,
                <DeleteOutlined
                    key="delete"
                    style={{ fontSize: "1.6em" }}
                    onClick={deleteFunction}
                />,
            ]}
        >
            <Meta
                title={WorkplaceDetails.name}
            />
        </Card>
    );
};
