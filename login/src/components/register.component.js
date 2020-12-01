
import React, {Component} from 'react'
import axios from 'axios';
export default class Register extends Component{

   handleSubmit = e => {
       e.preventDefault();

       const data ={
           username: this.username,
           email: this.email,
           password:this.password,
           postal_address: this.postalAdddress
       };
       axios.post('registration',data).then(
           res => {
               
           }
       )
       console.log("sdss")
       
   };


    render() {
        return(
            <form onSubmit ={this.handleSubmit}>
            <h3>Sign Up</h3>

            <div className="form-group">
                <label>User Name</label>
                <input type="text" className="form-control" placeholder="User Name" 
                onChange ={e=> this.username = e.target.value}/>
                
            </div>

            <div className="form-group">
                <label>Email</label>
                <input type="email" className="form-control" placeholder="email"
                onChange ={e=> this.email = e.target.value} />
            </div>

            <div className="form-group">
                <label>Postal Address</label>
                <input type="text" className="form-control" placeholder="Postal Address"
                onChange ={e=> this.postalAdddress = e.target.value} />
                
            </div>

            <div className="form-group">
                <label>Password</label>
                <input type="password" className="form-control" placeholder="Password" 
                onChange ={e=> this.password = e.target.value}/>
                
            </div> 

      

            <button className="btn btn-primary btn-block">Sign Up</button>

        </form>
        )
    }
}