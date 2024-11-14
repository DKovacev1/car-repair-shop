import { Button, FloatButton, Table } from "antd";
import { useRepairs } from "../hooks";
import { useState } from "react";
import { PlusOutlined } from "@ant-design/icons";
import {
    AddNewRepairModal,
    DeleteRepairModal,
    EditRepairModal,
} from "../components";

export const RepairsPage = () => {
    const [repairsList, resetRepairs] = useRepairs();

    const [selectedRepair, setSelectedRepair] = useState({});

    const [isAddNewRepairModalOpened, setIsAddNewRepairModalOpened] =
        useState(false);
    const [isUpdateModalOpened, setIsUpdateModalOpened] = useState(false);
    const [isDeleteModalOpened, setIsDeleteModalOpened] = useState(false);

    const columns = [
        {
            title: "Repair Name",
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
            title: "Repair Time",
            dataIndex: "repairTime",
            key: "repairTime",
            render: (_, { repairTime }) => (
                <div>{repairTime.substring(0, 5)}</div>
            ),
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

    const openUpdateWindow = (repair) => {
        setIsUpdateModalOpened(true);
        setSelectedRepair(repair);
    };

    const openDeleteWindow = (repair) => {
        setIsDeleteModalOpened(true);
        setSelectedRepair(repair);
    };

    return (
        <div>
            <Table
                columns={columns}
                dataSource={repairsList}
                locale={{ emptyText: "No Repairs Found" }}
            />

            <AddNewRepairModal
                open={isAddNewRepairModalOpened}
                close={() => {
                    setIsAddNewRepairModalOpened(false);
                    resetRepairs();
                }}
            />

            <DeleteRepairModal
                repairDetails={selectedRepair}
                open={isDeleteModalOpened}
                close={() => {
                    setIsDeleteModalOpened(false);
                    resetRepairs();
                }}
            />

            <EditRepairModal
                repairDetails={selectedRepair}
                open={isUpdateModalOpened}
                close={() => {
                    setIsUpdateModalOpened(false);
                    resetRepairs();
                }}
            />

            <FloatButton
                shape="square"
                type="primary"
                style={{
                    insetInlineEnd: 48,
                }}
                icon={<PlusOutlined />}
                tooltip={<div>Add New Car Repair</div>}
                onClick={() => setIsAddNewRepairModalOpened(true)}
            />
        </div>
    );
};
