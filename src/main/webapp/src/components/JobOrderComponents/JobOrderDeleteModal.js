import { Button, Modal } from "antd";
import { JobOrderService } from "../../service";

export const JobOrderDeleteModal = ({ orderData, open, close }) => {

    const handleOk = () => {
        JobOrderService.deleteJobOrder(orderData.idJobOrder).then(() =>
            close()
        );
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
                {orderData.car.carOwner.firstName[0] +
                    ". " +
                    orderData.car.carOwner.lastName}
                's order for {orderData.orderDate} at{" "}
                {orderData.timeFrom.substring(0, 5)}?
            </p>
        </Modal>
    );
};
