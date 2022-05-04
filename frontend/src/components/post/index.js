import React from 'react';
import withRouter from './withRouterWrapper';
import {Badge, Button} from 'react-bootstrap'
import CreatePost from './createPost';

class Post extends React.Component{
    constructor(props){
        super(props);
        this.maxDepth = 3;
        this.state = {
            title: '',
            content: '',
            reactions: [],
            dateCreated: '',
            author: '',
            children: [], 
            answer: false
        } 
    }

    async componentDidMount(){
        await this.fetchPost()
    }

    fetchPost = async () => {
        const id = this.props.id;
        console.log("fetching mock data for: " + id)
        const res = await fetch("http://localhost:8080/post/"+id)
        const data = (await res.json())['data']
        this.setState({title: data['title'], content: data['content'], author: data['creator'], dateCreated: data['dateCreated'], reactions: data['reactions'], children: data['children']});
    }

    handleReaction = async (reactionUnicode) => {
        console.log("adding like for "+ this.props.id + ": " + reactionUnicode)
        const res = await fetch("http://localhost:8080/post/"+this.props.id, {
            method: 'POST', 
            contentType: 'application/json',
            body:{
                reaction: {unicodeChar: reactionUnicode, change: 1}
            }
        })
        console.log(await res.json())
        this.fetchPost();
    }

    createFinished = async () => {
        this.setState({answer: false})
        await this.fetchPost()
    }

    render(){
        return(
            <div className="container" style={{marginTop: '50px', alignContent: 'left', alignContent: 'left', textAlign: 'left'}}>
                <div className="card">
            <div className="card-body">
                {this.props.depth == 0 && <h4 className="card-title">{this.state.title}{' '}</h4>}
                {this.props.depth != 0 && <h6 className="card-title">{this.state.title}{' '}</h6>}
                <small><a href="localhost:3000" className="text-black text-decoration-none">{this.state.author}</a> <span className="card-subtitle mb-2 text-muted">{this.state.dateCreated}</span></small>
                <br /><br />
                <p className="card-text">{this.state.content}</p>
            Reactions: {' '}
            {this.state.reactions.map(el =>
                <Button style={{marginLeft: '5px'}} key={el['unicodeChar']} onClick={() => this.handleReaction(el['unicodeChar'])} className='badge rounded-pill bg-light text-dark'>{el['unicodeChar']} {el['amount']}</Button>)}
            <Button style={{marginLeft: '5px'}} key={"addComment"} onClick={() =>this.setState({answer: !this.state.answer})} > Add answer</Button>
            {this.state.answer && <CreatePost parent={this.state.id} onFinish = {this.createFinished} />}
            {this.props.depth < this.maxDepth && this.state.children.map(el => <Post depth={this.props.depth + 1} id={el}/>)}
        </div>
    </div>
</div>
        )
    }
}

export default withRouter(Post);