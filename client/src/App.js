import logo from './logo.svg';
import './App.css';
import {useCallback, useState} from "react";
import {Login} from "./Login";
import {PageNotFound} from "./PageNotFound";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {Home} from "./Home";
import {Dashboard} from "./Dashboard";

function App() {
  return (
      <BrowserRouter>
          <Routes>
              <Route path="/">
                  <Route index element={<Home />} />
                  <Route path="login" element={<Login />} />
                  <Route path="app" element={<Dashboard />} />
                  <Route path="*" element={<PageNotFound />} />
              </Route>
          </Routes>
      </BrowserRouter>
  );
}

export default App;
