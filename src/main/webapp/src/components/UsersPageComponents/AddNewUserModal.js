import { Button, Modal } from "antd";

export const AddNewUserModal = ({ open, close }) => {

    const handleOk = () => {
        close();
    };
    
    return (
        <Modal
            title="Add New User"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleOk}>
                    Add User
                </Button>,
            ]}
            destroyOnClose
        >
            <p>
                Do you want to add new user?
            </p>
        </Modal>
    );
};
