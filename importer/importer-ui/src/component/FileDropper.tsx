import { Button } from "@mui/material"
import { useRef } from "react"
import useImporter from "../hooks/useImporter"


const FileDropper = () => {
    const { upload } = useImporter();
    const inputRef = useRef<HTMLInputElement>(null)

    const handleFiles = (files: FileList | null) => {

        if (files && files[0])
            upload(files[0])
    }

    return (<>
        <input type="file" hidden={true} ref={inputRef} accept={".csv"}
            onChange={(e => handleFiles(e.target.files))}
        ></input>
        <Button onClick={() => inputRef.current?.click()
        }>Choose a file</Button>
    </>
    )
}


export default FileDropper