import axios from 'axios';
import React, { createContext, useState, useContext, useEffect } from 'react';
import { API_PATRIMONY_URL } from '../../common/api_url';

const DepartamentContext = createContext();

export default function DepartamentProvider({children}){

    const [departaments, setDepartaments] = useState([]);

    useEffect(
        () => {
            axios.get(`${API_PATRIMONY_URL}departament/all`).then(
                response => {
                    setDepartaments(response.data)
                }
            )
        }, []
    )    

    const [departamentModal, setDepatamentModal] = useState(
        { id: -1, name: '' }
    )

    const [showModal, setShowModal] = useState(false);
    const handleShowModal = () => setShowModal(true);
    const handleCloseModal = () => setShowModal(false);

    const [typeCrud, setTypeCrud] = useState('NEW');

    function newDepartament() {
        setTypeCrud('NEW');
        setDepatamentModal({ id: -1, name: '' });
        handleShowModal();
    }

    function editDepartament(departament) {
        setTypeCrud('EDIT');
        setDepatamentModal(departament);
        handleShowModal();
    }

    function deleteDepartament(id) {
        let confirmDelete = window.confirm('Confirma a exclusão do ítem?');
        if (confirmDelete) {
            axios.delete(`${API_PATRIMONY_URL}departament/${id}`);

            let novaLista = departaments.filter(item => item.id !== id);

            setDepartaments([
                ...novaLista
            ])
        }
    }

    function handleSubmit(event) {
        if (typeCrud === 'NEW') {
            axios.post(`${API_PATRIMONY_URL}departament`, departamentModal).then(
                response => {
                    setDepartaments([
                        ...departaments,
                        response.data
                    ])
                }
            )
            // let lastId = 0;
            // if (departaments.length > 0) {
            //     lastId = departaments[departaments.length - 1].id;
            // }
            // setDepartaments([
            //     ...departaments,
            //     {
            //         id: lastId + 1,
            //         name: departamentModal.name
            //     }
            // ]);
        } else {
            axios.put(`${API_PATRIMONY_URL}departament/${departamentModal.id}`, 
                    departamentModal);
            let departamentsList = departaments;
            for (let index = 0; index < departaments.length; index++) {
                const element = departaments[index];
                if (element.id === departamentModal.id) {
                    departamentsList[index] = departamentModal;
                }
            }
            setDepartaments([
                ...departamentsList
            ])
        }
        handleCloseModal();
        event.preventDefault();
    }

    return (
        <DepartamentContext.Provider value={{
            departaments,
            setDepartaments,
            newDepartament,
            editDepartament,
            deleteDepartament,
            showModal,
            handleCloseModal,
            handleSubmit,
            departamentModal,
            setDepatamentModal
        }}>
            {children}
        </DepartamentContext.Provider>
    );
}

export function useDepartaments(){
    const context = useContext(DepartamentContext);
    const { 
        departaments,
        setDepartaments,
        newDepartament,
        editDepartament,
        deleteDepartament,
        showModal,
        handleCloseModal,
        handleSubmit,
        departamentModal,
        setDepatamentModal
     } = context;
     return { 
        departaments,
        setDepartaments,
        newDepartament,
        editDepartament,
        deleteDepartament,
        showModal,
        handleCloseModal,
        handleSubmit,
        departamentModal,
        setDepatamentModal
     };
}