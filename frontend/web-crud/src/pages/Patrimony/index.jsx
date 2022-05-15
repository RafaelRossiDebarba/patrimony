import React, {} from "react";
import { Button, ButtonGroup, Card, Col, Form, Modal, Row, Table } from "react-bootstrap";
import Header from "../../components/Header";
import {useAssets} from "../../context/PatrimonyContext";
import {useCategories} from "../../context/CategoryContext";
import {useDepartaments} from "../../context/DepartamentContext";

export default function PatrimonyPage() {
  const {
    assets,
    showModal,
    handleCloseModal,
    patrimonyModal,
    setPatrimonyModal,
    newPatrimony,
    submitPatrimony,
    typeCrud,
    editPatrimony,
    closeModal,
    deletePatrimony
  } = useAssets();

  const {
    categories
  } = useCategories();

  const {
    departaments
  } = useDepartaments();
   
  return (
    <React.Fragment>
      <Header />
      <div className="container">
        <h2>Cadastro de Patrimônio</h2>
        <Button variant="primary" onClick={newPatrimony}>Novo</Button>

        {
          assets.length === 0
          ? <div>Nenhum registro</div>
          : <React.Fragment>
            {
              assets.map(
                patrimonyLoop => {
                  return <Card>
                    <Card.Body>
                      <Card.Title>{patrimonyLoop.id}</Card.Title>
                      <Card.Text>
                        <span>Nome: </span>{patrimonyLoop.name}<br />
                        <span>Preço: </span>{patrimonyLoop.price}<br />
                        <span>Deepartamento: </span>{patrimonyLoop.departament.name}<br />
                          <Table>
                            <thead>
                              <tr>
                                <th>Categoria</th>
                              </tr>
                            </thead>
                            <tbody>
                              {
                                patrimonyLoop.categories.map(
                                  categories => {
                                    return <tr key={categories.id}>
                                      <td>{categories.name}</td>
                                    </tr>
                                  }
                                )
                              }
                            </tbody>
                          </Table>
                      </Card.Text>
                    </Card.Body>
                    <Card.Footer>
                      <ButtonGroup>
                        <Button
                          variant="primary"
                          onClick={() => {editPatrimony(patrimonyLoop)}} 
                        >
                          Editar
                        </Button>
                        <Button 
                          variant="danger"
                          onClick={() => {deletePatrimony(patrimonyLoop.id)}}
                        >
                          Deletar
                        </Button>
                      </ButtonGroup>
                    </Card.Footer>
                  </Card>
                }
              )
            }
            
          </React.Fragment>
        }

        <Modal show={showModal} onHide={handleCloseModal}>
          <Modal.Header>
            <Modal.Title>Edição Registro</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <Form id="formPatrimony">
              <Form.Group>
                <Form.Label>Nome</Form.Label>
                <Form.Control 
                  placeholder="Nome"
                  autoFocus
                  required={true}
                  value={patrimonyModal.name}
                  onChange={e => setPatrimonyModal(
                    {
                      ...patrimonyModal,
                      name: e.target.value
                    }
                  )}
                />
              </Form.Group>
              <Form.Group>
                <Form.Label>Descrição</Form.Label>
                <Form.Control 
                  placeholder="Descrição"
                  autoFocus
                  value={patrimonyModal.description}
                  onChange={e => setPatrimonyModal(
                    {
                      ...patrimonyModal,
                      description: e.target.value
                    }
                  )}
                />
              </Form.Group>
              <Row className="md-3">
                <Col xs="auto">
                  <Form.Group>
                    <Form.Label>Preço</Form.Label>
                    <Form.Control 
                      className="md-2" 
                      placeholder="Preço"
                      autoFocus
                      value={patrimonyModal.price}
                      onChange={e => setPatrimonyModal(
                        {
                          ...patrimonyModal,
                          price: parseInt(e.target.value)
                        }
                      )}
                    />
                  </Form.Group>
                </Col>
                <Col className="md-3">
                  <Form.Group>
                    <Form.Label>Departamento</Form.Label>
                    <Form.Select 
                      className="md-2"
                      placeholder="Departamento"
                      value={patrimonyModal.departament.id}
                      onChange={e => {
                        patrimonyModal.departament.id = e.target.value;
                      }}
                    >
                      {
                        departaments.map(
                          departamentLoop => {
                            return <option value={departamentLoop.id}>{departamentLoop.name}</option>
                          }
                        )
                      }
                    </Form.Select>
                  </Form.Group>
                </Col>
              </Row>
              {
                typeCrud === 'NEW'
                ? <h5>informe as Categorias</h5>
                : <h5>Categorias</h5>
              }
              <Form.Group>
                <Row>
                  <Col sm={9}>
                    <Form.Select 
                      placeholder="Categorias"
                      value={patrimonyModal.categories[0].id}
                      onChange={e => {
                        patrimonyModal.categories[0].id = e.target.value
                      }}
                    > 
                      {
                        categories.map(
                          category => {
                            return <option value={category.id}>{category.name}</option>
                          }
                        )
                      }
                    </Form.Select>
                  </Col>
                  <Col>
                    <ButtonGroup>
                      <Button variant="secondary">+</Button>
                      <Button variant="secondary">-</Button>
                    </ButtonGroup>
                  </Col>
                </Row>
              </Form.Group>
              
              
            </Form>
          </Modal.Body>
          <Modal.Footer>
            <Button variant="danger" onClick={closeModal}>
              Fechar
            </Button>
            <Button variant="primary" onClick={submitPatrimony}>
              Salvar
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    </React.Fragment>
  )
}