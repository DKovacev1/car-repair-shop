import { Button, Form, Modal } from "antd";
import { PartsService } from "../../service";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
import { CarPartForm } from "./CarPartForm";

export const EditPartModal = ({ partDetails, open, close }) => {
    const [newPartDetails, setNewPartDetails] = useState();

    const [formRef] = Form.useForm();

    useEffect(() => {
        setNewPartDetails(partDetails);
    }, [partDetails]);

    const handleOk = () => {
        PartsService.editPart(partDetails.idPart, newPartDetails).then(() =>
            close()
        );
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
            title="Edit Car Part Information"
            open={open}
            centered
            onOk={handleValidate}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleValidate}>
                    Update Car Part Information
                </Button>,
            ]}
            destroyOnClose
        >
            <CarPartForm
                partDetails={partDetails}
                formRef={formRef}
                onChange={(item) =>
                    setNewPartDetails({ ...newPartDetails, ...item })
                }
            />
        </Modal>
    );
};
