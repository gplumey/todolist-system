


const useImporter = () => {

    const upload = async (file: File) => {

        const formData = new FormData();
        formData.append('file', file);
        const importData = await fetch('http://localhost:8081/import', {
            method: "POST",
            body: formData
        }).then((res) => {
            if (res.ok) {
                return res.json()
            } else {
                return Promise.reject("Upload failed")
            }
        })
        const startLink = importData._links.start.href;
        await fetch(startLink)
        console.log("Uploal File", file.name)
    }

    return { upload }
}



export default useImporter