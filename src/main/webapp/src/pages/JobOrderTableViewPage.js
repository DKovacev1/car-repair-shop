import React, { useState } from "react";
import { JobOrderDeleteModal, JobOrderPaymentModal, JobOrderTable } from "../components";
import { useJobOrders } from "../hooks";

export const JobOrderTableViewPage = () => {
    const [jobOrders, resetJobOrders] = useJobOrders();
    const [selectedJobOrder, setSelectedJobOrder] = useState({
        idJobOrder: 1,
        description: "",
        orderDate: "",
        timeFrom: "",
        timeTo: "",
        workplace: {},
        jobOrderAppUserEmployee: {
            firstName: "",
            lastName: "",
        },
        car: {
            maker: "",
            model: "",
            carOwner: {
                firstName: "",
                lastName: "",
            },
        },
        jobOrderStatus: {},
        repairs: [],
        parts: [],
        isReceiptGiven: false,
    });

    const [isDeleteModalOpened, setIsDeleteModalOpened] = useState(false);
    const [isPaymentModalOpened, setIsPaymentModalOpened] = useState(false);

    const openDeleteWindow = (jobOrder) => {
        setIsDeleteModalOpened(true);
        setSelectedJobOrder(jobOrder);
    };

    const openPaymentWindow = (jobOrder) => {
        setSelectedJobOrder(jobOrder);
        setIsPaymentModalOpened(true);
    };

    return (
        <div>
            <JobOrderTable
                jobOrders={jobOrders}
                openDeleteWindow={openDeleteWindow}
                proceedToPayment={openPaymentWindow}
            />

            <JobOrderDeleteModal
                orderData={selectedJobOrder}
                open={isDeleteModalOpened}
                close={() => {
                    setIsDeleteModalOpened(false);
                    resetJobOrders();
                }}
            />

            <JobOrderPaymentModal
                orderData={selectedJobOrder}
                open={isPaymentModalOpened}
                close={() => {
                    setIsPaymentModalOpened(false);
                    resetJobOrders();
                }}
            />
        </div>
    );
};
