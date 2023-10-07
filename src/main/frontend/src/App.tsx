"use client";

import { Routes, Route } from 'react-router-dom'

// import * as petServices from './services/petService'
import Header from './components/Header/Header';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css'
import Home from './components/Home/Home';
// import { useEffect, useState } from 'react';
// import { Pet } from './lib/types';
import Register from './components/Register/Register';
import Login from './components/Login/Login';
import AddPet from './components/AddPet/AddPet';
import Catalog from './components/Catalog/Catalog';
import Contact from './components/Contact/Contact';
import { AuthContext } from './context/AuthContext';
import { LoginForm } from './lib/types';


function App() {


	// const [pets, setPets] = useState<Pet | null>();

	// useEffect(() => {
	// 	petServices.getAllPets()
	// 		.then((result: any) => {
	// 			setPets(result);
	// 		})
	// }, []);

	const onLoginSunmit = async (data: LoginForm) => {
		console.log(data)
	}

	return (
		<AuthContext.Provider value={{ onLoginSunmit }}>
			<Header />
			<Routes>
				<Route path='/' element={<Home />} />
				<Route path='/login' element={<Login />} />
				<Route path='/register' element={<Register />} />
				<Route path='/add-pet' element={<AddPet />} />
				<Route path='/catalog' element={<Catalog />} />
				<Route path='/contact' element={<Contact />} />
			</Routes>
		</AuthContext.Provider>
	);
}

export default App;
