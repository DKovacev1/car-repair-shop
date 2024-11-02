import {
    DeleteOutlined,
    EditOutlined,
    SettingOutlined,
} from "@ant-design/icons";
import { Card } from "antd";
import Meta from "antd/es/card/Meta";
import carImage from "../../images/car.png";

export const CarCard = ({ carDetails, deleteFunction, editFunction }) => {
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
                    src={carImage}
                    style={{
                        margin: "0 auto",
                        maxWidth: "250px",
                        width: "80%",
                    }}
                />
            }
            actions={[
                <SettingOutlined key="setting" style={{ fontSize: "1.6em" }} />,
                <EditOutlined key="edit" style={{ fontSize: "1.6em" }} />,
                <DeleteOutlined
                    key="delete"
                    style={{ fontSize: "1.6em" }}
                    onClick={deleteFunction}
                />,
            ]}
        >
            <Meta
                title={carDetails.name}
                description={
                    carDetails.maker +
                    " " +
                    carDetails.model +
                    " | " +
                    carDetails.yearOfProduction +
                    " | " +
                    carDetails.registrationPlate
                }
            />
        </Card>
    );
};
