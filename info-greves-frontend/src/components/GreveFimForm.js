import { useEffect, useState } from 'react';
import { createGreve, updateGreveFinal, getGreveById } from '../api'; 
import { useNavigate, useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import '../App.css';



const GreveFimForm = ({ initialData = {} }) => {
  const [formData, setFormData] = useState(initialData);
  const navigate = useNavigate();
  const { id } = useParams(); // Pega o ID da URL
  const [errorMessage, setErrorMessage] = useState('');


  useEffect(() => {
    if (id) {
      const fetchGreveData = async () => {
        try {
          const response = await getGreveById(id);
          setFormData(response.data);
        } catch (error) {
          console.error('Erro ao buscar dados da greve:', error);
        }
      };

      fetchGreveData();
    }
  }, [id]); 

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData({
      ...formData,
      [name]: type === 'checkbox' ? checked : value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        await updateGreveFinal(id, formData);
      } else {
        await createGreve(formData);
      }
      navigate('/');
    } catch (error) {
        if (error.response && error.response.data) {
          toast.error(error.response.data); // Exibe a mensagem de erro como um pop-up
        } else {
          toast.error('Erro desconhecido ao salvar greve.');
        }
        console.error('Erro ao salvar greve:', error);
    }
  };
  

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Data de Fim:</label>
        <input
          type="date"
          name="dataFim"
          value={formData.dataFim || ''}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Greve Teve Sucesso:</label>
        <input
          type="checkbox"
          name="greveTeveSucesso"
          checked={formData.greveTeveSucesso || false}
          onChange={handleChange}
        />
      </div>
      <div>
        <label>Resolução Acordo:</label>
        <input
          type="text"
          name="resolucaoAcordo"
          value={formData.resolucaoAcordo || ''}
          onChange={handleChange}
          placeholder="Resolução Acordo"
        />
      </div>
      <button type="submit">Salvar</button>
    </form>
  );  
};

export default GreveFimForm;
