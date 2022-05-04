import React from "react";
import { Link } from "react-router-dom";



const Home = () => {
    return (
        <div>
            <br /><br /><br />
            <h2>Welcome to DHBW42 </h2>
            <br />
            <h4>A wise person once said: "Root weiß was er tut" <br />
            And using this website is the fastest way to become a DHBW-Root 🥳</h4>
            <br />
            <Link style={{border:'3px lightgray solid', textDecoration: 'none', color: 'red', borderRadius: '5%', padding: '5px'}} to={'/questionList'}>View questions</Link>&emsp;&emsp;&emsp;&emsp;<Link style={{border:'3px lightgray solid', textDecoration: 'none', color: 'red', borderRadius: '5%', padding: '5px'}} to='/ask'>Ask a question</Link>
        </div>
    )
}

export default Home;