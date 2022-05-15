import React from "react";
import { Button, Table, Form } from "react-bootstrap";
import Header from "../../components/Header";
import ModalForm from "../../components/ModalForm";

import { useCategories } from "../../context/CategoryContext";
import CountCategories from "./component/CountCategory";

export default function CategoryPage() {

  const {
    categories,
    categoryModal,
    deleteCategory,
    editCategory,
    handleCloseModal,
    handleSubmit,
    newCategory,
    setCategoryModal,
    showModal
  } = useCategories();

  return (
    <React.Fragment>
      <Header />

      <div className="container">
        <h2>Cadastro de Categorias</h2>

        <Button onClick={newCategory}>Novo</Button>

        <br />
        {
          categories.length === 0
            ? <div className="container">
              <h4>Sem registro em categorias</h4>
            </div>
            : <React.Fragment>
              <Table striped bordered hover>
                <thead>
                  <tr>
                    <th>Código</th>
                    <th>Nome</th>
                    <th>Ação</th>
                  </tr>
                </thead>
                <tbody>
                  {
                    categories.map(
                      categoryLoop => {
                        return <tr key={categoryLoop.id}>
                          <td>{categoryLoop.id}</td>
                          <td>{categoryLoop.name}</td>
                          <td>
                          <Button variant="outline-secondary"
                            onClick={() => editCategory(categoryLoop)}
                          >
                            Editar
                          </Button> {' '}
                          <Button variant="outline-secondary"
                            onClick={() => deleteCategory(categoryLoop.id)}
                          >
                            Excluir
                          </Button>
                          </td>
                        </tr>
                      }
                    )
                  }
                </tbody>
              </Table>
              <CountCategories />
            </React.Fragment>
        }
        <ModalForm
          title='Edição Registro'
          showModal={showModal}
          closeModal={handleCloseModal}
        >
          <Form id='myForm' onSubmit={handleSubmit}>
            <Form.Group size='lg' controlId="name">
              <Form.Label>Nome</Form.Label>
              <Form.Control
                autoFocus
                required={true}
                value={categoryModal.name}
                onChange={e => setCategoryModal(
                  {
                    ...categoryModal,
                    name: e.target.value
                  }
                )}
              />
            </Form.Group>
          </Form>
        </ModalForm>
      </div>
    </React.Fragment>
  )
}