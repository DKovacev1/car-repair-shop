import { Button, Form, Modal } from "antd";
import { PartsService, RepairsService } from "../../service";
import React, { useState } from "react";
import { toast } from "react-toastify";
import { CarPartForm, RepairForm } from "./RepairForm";

export const AddNewRepairModal = ({ open, close }) => {
    const [newRepairDetails, setNewRepairDetails] = useState();

    const [formRef] = Form.useForm();

    const handleOk = () => {
        RepairsService.addRepair({
            ...newRepairDetails,
        }).then(() => close());
    };

    const handleValidate = async () => {
        try {
            const values = await formRef.validateFields();
            handleOk();
        } catch (errorInfo) {
            toast.error("All fields should be filled!");
        }
    };

    return (
        <Modal
            title="Add New Repair"
            open={open}
            centered
            onOk={handleValidate}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleValidate}>
                    Add New Repair
                </Button>,
            ]}
            destroyOnClose
        >
            <RepairForm
                formRef={formRef}
                onChange={(item) => {
                    setNewRepairDetails({ ...newRepairDetails, ...item });
                }}
            />
        </Modal>
    );
};
