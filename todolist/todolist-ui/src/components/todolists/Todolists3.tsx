import { useDependency } from "../../services/ServiceProvider";
import { useQuery } from "react-query"


const Todolists3 = () => {
    const { todolistService } = useDependency()
    const { data, isLoading, error, refetch } = useQuery("todolists", todolistService.getAll)

    return <div>
        <h1>Todolist3 page
            <>{isLoading && "LOADING"}</>
            <>{error && "ERROR"}</></h1>
        <ul>
            {data && data.map(todolist => <li key={todolist.name}>{todolist.name}</li>)}
        </ul>
        <button onClick={() => refetch()}>Refresh</button>
    </div>
}
export default Todolists3