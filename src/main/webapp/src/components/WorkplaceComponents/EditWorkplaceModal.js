import { Button, Form, Modal } from "antd";
import { WorkplaceService } from "../../service";
import { useEffect, useState } from "react";
import { WorkplaceForm } from "./WorkplaceForm";
import { toast } from "react-toastify";

export const EditWorkplaceModal = ({ workplaceDetails, open, close }) => {
    const [newWorkplaceDetails, setNewWorkplaceDetails] = useState();

    const [formRef] = Form.useForm();

    useEffect(() => {
        setNewWorkplaceDetails(workplaceDetails);
    }, [workplaceDetails]);

    const handleOk = () => {
        WorkplaceService.editWorkplace(workplaceDetails.idWorkplace, newWorkplaceDetails).then(() =>
            close()
        );
    };

    const handleValidate = async () => {
        try {
            const values = await formRef.validateFields();
            handleOk();
        } catch (errorInfo) {
            toast.error("All fields should be filled!");
        }
    };

    return (
        <Modal
            title="Edit Workplace Information"
            open={open}
            centered
            onOk={handleValidate}
            onCancel={close}
            footer={[
                <Button key="back" onClick={close}>
                    Return
                </Button>,
                <Button key="submit" type="primary" onClick={handleValidate}>
                    Update Workplace Information
                </Button>,
            ]}
            destroyOnClose
        >
            <WorkplaceForm
                workplaceDetails={workplaceDetails}
                formRef={formRef}
                onChange={(item) =>
                    setNewWorkplaceDetails({ ...newWorkplaceDetails, ...item })
                }
            />
        </Modal>
    );
};
