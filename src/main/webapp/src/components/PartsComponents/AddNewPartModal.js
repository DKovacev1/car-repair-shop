import { Button, Form, Modal } from "antd";
import { PartsService } from "../../service";
import React, { useState } from "react";
import { toast } from "react-toastify";
import { CarPartForm } from "./CarPartForm";

export const AddNewPartModal = ({ open, close }) => {
    const [newPartDetails, setNewPartDetails] = useState();

    const [formRef] = Form.useForm();

    const handleOk = () => {
        PartsService.addPart({
            ...newPartDetails,
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
            title="Add New Car Part"
            open={open}
            centered
            onOk={handleValidate}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleValidate}>
                    Add New Car Part
                </Button>,
            ]}
            destroyOnClose
        >
            <CarPartForm
                formRef={formRef}
                onChange={(item) => {
                    setNewPartDetails({ ...newPartDetails, ...item });
                }}
            />
        </Modal>
    );
};
