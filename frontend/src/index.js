import React from 'react';
import {createRoot} from 'react-dom/client'
import {BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './components/home'
import PostList from './components/postList';
import Post from './components/post'
import './index.css';
import NavigationBar from './components/NavigationBar/NavigationBar';
import reportWebVitals from './reportWebVitals';
import 'bootstrap/dist/css/bootstrap.css';
import About from './components/about/About';
import NotFound from './components/NotFound';
import FAQ from './components/faq';
import CreatePost from './components/post/createPost';

const root = createRoot(document.getElementById('root'))

root.render(
<React.StrictMode>
  <NavigationBar />
  <div style={{textAlign: 'center', justifyContent: 'center'}}>
    <Router >
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route exact path='/about' element={<About/>} />
        <Route exact path="/questionList" element={<PostList />} />
        <Route exact path="/faq" element={<FAQ />}/>
        <Route path="/post/:id" element={<Post depth={0} />} />
        <Route path="/createPost" element={<CreatePost />} />
        <Route path="*" element={<NotFound/>} />
      </Routes>
  </Router>
  </div>
  </React.StrictMode>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
//reportWebVitals(console.log);
