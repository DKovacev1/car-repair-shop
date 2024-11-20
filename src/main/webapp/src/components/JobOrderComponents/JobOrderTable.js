import { Button, Table, Tag } from "antd";
import dayjs from "dayjs";
import { useNavigate } from "react-router-dom";
import { AppContext } from "../../AppContext";
import React from "react";
import { ROLE_NAMES } from "../../constants";
import { JobOrderService } from "../../service";

export const JobOrderTable = ({
    jobOrders,
    openDeleteWindow,
    proceedToPayment,
}) => {
    const navigate = useNavigate();
    const appContext = React.useContext(AppContext);

    const columns = [
        {
            title: "Status",
            dataIndex: "jobOrderStatus",
            key: "jobOrderStatus",
            render: (_, jobOrder) => {
                const tagColor =
                    jobOrder.jobOrderStatus.idJobOrderStatus === 1
                        ? "#42A5F5"
                        : jobOrder.jobOrderStatus.idJobOrderStatus === 2
                        ? "#FF9800"
                        : "#4CAF50";
                return (
                    <>
                        <Tag color={tagColor}>
                            {jobOrder.jobOrderStatus.name}
                        </Tag>
                    </>
                );
            },
        },
        {
            title: "Owner's name",
            dataIndex: "ownersName",
            key: "ownersName",
            render: (_, jobOrder) => (
                <div>
                    {jobOrder.car.carOwner.firstName[0] +
                        ". " +
                        jobOrder.car.carOwner.lastName}
                </div>
            ),
        },
        {
            title: "Employee's name",
            dataIndex: "employeeName",
            key: "employeeName",
            render: (_, jobOrder) => (
                <div>
                    {jobOrder.jobOrderAppUserEmployee.firstName[0] +
                        ". " +
                        jobOrder.jobOrderAppUserEmployee.lastName}
                </div>
            ),
        },
        {
            title: "Car Maker & Modal",
            dataIndex: "modal",
            key: "modal",
            render: (_, jobOrder) => (
                <div>{jobOrder.car.maker + " " + jobOrder.car.model}</div>
            ),
        },
        {
            title: "Date",
            dataIndex: "orderDate",
            key: "orderDate",

            sorter: (a, b) => {
                const dateA = dayjs(a.orderDate);
                const dateB = dayjs(b.orderDate);

                if (dateA.isBefore(dateB)) {
                    return -1;
                }
                if (dateA.isAfter(dateB)) {
                    return 1;
                }
                return 0;
            },
            sortDirections: ["descend", "ascend"],

            render: (_, jobOrder) => (
                <div>
                    {jobOrder.timeFrom.substring(0, 5) +
                        " - " +
                        jobOrder.timeTo.substring(0, 5) +
                        " | " +
                        jobOrder.orderDate}
                </div>
            ),
        },
        {
            title: "Actions",
            key: "actions",
            render: (_, jobOrder) => (
                <div>
                    <Button
                        onClick={() =>
                            navigate("/job-order?id=" + jobOrder.idJobOrder)
                        }
                    >
                        Open Order
                    </Button>
                    {(appContext.role.name === ROLE_NAMES.Employee ||
                        appContext.role.name === ROLE_NAMES.Admin) && (
                        <>
                            <Button
                                onClick={() => openDeleteWindow(jobOrder)}
                                danger
                            >
                                Delete
                            </Button>
                            {jobOrder.jobOrderStatus.idJobOrderStatus == 3 &&
                                !jobOrder.isReceiptGiven && (
                                    <Button
                                        onClick={() =>
                                            proceedToPayment(jobOrder)
                                        }
                                        type="primary"
                                    >
                                        Payment
                                    </Button>
                                )}
                        </>
                    )}
                    {jobOrder.isReceiptGiven && (
                        <Button
                            onClick={() =>
                                JobOrderService.downloadReceipt(
                                    jobOrder.idJobOrder
                                )
                            }
                        >
                            Receipt
                        </Button>
                    )}
                </div>
            ),
        },
    ];

    return (
        <Table
            columns={columns}
            dataSource={jobOrders}
            locale={{ emptyText: "No Job Orders Found" }}
            onRow={(record) => {
                return {
                    onClick: () => {},
                };
            }}
        />
    );
};
