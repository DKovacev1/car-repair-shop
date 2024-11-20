import { Button, Modal } from "antd";
import { UsersService } from "../../service";

export const ActivationModal = ({ mode, userData, open, close }) => {
    const handleOk = () => {
        if (mode === "DELETE") UsersService.deactivateUser(userData.idAppUser);
        else UsersService.activateUser(userData.idAppUser);
        close();
    };

    return (
        <Modal
            title={mode === "DELETE" ? "User Deactivation" : "User Activation"}
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    {mode === "DELETE" ? "Delete User" : "Activate User"}
                </Button>,
            ]}
            destroyOnClose
        >
            <p>
                Do you want to {mode === "DELETE" ? "delete" : "activate"} user{" "}
                {userData.firstName + " " + userData.lastName}?
            </p>
        </Modal>
    );
};
