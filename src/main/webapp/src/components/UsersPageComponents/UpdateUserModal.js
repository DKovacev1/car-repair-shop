import { Form, Modal } from "antd";
import { RegisterForm } from "../RegisterForm";
import { UsersService } from "../../service";

export const UpdateUserModal = ({ userData, open, close }) => {
    const [form] = Form.useForm();

    const handleOk = (updatedUserData) => {
        UsersService.updateUser(userData.idAppUser, {
            ...userData,
            ...updatedUserData,
        });
    };

    return (
        <Modal
            title="Update User"
            open={open}
            centered
            onOk={handleOk}
            onCancel={close}
            footer={null}
            destroyOnClose
        >
            <RegisterForm
                data={userData}
                formRef={form}
                onFormFinish={handleOk}
                mode={"Update User"}
            />
        </Modal>
    );
};
