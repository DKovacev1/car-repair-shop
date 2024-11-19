import React from "react";
import { Descriptions, Card, List, Divider, Typography, Row, Col } from "antd";
const { Title, Text } = Typography;

export const JobOrderDetails = ({ jobOrder }) => {
    const {
        idJobOrder,
        description,
        orderDate,
        timeFrom,
        timeTo,
        workplace,
        jobOrderAppUserEmployee,
        car,
        jobOrderStatus,
        repairs,
        parts
    } = jobOrder;

    return (
        <Card style={{ margin: "20px", padding: "20px" }}>
            <div style={{ marginBottom: "20px" }}>
                <Row align="middle" justify="space-between">
                    <Col>
                        <Title level={3}>Job Order #{idJobOrder}</Title>
                    </Col>
                    <Col>
                        <Text type="secondary">
                            Status: {jobOrderStatus.name}
                        </Text>
                    </Col>
                </Row>
            </div>

            <Descriptions title="General Details" bordered column={1}>
                <Descriptions.Item label="Description">
                    {description}
                </Descriptions.Item>
                <Descriptions.Item label="Order Date">
                    {orderDate}
                </Descriptions.Item>
                <Descriptions.Item label="Time">
                    {timeFrom} - {timeTo}
                </Descriptions.Item>
                <Descriptions.Item label="Workplace">
                    {workplace.name}
                </Descriptions.Item>
            </Descriptions>

            <Divider />

            <Descriptions title="Assigned Employee" bordered column={1}>
                <Descriptions.Item label="Name">
                    {`${jobOrderAppUserEmployee.firstName} ${jobOrderAppUserEmployee.lastName}`}
                </Descriptions.Item>
                <Descriptions.Item label="Email">
                    {jobOrderAppUserEmployee.email}
                </Descriptions.Item>
            </Descriptions>

            <Divider />

            {/* Car Details */}
            <Descriptions title="Car Details" bordered column={1}>
                <Descriptions.Item label="Owner">
                    {`${car.carOwner.firstName} ${car.carOwner.lastName}`}
                </Descriptions.Item>
                <Descriptions.Item label="Registration Plate">
                    {car.registrationPlate}
                </Descriptions.Item>
                <Descriptions.Item label="Maker/Model">
                    {`${car.maker} ${car.model}`}
                </Descriptions.Item>
                <Descriptions.Item label="Year of Production">
                    {car.yearOfProduction}
                </Descriptions.Item>
                <Descriptions.Item label="Fuel Type">
                    {car.fuelType}
                </Descriptions.Item>
            </Descriptions>

            <Divider />

            <List
                header={<div>Repairs</div>}
                bordered
                dataSource={repairs}
                grid={{ gutter: 16, column: 3 }}
                renderItem={(repair) => (
                    <List.Item style={{marginTop:16}}>
                        <Descriptions bordered size="small" column={1}>
                            <Descriptions.Item label="Name">
                                {repair.name}
                            </Descriptions.Item>
                            <Descriptions.Item label="Cost">
                                ${repair.cost}
                            </Descriptions.Item>
                            <Descriptions.Item label="Repair Time">
                                {repair.repairTime}
                            </Descriptions.Item>
                        </Descriptions>
                    </List.Item>
                )}
            />

            <Divider />

            <List
                header={<div>Parts</div>}
                bordered
                dataSource={parts}
                grid={{ gutter: 16, column: 3 }}
                renderItem={({ part, quantity }) => (
                    <List.Item style={{marginTop:16}}>
                        <Descriptions bordered size="small" column={1}>
                            <Descriptions.Item label="Name">
                                {part.name}
                            </Descriptions.Item>
                            <Descriptions.Item label="Cost">
                                ${part.cost} each
                            </Descriptions.Item>
                            <Descriptions.Item label="Quantity">
                                {quantity}
                            </Descriptions.Item>
                            <Descriptions.Item label="Total Cost">
                                ${part.cost * quantity}
                            </Descriptions.Item>
                        </Descriptions>
                    </List.Item>
                )}
            />
        </Card>
    );
};
