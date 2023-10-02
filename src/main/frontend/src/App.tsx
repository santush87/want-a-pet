"use client";

import { Routes, Route } from 'react-router-dom'

import * as petServices from './services/petService'
import Header from './components/Header/Header';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import RegisterPage from './components/models/RegisterPage';
import Home from './components/Home/Home';
import { useEffect, useState } from 'react';
import { Pet } from './lib/types';


function App() {
	const [pets, setPets] = useState<Pet | null>();

	useEffect(() => {
		petServices.getAllPets()
			.then((result: any) => {
				setPets(result);
			})
	}, []);

	return (
		<>
			<Header />
			<Routes>
				<Route path='/' element={<Home />} />
				{/* <Route path='/login' element={<RegisterPage />} /> */}
				<Route path='/register' element={<RegisterPage />} />
				{/* <Route path='/add-pet' element={<RegisterPage />} />
        <Route path='/catalog' element={<RegisterPage />} />
        <Route path='/contact' element={<RegisterPage />} /> */}
			</Routes>
		</>
	);
}

export default App;
