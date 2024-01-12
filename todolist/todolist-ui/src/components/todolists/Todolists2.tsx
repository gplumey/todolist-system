import { useTodolists2 } from "../../states/todolists.reducer"

const Todolists2 = () => {
    const [{ data, isLoading }, { addTask }] = useTodolists2();

    return <div>
        <h1>Todolist2 page {isLoading ? "LOADING" : "DONE"}</h1>
        <ul>
            {data.map(todolist => <li key={todolist.name}>{todolist.name}</li>)}
        </ul>
        <button onClick={() => addTask("Task " + data.length + 1)}>ADD</button>

    </div>
}
export default Todolists2