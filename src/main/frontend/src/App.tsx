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


function App() {
	// const [pets, setPets] = useState<Pet | null>();

	// useEffect(() => {
	// 	petServices.getAllPets()
	// 		.then((result: any) => {
	// 			setPets(result);
	// 		})
	// }, []);

	return (
		<>
			<Header />
			<Routes>
				<Route path='/' element={<Home />} />
				<Route path='/login' element={<Login />} />
				<Route path='/register' element={<Register />} />
			</Routes>
		</>
	);
}

export default App;