import { useParams } from "react-router"

const withRouter = WrappedComponent => props => {
    const params = useParams();
    return (
        <WrappedComponent id={params.id} {...props} />
    )
}

export default withRouter;