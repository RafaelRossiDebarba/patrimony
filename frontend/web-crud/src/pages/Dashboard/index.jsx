import React from "react";
import { Alert, Card, Col, Row } from "react-bootstrap";

import Header from "../../components/Header";
import {useDepartaments} from "../../context/DepartamentContext";
import {useCategories} from "../../context/CategoryContext";
import {useAssets} from "../../context/PatrimonyContext";

export default function DashboardPage() {
    const {
        departaments
    } = useDepartaments();

    const {
        categories
    } = useCategories();

    const {
        assets
    } = useAssets();

    return(
        <React.Fragment>
            <Header />
            <div className="container">
                <Alert 
                    variant="success"
                    style={{marginTop: '15px'}}
                >
                    <Alert.Heading>Olá, Seja Bem-Vindo</Alert.Heading>
                    <p>
                        Selecione a opção desejada no menu acima.
                    </p>
                </Alert>
                <Row>
                    <Col>
                        <Card
                            bg="dark"
                            key="dark"
                            text='white'
                            className="mb-2"
                        >
                            <Card.Header>Departamentos</Card.Header>
                            <Card.Text
                                style={{paddingLeft: '5px' }}
                            >
                                {
                                    departaments.length
                                }
                                <p>Departamentos cadastrados</p>
                            </Card.Text>
                        </Card>
                    </Col>
                    <Col>
                        <Card
                            bg="ligth"
                            key="ligth"
                            text='dark'
                            className="mb-2"
                        >
                            <Card.Header>Categorias</Card.Header>
                            <Card.Text
                                style={{paddingLeft: '5px' }}
                            >
                                {
                                    categories.length
                                }
                                <p>categorias cadastradas</p>
                            </Card.Text>
                        </Card>
                    </Col>
                    <Col>
                        <Card
                            bg="success"
                            key="success"
                            text='white'
                            className="mb-2"
                        >
                            <Card.Header>Patrimonios</Card.Header>
                            <Card.Text
                                style={{paddingLeft: '5px' }}
                            >
                                {
                                    assets.length
                                }
                                <p>patrimonios cadastrados</p>
                            </Card.Text>
                        </Card>
                    </Col>
                </Row>
                
            </div>
        </React.Fragment>
    );
}