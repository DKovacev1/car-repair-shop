import { Button, Form, Modal } from "antd";
import { CarsService } from "../../service";
import React, { useState } from "react";
import { CarForm } from "./CarForm";
import { toast } from "react-toastify";
import { AppContext } from "../../AppContext";

export const AddNewCarModal = ({ open, close }) => {
    const [newCarDetails, setNewCarDetails] = useState();
    
    const userContext = React.useContext(AppContext);

    const [formRef] = Form.useForm();

    const handleOk = () => {
        CarsService.addCar({idCarOwner: userContext.idAppUser ,...newCarDetails}).then(() => close());
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
            title="Edit Car Information"
            open={open}
            centered
            onOk={handleValidate}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleValidate}>
                    Update Car Information
                </Button>,
            ]}
            destroyOnClose
        >
            <CarForm
                formRef={formRef}
                onChange={(item) =>
                    setNewCarDetails({ ...newCarDetails, ...item })
                }
            />
        </Modal>
    );
};
