import React, { Component } from "react";
import { Button, ButtonGroup, Col, Form, Row } from "react-bootstrap";
import { useCategories } from "../../../context/CategoryContext";

function SelectCategories() {
  const {
    categories
  } = useCategories();

  return (
    <React.Fragment>
      <Form.Group>
        <Row>
          <Col sm={9}>
            <Form.Select type="input" placeholder="Categorias">
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
    </React.Fragment>
  )
}

export default SelectCategories;