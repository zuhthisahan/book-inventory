
import React, {Component} from 'react';
import axios from 'axios';
import jwtDecode from 'jwt-decode';
import { Redirect } from 'react-router-dom';
export default class Login extends Component{

    state ={}

    handleSubmit = e => {
        e.preventDefault();
 
        const data ={
            username: this.username,
            pass:this.password
            
        };
        axios.post('login',data).then(
            res => {
                localStorage.setItem('jwt', res.data.token);
               // console.log(jwtDecode(localStorage.getItem('jwt')).username)
               this.setState({
                   loggedIn : true
               });
               this.props.setUser(res.data.user);
            })
        
    };
 
    render() {
        if(this.state.loggedIn){
            return <Redirect to ={'/'}/>
        }
        return(
            <form onSubmit ={this.handleSubmit}>
            <h3>Login</h3>

            <div className="form-group">
                <label>User Name</label>
                <input type="text" className="form-control" placeholder="Username" 
                onChange ={e=> this.username = e.target.value}/>
                
            </div>

            <div className="form-group">
                <label>Password</label>
                <input type="password" className="form-control" placeholder="Password" 
                onChange ={e=> this.password = e.target.value}/>
                
            </div> 

      

            <button className="btn btn-primary btn-block">Login</button>

        </form>
        )
    }
}