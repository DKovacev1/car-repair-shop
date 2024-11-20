import { Button, Modal } from "antd";
import { PartsService } from "../../service";

export const DeletePartModal = ({ partDetails, open, close }) => {
    const handleOk = () => {
        PartsService.deletePart(partDetails.idPart).then(() => close());
    };

    return (
        <Modal
            title="Delete Car Part"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Delete Car Part
                </Button>,
            ]}
            destroyOnClose
        >
            <p>
                Do you want to delete{" "}
                {partDetails.name}?
            </p>
        </Modal>
    );
};
