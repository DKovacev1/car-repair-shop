import { Button, Modal } from "antd";
import { CarsService } from "../../service";

export const DeleteCarModal = ({ carDetails, open, close }) => {
    const handleOk = () => {
        CarsService.deleteCar(carDetails.idCar).then(() => close());
    };

    return (
        <Modal
            title="Delete Car"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Delete Car
                </Button>,
            ]}
            destroyOnClose
        >
            <p>
                Do you want to delete{" "}
                {carDetails.maker + " " + carDetails.model} registration number{" "}
                {carDetails.registrationPlate}?
            </p>
        </Modal>
    );
};
