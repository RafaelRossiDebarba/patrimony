import axios from 'axios';
import React, { createContext, useContext, useEffect, useState } from 'react';
import {API_PATRIMONY_URL} from '../../common/api_url';


const PatrimonyContext = createContext();

export default function PatrimonyProvider({children}){

  const basePatrimony = {
    name: "",
    description: "",
    price: null,
    status: true,
    departament: {
      id: 1
    },
    categories: [
      {
        id: 1,
      }
    ]
  }

  const [assets, setAssets] = useState([]);

  const [showModal, setShowModal] = useState(false);

  const handleCloseModal = () => setShowModal(false);
  const handleShowModal = () => setShowModal(true);

  const [typeCrud, setTypeCrud] = useState('NEW');

  const [patrimonyModal, setPatrimonyModal] = useState(basePatrimony)

  useEffect(
    () => {
      axios.get(`${API_PATRIMONY_URL}patrimony/all`).then(
        response => {
          setAssets(response.data)
        }
      )
    }, []
  )

  function getPatrimonyAll() {
    axios.get(`${API_PATRIMONY_URL}patrimony/all`).then(
      response => {
        setAssets(response.data)
      }
    )
  }

  function newPatrimony() {
    setTypeCrud('NEW');
    setPatrimonyModal(basePatrimony);
    handleShowModal();
  }

  function editPatrimony(patrimony) {
    setTypeCrud('EDIT');
    setPatrimonyModal(patrimony);
    handleShowModal();
  }

  function closeModal() {
    setPatrimonyModal(basePatrimony);
    handleCloseModal();
  }

  function submitPatrimony(event) {
    if(typeCrud === 'NEW') {
      axios.post(`${API_PATRIMONY_URL}patrimony/`, patrimonyModal).then(
        resp => {
          handleCloseModal();
          getPatrimonyAll();
        }
      )
    }
    else {
      axios.put(`${API_PATRIMONY_URL}patrimony/${patrimonyModal.id}`, patrimonyModal).then(
        resp => {
          handleCloseModal();
          getPatrimonyAll();
        }
      )
    }
    event.preventDefault();
  }

  function deletePatrimony(id) {
    let confirmDelete = window.confirm('Confirma a exclusão do Patrimonio?');
    if (confirmDelete) {
      axios.delete(`${API_PATRIMONY_URL}patrimony/${id}`).then(
        resp => {
          getPatrimonyAll();
        }
      )
    }
    
  }

  
  return (
    <PatrimonyContext.Provider value={{
      assets,
      setAssets,
      showModal,
      handleCloseModal,
      handleShowModal,
      patrimonyModal,
      setPatrimonyModal,
      typeCrud,
      newPatrimony,
      submitPatrimony,
      editPatrimony,
      closeModal,
      deletePatrimony
    }}>
        {children}
    </PatrimonyContext.Provider>
  );
}

export function useAssets(){
  const context = useContext(PatrimonyContext);
  const { 
    assets,
    setAssets,
    showModal,
    handleCloseModal,
    handleShowModal,
    patrimonyModal,
    setPatrimonyModal,
    typeCrud,
    newPatrimony,
    submitPatrimony,
    editPatrimony,
    closeModal,
    deletePatrimony
  } = context;
  return { 
    assets,
    setAssets,
    showModal,
    handleCloseModal,
    handleShowModal,
    patrimonyModal,
    setPatrimonyModal,
    typeCrud,
    newPatrimony,
    submitPatrimony,
    editPatrimony,
    closeModal,
    deletePatrimony
  };
}