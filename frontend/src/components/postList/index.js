import React from "react";
import {ListGroup, Badge} from 'react-bootstrap'
import { Link } from 'react-router-dom'


export default class PostList extends React.Component {

    constructor(){
        super()
        this.state = {
            postList: []
        }
    }
    async componentDidMount(){
        await this.loadPostList()
    }
    loadPostList = async () => {
        const res = await fetch("http://localhost:8080/questions")
        const data = await res.json()
        this.setState({postList: data.data})
    }

    render(){
        return (
            <div className='align-items-center'>
                <br />
                <h4>Questionslist</h4>
                <br />
                <ListGroup className='d-flex justify-content-center' as='ul' style={{textDecoration: 'none', width: '60%', marginLeft:'20%'}}>
                    
                    {this.state.postList.map(el => 
                        <Link key={el['id']} style={{textDecoration: 'none'}} to={`/post/${el['id']}`}>
                        <br />
                    <ListGroup.Item as='li' className="d-flex align-items-start ">
                        <div className="ms-2 me-auto" style={{textAlign: 'left', textDecoration:'none'}}>
                        <div className="fw-bold" style={{textDecoration: 'none'}}>{el['title']}</div>
                        {el['creator']}@{el['dateCreated']}
                        </div>
                        <Badge bg="primary" pill>
                            {el['answerCount']}
                        </Badge>

                    </ListGroup.Item>
                    </Link>
                    )}
                </ListGroup>
                
            </div>
        )
    }
}