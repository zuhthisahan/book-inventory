
import React, {Component} from 'react'
import axios from 'axios';

export default class Home extends Component{

constructor(props) {
    super(props);
 
    this.state = {
     books:[]
    };
  }
    componentDidMount= () => {

        const config = {
            headers: {
                Authorization : 'Bearer ' + localStorage.getItem('jwt')
            }
        };

          axios.get('books/',config).then(
        res => {
            this.setState({
                books :res.data
                
            })
            
           
             
            },
        err => {
            console.log(err)
        }
    )
    }


    render() {
      if(this.props.user){
        return(
//             <div>
                
//                 {this.state.books.map((name, index) => (
//                  <li key={index}>
//                  {name}
//                 </li>
//   ))}
                  
//             </div>
         
        <div>
        
        {/* <h2 className = "welcom">Welcome to the Book Shop </h2> */}
        <div>
            <h2>Available Books</h2>
      {this.state.books.map((book, index) => (
        <div key={index}>
        <ul>
        <li> {index+1}</li>
          <ul>
          <li>Title : {book.title}</li>
         <li>Author : {book.author}</li>
         <li>Prce : {book.price}</li>
         <button type="button" class="btn btn-secondary">AddToCart</button>
         </ul>
         </ul>
        </div>
      ))}
    </div>
            
      
        </div>
        )
      }
      return(
          <h2>You are not logged in</h2>
      )
   
    }
}