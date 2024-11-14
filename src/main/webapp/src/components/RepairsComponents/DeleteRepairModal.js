import { Button, Modal } from "antd";
import { RepairsService } from "../../service";

export const DeleteRepairModal = ({ repairDetails, open, close }) => {
    const handleOk = () => {
        RepairsService.deleteRepair(repairDetails.idRepair).then(() => close());
    };

    return (
        <Modal
            title="Delete Car Repair"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Delete Repair
                </Button>,
            ]}
            destroyOnClose
        >
            <p>
                Do you want to delete{" "}
                {repairDetails.name}?
            </p>
        </Modal>
    );
};
