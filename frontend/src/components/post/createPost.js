import React from "react";
import {Form, Button, Alert} from 'react-bootstrap';

export default class CreatePost extends React.Component{
    state = {
        warnings: ''
    }

    previewPost = () =>{
        if (this._titleInput && this._contentInput) {
            console.log(this._titleInput.value)
            console.log(this._contentInput.value)
        }
    }

    post = () => {
        if (!this._titleInput.value || !this._contentInput.value) {
            this.setState({warnings: 'Please fill out everything'})
            return
        }
        this.setState({warnings: ''})
        fetch('http://localhost:8080/post', 
        {
            method: 'PUT',
            headers: {
                'Content-Type' : 'application/json'
            },
            data: JSON.stringify({
                parent: this.props.parent,
                creator: 'anonymer rekursiver Aufruf auf anonymen rekursiven Aufruf auf anonymen rekursiven Aufruf',
                title: this._titleInput.value,
                content: this._contentInput.value
            })
        })
        if (this.props.onFinish) {
            this.props.onFinish()
        }
    }
    render(){
        return(
            <div style={{width: '80%', marginLeft: '10%'}}>
                <Form>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlInput1">
                        <Form.Label>Title</Form.Label>
                        <Form.Control ref={(a) => this._titleInput = a} type="text" placeholder="Post title" />
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="exampleForm.ControlTextarea1">
                        <Form.Label>Enter your question</Form.Label>
                        <Form.Control ref={(a) => this._contentInput = a} as="textarea" rows={10} />
                    </Form.Group>
                    {console.log(this.state.warnings)}
                    {this.state.warnings && <Alert variant='warning' >{this.state.warnings}</Alert>}
                    <Button variant='outlined-primary' onClick={this.previewPost}>Preview question</Button>
                    <Button variant='outlined-primary' onClick={this.post}>Post question</Button>
                </Form>
            </div>
        )
    }
}