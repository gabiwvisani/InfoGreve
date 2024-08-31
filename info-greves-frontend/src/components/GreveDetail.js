import { useEffect, useState } from 'react';
import { getGreveById } from '../api';
import { useParams } from 'react-router-dom';
import '../App.css';

const GreveDetail = () => {
  const { id } = useParams();
  const [greve, setGreve] = useState(null);

  useEffect(() => {
    const fetchGreve = async () => {
      try {
        const response = await getGreveById(id);
        setGreve(response.data);
      } catch (error) {
        console.error('Erro ao buscar greve:', error);
      }
    };

    fetchGreve();
  }, [id]);

  if (!greve) return <p>Carregando...</p>;

  return (
    <div>
      <h1>Detalhes da Greve</h1>
      <h5>Data de Início: {greve.dataInicio}</h5>
      <h5>Data de Fim: {greve.dataFim}</h5>
      <h5>Motivo: {greve.motivo}</h5>
      <h5>Categoria: {greve.categoria}</h5>
      <h5>Sindicato Responsável: {greve.sindicatoResponsavel}</h5>
      <h5>Número de Trabalhadores: {greve.numeroTrabalhadores}</h5>
      <h5>Local: {greve.local}</h5>
      <h5>Greve Teve Sucesso: {greve.greveTeveSucesso ? (<>Sim</>):(<>Não</>)}</h5>
      <h5>Resolução Acordo: {greve.resolucaoAcordo}</h5>
    </div>
  );
};

export default GreveDetail;
