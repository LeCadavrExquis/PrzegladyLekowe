import './App.css';
import {Login} from "./Authentication/Login"
import {Register} from "./Authentication/Register"
import {PageNotFound} from "./PageNotFound";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Home} from "./Home";
import {Dashboard} from "./Dashboard";
import { NavBar } from './NavBar';
import { StartingPage } from './StartingPage';
import { CreateAssignment } from './CreateAssignment';
import { useState } from 'react';
import { Assignment } from './Assignment';


function App() {


const [role, setRole] = useState("DEFAULT");

  return (
      <BrowserRouter>
      <NavBar role={role}/>
          <Routes>
              <Route path="/">
                  <Route index element={<Home />} />
                  <Route path='start' element={<StartingPage/>} />
                  <Route path="login" element={<Login />} />
                  <Route path="register" element={<Register />} />
                  <Route path="app" element={<Dashboard role={role} setRole={setRole}/>} />         {/* TODO clickable LIST OF FORMs FOR AND BY current USER */}
                  <Route path="*" element={<PageNotFound />} />
                  <Route path="assignment">
                    <Route index element={<CreateAssignment/>} />
                    <Route path=':id' element={<Assignment/>} />
                  </Route>
              </Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;
