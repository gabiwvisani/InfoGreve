import { useEffect, useState } from 'react';
import { getAllGreves, deleteGreve } from '../api';
import { Link } from 'react-router-dom';
import { format } from 'date-fns';
import '../App.css';
import  'react-bootstrap';

const GreveList = () => {
  const [greves, setGreves] = useState([]);

  useEffect(() => {
    const fetchGreves = async () => {
      try {
        const response = await getAllGreves();
        setGreves(response.data);
      } catch (error) {
        console.error('Erro ao buscar greves:', error);
      }
    };

    fetchGreves();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteGreve(id);
      setGreves(greves.filter(greve => greve.idGreve !== id));
    } catch (error) {
      console.error('Erro ao deletar greve:', error);
    }
  };

  return (
    <div >
      <h1>Lista de Greves</h1>
      <ul>
        {greves.map(greve => (
          <li key={greve.idGreve}>
            {greve.categoria} - {greve.motivo} - Início: {format(new Date(greve.dataInicio), 'dd/MM/yyyy')} 
            {greve.dataFim ? (
              <>&nbsp; - Fim: {format(new Date(greve.dataFim), 'dd/MM/yyyy')}</>
            ) : (
              <>
                &nbsp; - Fim: não informado
                <Link to={`/edit/fim/${greve.idGreve}`}>
                  <button variant="info">Adicionar dados do final da greve</button>
                </Link>
              </>
            )}
            <Link to={`/edit/${greve.idGreve}`}>
              <button variant="info">Editar</button>
            </Link>
            <Link to={`/details/${greve.idGreve}`}>
              <button variant="info">Detalhes da Greve</button>
            </Link>
            <button variant="info" onClick={() => handleDelete(greve.idGreve)}>Deletar</button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default GreveList;
