
import './App.css';
import api from './api/axiosConfig';
import React, { useState, useEffect } from'react';
import Layout from './components/Layout';

function App() {

  const [movies, setMovies] = useState();

  const getMovies = async () => {

    try{
       const response = await api.get("/api/v1/movies");
       setMovies(response.data);
      console.log(response.data);

    }catch(err){
      console.log(err);
    }
   
  }

  useEffect(() => {
    getMovies();
  }, [])


  return (
    <div className="App">
     
    </div>
  );
}

export default App;
