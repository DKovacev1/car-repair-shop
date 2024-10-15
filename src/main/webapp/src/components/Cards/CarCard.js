import { EditOutlined, EllipsisOutlined, SettingOutlined } from "@ant-design/icons";
import { Avatar, Card } from "antd";
import Meta from "antd/es/card/Meta";

export const CarCard = ({ carDetails }) => {
    return (
        <Card
            style={{ width: "30%", margin: "1.5%", minHeight: "350px" }}
            cover={
                <img
                    alt="example"
                    src={carDetails.imageSrc}
                    style={{ margin: "20px 0" }}
                />
            }
            actions={[
                <SettingOutlined key="setting" style={{ fontSize: "1.5em" }} />,
                <EditOutlined key="edit" style={{ fontSize: "1.5em" }} />,
                <EllipsisOutlined key="ellipsis" style={{ fontSize: "1.5em" }} />,
            ]}
        >
            <Meta
                title={carDetails.name}
                description={
                    carDetails.brand +
                    " " +
                    carDetails.model +
                    " | " +
                    carDetails.godina +
                    " | " +
                    carDetails.kilometri +
                    " km"
                }
            />
        </Card>
    );
};
