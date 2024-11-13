import { Button, Form, Modal } from "antd";
import { WorkplaceService } from "../../service";
import React, { useState } from "react";
import { toast } from "react-toastify";
import { WorkplaceForm } from "./WorkplaceForm";

export const AddNewWorkplaceModal = ({ open, close }) => {
    const [newWorkplaceDetails, setNewWorkplaceDetails] = useState();

    const [formRef] = Form.useForm();

    const handleOk = () => {
        WorkplaceService.addWorkplace({
            ...newWorkplaceDetails,
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
            title="Add New Workplace"
            open={open}
            centered
            onOk={handleValidate}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleValidate}>
                    Add New Workplace
                </Button>,
            ]}
            destroyOnClose
        >
            <WorkplaceForm
                formRef={formRef}
                onChange={(item) => {
                    setNewWorkplaceDetails({ ...newWorkplaceDetails, ...item });
                }}
            />
        </Modal>
    );
};
