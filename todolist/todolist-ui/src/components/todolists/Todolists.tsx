import { useTodolists } from "../../states/todolists.state"

const Todolists = () => {
    const { todolists, addTask } = useTodolists()

    return <div>
        <h1>Todolist page {todolists.isLoading ? "LOADING" : "DONE"}</h1>
        <ul>
            {todolists.data.map(todolist => <li key={todolist.name}>{todolist.name}</li>)}
        </ul>
        <button onClick={() => addTask("Task " + todolists.data.length + 1)}>ADD</button>

    </div>
}
export default Todolists