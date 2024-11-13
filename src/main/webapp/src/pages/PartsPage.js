import { Button, FloatButton, Table } from "antd";
import { useParts } from "../hooks";
import { useState } from "react";
import { PlusOutlined } from "@ant-design/icons";
import { AddNewPartModal, DeletePartModal, EditPartModal } from "../components";

export const PartsPage = () => {
    const [partsList, resetParts] = useParts();

    const [selectedCarPart, setSelectedCarPart] = useState({});

    const [isAddNewPartModalOpened, setIsAddNewPartModalOpened] =
        useState(false);
    const [isUpdateModalOpened, setIsUpdateModalOpened] = useState(false);
    const [isDeleteModalOpened, setIsDeleteModalOpened] = useState(false);

    const columns = [
        {
            title: "Car Part Name",
            dataIndex: "name",
            key: "name",
        },
        {
            title: "Cost",
            dataIndex: "cost",
            key: "cost",
            render: (_, { cost }) => <div>{cost.toFixed(2) + "€"}</div>,
        },
        {
            title: "Actions",
            dataIndex: "",
            key: "actions",
            render: (_, part) => {
                return (
                    <div>
                        <Button onClick={() => openUpdateWindow(part)}>
                            Update
                        </Button>
                        <Button onClick={() => openDeleteWindow(part)}>
                            Delete
                        </Button>
                    </div>
                );
            },
        },
    ];

    const openUpdateWindow = (part) => {
        setIsUpdateModalOpened(true);
        setSelectedCarPart(part);
    };

    const openDeleteWindow = (part) => {
        setIsDeleteModalOpened(true);
        setSelectedCarPart(part);
    };

    return (
        <div>
            <Table
                columns={columns}
                dataSource={partsList}
                locale={{ emptyText: "No Users Found" }}
            />

            <AddNewPartModal
                open={isAddNewPartModalOpened}
                close={() => {
                    setIsAddNewPartModalOpened(false);
                    resetParts();
                }}
            />

            <DeletePartModal
                partDetails={selectedCarPart}
                open={isDeleteModalOpened}
                close={() => {
                    setIsDeleteModalOpened(false);
                    resetParts();
                }}
            />

            <EditPartModal
                partDetails={selectedCarPart}
                open={isUpdateModalOpened}
                close={() => {
                    setIsUpdateModalOpened(false);
                    resetParts();
                }}
            />

            <FloatButton
                shape="square"
                type="primary"
                style={{
                    insetInlineEnd: 48,
                }}
                icon={<PlusOutlined />}
                tooltip={<div>Add New Car Part</div>}
                onClick={() => setIsAddNewPartModalOpened(true)}
            />
        </div>
    );
};
