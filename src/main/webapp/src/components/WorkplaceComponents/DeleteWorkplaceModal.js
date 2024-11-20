import { Button, Modal } from "antd";
import { WorkplaceService } from "../../service";

export const DeleteWorkplaceModal = ({ workplaceDetails, open, close }) => {
    const handleOk = () => {
        WorkplaceService.deleteWorkplace(workplaceDetails.idWorkplace).then(() => close());
    };

    return (
        <Modal
            title="Delete Workplace"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Delete Workplace
                </Button>,
            ]}
            destroyOnClose
        >
            <p>
                Do you want to delete{" "}
                {workplaceDetails.name}?
            </p>
        </Modal>
    );
};
