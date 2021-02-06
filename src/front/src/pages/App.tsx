import React from 'react';
import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import {Global, css} from '@emotion/react';

import Home from './Home';
// import Header from '../components/layout/Header';
const SignIn = React.lazy(() => import('@src/pages/SignIn'));
const SignUp = React.lazy(() => import('@src/pages/SignUp'));

const App: React.FC = () => {
  
  return (
    <>
      {/* <Header /> */}
      <Global styles={resetCss}/>
      <React.Suspense fallback="">
        <Router>
          <Switch>
            <Route exact path="/signin" component={SignIn} />
            <Route exact path="/signup" component={SignUp} />
            <Route exact path="/" component={Home} />
          </Switch>
        </Router>
      </React.Suspense>
    </>
  );
}

const resetCss = css`
  * {
    margin: 0;
    padding: 0;
    list-style: none;
    box-sizing: border-box;
    font: inherit;
    &:focus {
      // border: 0;
      outline: 0;
    }
  }
  
  body {
    background-color: #fff;
  }
  ul,
  ol,
  li {
    list-style: none;
  }
  h1,
  h2,
  h3,
  h4,
  h5,
  h6 {
    font-weight: normal;
  }
  html {
    font-size: 0.625rem;
    font-family: 'Do Hyeon', sans-serif;
    @media (max-width: 320px) {
      font-size: 8px;
    }
  }
  body {
    -webkit-text-size-adjust: none;
  }
  a {
    text-decoration: none;
    color: inherit;
    font-size: inherit;
    cursor: pointer;
    vertical-align: middle;
    line-height: 1;
    -webkit-tap-highlight-color: transparent;
  }
  button {
    border: none;
    background-color: transparent;
    outline-width: 0;
    cursor: pointer;
    font-family: inherit;
    color: inherit;
    -webkit-tap-highlight-color: transparent;
  }
  img {
    max-width: 100%;
  }
  input,
  textarea {
    appearance: none;
    color: inherit;
    font-size: inherit;
    -webkit-border-radius: 0;
    -webkit-appearance: none;
    border: 0;
    border-color: transparent;
    &::placeholder {
      color: #a3a9b1;
    }
  }
  textarea {
    resize: none;
  }
  * {
    scrollbar-width: thin;
    scrollbar-color: #ddd transparent;
    scrollbar-face-color: #e0e0e0;
    scrollbar-track-color: #fff;
    scrollbar-arrow-color: none;
    scrollbar-highlight-color: #e0e0e0;
    scrollbar-3dlight-color: none;
    scrollbar-shadow-color: #e0e0e0;
    scrollbar-darkshadow-color: none;
    &::-webkit-scrollbar {
      width: 6px;
    }
    &::-webkit-scrollbar-track {
      background-color: transparent;
    }
    &::-webkit-scrollbar-thumb {
      border-radius: 3px;
      background-color: #ddd;
    }
    &::-webkit-scrollbar-button {
      width: 0;
      height: 0;
    }
  }
`

App.displayName = 'App';
export default App;
