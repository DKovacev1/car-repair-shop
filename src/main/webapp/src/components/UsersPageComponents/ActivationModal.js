import { Button, Modal } from "antd";
import { UsersService } from "../../service";

export const ActivationModal = ({ userData, open, close }) => {

    const handleOk = () => {
        UsersService.activateUser(userData.idAppUser);
        close();
    };
    
    return (
        <Modal
            title="User Activation"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Activate User
                </Button>,
            ]}
            destroyOnClose
        >
            <p>
                Do you want to activate user{" "}
                {userData.firstName + " " + userData.lastName}?
            </p>
        </Modal>
    );
};
