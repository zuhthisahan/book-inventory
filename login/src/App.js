
import { BrowserRouter, Route, Switch } from 'react-router-dom';
import '../node_modules/bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import Home from './components/home.component';
import Nav from './components/nav.component';
import Login from './components/login.component';
import Register from './components/register.component';
import { Component } from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
export default class App extends Component{
  state ={};

  componentDidMount= () => {

    const config = {
        headers: {
            Authorization : 'Bearer ' + localStorage.getItem('jwt')
        }
    };
  
    
   
    console.log("ssksk")
    
    try {
      var  x= jwtDecode(localStorage.getItem('jwt')).username;
       console.log("1"+x)
      // valid token format
    } catch(error) {
     var x=null;
      console.log("2"+x)
       
    }
    axios.get('user/'+x,config).then(

        res => {
            this.setUser(res.data);
        },
        err => {
            console.log(err)
        }
    )

    // axios.get('books/',config).then(
    //     res => {
    //         this.setState({
    //             books :res.data
    //         })
            
              
             
    //         },
    //     err => {
    //         console.log(err)
    //     }
    // )
        

};

setUser =  user => {
  this.setState({
    user : user
  });
};

  render() {
  return (
    
    <BrowserRouter>
    <div className="App">
  
     <Nav user ={this.state.user} setUser={this.setUser}/>
     <div className ="auth-wrapper">
        <div className = "auth-inner">
          <Switch>
            <Route exact path="/" component={() =><Home user = {this.state.user}/>} />
            <Route exact path="/login" component={() => <Login setUser ={this.setUser}/>} />
            <Route exact path="/registration" component={Register} />
          </Switch>
            
        </div>  
     </div> 




    </div>
    </BrowserRouter>
  );
}
}


