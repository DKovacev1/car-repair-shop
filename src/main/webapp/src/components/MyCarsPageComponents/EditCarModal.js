import { Button, Form, Modal } from "antd";
import { CarsService } from "../../service";
import { useState } from "react";
import { CarForm } from "./CarForm";

export const EditCarModal = ({ carDetails, open, close }) => {
    const [newCarDetails, setNewCarDetails] = useState(carDetails);

    const [formRef] = Form.useForm();

    const handleOk = () => {
        CarsService.editCar(carDetails.idCar, newCarDetails).then(() =>
            close()
        );
    };

    return (
        <Modal
            title="Edit Car Information"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Update Car Information
                </Button>,
            ]}
            destroyOnClose
        >
            <CarForm
                carDetails={carDetails}
                formRef={formRef}
                onChange={(item) => setNewCarDetails({ ...newCarDetails, ...item })}
            />
        </Modal>
    );
};
