import {Button, Col, Image, Layout, Menu, Row} from "antd";
import axios from "axios";
import { BASE_URL } from "../constants";
import { toast } from "react-toastify";
import {Content, Header} from "antd/es/layout/layout";

export const MainPage = () => {
    return (
        <Content style={{ padding: '20px', backgroundColor: '#f0f2f5' }}>
            <div style={{ position: 'relative', textAlign: 'center', color: 'white' }}>
                <Image
                    width="100%"
                    src="/slide1.jpg"
                    alt="Homepage Illustration"
                    style={{
                        filter: 'brightness(0.7)',
                        maxHeight: '400px',
                        objectFit: 'cover',
                    }}
                />
                <div style={{
                    position: 'absolute',
                    top: '50%',
                    left: '50%',
                    transform: 'translate(-50%, -50%)',
                    textAlign: 'center',
                    fontSize: '2rem',
                    fontWeight: 'bold',
                    textShadow: '2px 2px 4px rgba(0,0,0,0.7)',
                    width: '90%', // Responsive width for text
                }}>
                    <h2 style={{ fontSize: '2.5rem', marginBottom: '0.5rem' }}>
                        Welcome to Tvoje vozilo Zagreb
                    </h2>
                    <p style={{ fontSize: '1.5rem', fontWeight: 'normal' }}>
                        Your trusted service for worry-free driving
                    </p>
                </div>
            </div>

            <div style={{ marginTop: '40px' }}>
                <Row gutter={[16, 16]} justify="center">
                    {/* Service Box 1 */}
                    <Col xs={24} sm={12} md={8}>
                        <div style={{
                            padding: '30px 20px',
                            borderRadius: '8px',
                            backgroundColor: '#ffffff',
                            boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
                            textAlign: 'center',
                        }}>
                            <h3 style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>Reliable Repairs</h3>
                            <p style={{ fontSize: '1rem' }}>
                                Expert technicians to ensure your vehicle is in top condition.
                            </p>
                        </div>
                    </Col>
                    <Col xs={24} sm={12} md={8}>
                        <div style={{
                            padding: '30px 20px',
                            borderRadius: '8px',
                            backgroundColor: '#ffffff',
                            boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
                            textAlign: 'center',
                        }}>
                            <h3 style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>Affordable Prices</h3>
                            <p style={{ fontSize: '1rem' }}>
                                High-quality service without breaking the bank.
                            </p>
                        </div>
                    </Col>
                    <Col xs={24} sm={12} md={8}>
                        <div style={{
                            padding: '30px 20px',
                            borderRadius: '8px',
                            backgroundColor: '#ffffff',
                            boxShadow: '0 4px 6px rgba(0, 0, 0, 0.1)',
                            textAlign: 'center',
                        }}>
                            <h3 style={{ fontSize: '1.5rem', fontWeight: 'bold' }}>Fast Service</h3>
                            <p style={{ fontSize: '1rem' }}>
                                We value your time and strive for quick turnaround times.
                            </p>
                        </div>
                    </Col>
                </Row>
            </div>
        </Content>
    );
};
