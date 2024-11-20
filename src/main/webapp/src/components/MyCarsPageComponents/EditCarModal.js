import { Button, Form, Modal } from "antd";
import { CarsService } from "../../service";
import { useEffect, useState } from "react";
import { CarForm } from "./CarForm";
import { toast } from "react-toastify";

export const EditCarModal = ({ carDetails, open, close }) => {
    const [newCarDetails, setNewCarDetails] = useState();

    const [formRef] = Form.useForm();

    useEffect(() => {
        setNewCarDetails(carDetails);
    }, [carDetails]);

    const handleOk = () => {
        CarsService.editCar(carDetails.idCar, newCarDetails).then(() =>
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
                carDetails={carDetails}
                formRef={formRef}
                onChange={(item) =>
                    setNewCarDetails({ ...newCarDetails, ...item })
                }
            />
        </Modal>
    );
};
