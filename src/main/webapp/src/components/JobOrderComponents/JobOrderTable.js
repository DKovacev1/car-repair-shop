import { Button, Table } from "antd";
import dayjs from "dayjs";

export const JobOrderTable = ({ jobOrders, openDeleteWindow }) => {
    const columns = [
        {
            title: "Status",
            dataIndex: "order_status",
            key: "order_status",
            /*render: (_, record) => (
                <>
                    {record.activated ? (
                        <UnlockFilled style={{ color: "green", fontSize: "2em" }} />
                    ) : (
                        <LockFilled
                            style={{ color: "red", fontSize: "2em" }}
                            onClick={() => {
                                setSelectedUser(record);
                                setIsActivationModalOpened(true);
                            }}
                        />
                    )}
                </>
            ),*/
        },
        {
            title: "User's name",
            dataIndex: "firstName",
            key: "firstName",
        },
        {
            title: "Employee's name",
            dataIndex: "emp_name",
            key: "emp_name",
        },
        {
            title: "Car Maker & Modal",
            dataIndex: "modal",
            key: "modal",
        },
        {
            title: "date",
            dataIndex: "date",
            key: "date",

            sorter: (a, b) => {
                const dateA = dayjs(a.date);
                const dateB = dayjs(b.date);
                return dateA.isBefore(dateB ? -1 : dateA.isAfter(dateB) ? 1 : 0);
            },
            sortDirections: ["descend", "ascend"],
        },
        {
            title: "Actions",
            key: "actions",
            render: (_, jobOrder) => (
                <div>
                    <Button onClick={() => openDeleteWindow(jobOrder)}>
                        Delete
                    </Button>
                    <Button onClick={() => console.log("Payment")}>
                        Proceed To Payment
                    </Button>
                </div>
            ),
        },
    ];

    return (
        <Table
            columns={columns}
            dataSource={jobOrders}
            locale={{ emptyText: "No Job Orders Found" }}
        />
    );
};
